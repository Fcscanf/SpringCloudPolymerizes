package com.fcant.service_acti.controller;

import com.fcant.service_acti.result.Result;
import com.fcant.service_acti.service.WorkFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ActivitiController
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 22:16 2020/8/1/0001
 */
@RestController
@Api(tags = "activiti",value = "工作流控制器")
@RequestMapping("/activiti")
@Slf4j
public class ActivitiController {

    @Autowired
    private WorkFlowService workFlowService;


    @PostMapping("/apply")
    @ApiOperation(value="启动请假流程")
    public Result startWorkflow(@RequestParam(required = false) String pdKey){
        Map param = new HashMap(4){{
            put("applyUserId","001");
            put("approveUserIds", Arrays.asList("001","002","003"));
        }};

        if(StringUtils.isBlank(pdKey)){
            pdKey="QjFlow";
        }
        // 启动流程
        String pdId = workFlowService.startWorkflow(pdKey, "QJ001", param);
        // 获取请假申请任务节点
        String Id = workFlowService.getCurrentTask(pdId);
        // 完成请假申请任务节点
        Map continueParam = new HashMap(2){{
            put("dealUserId",param.get("applyUserId"));
        }};
        workFlowService.continueWorkflow(Id,continueParam);
        return Result.success().reSetMsg("请假已提交");
    }

    @PostMapping("/approve")
    @ApiOperation(value="审批请假流程")
    public Result continueWorkflow(@RequestParam String pId,@RequestParam String result){
        Map param = new HashMap(2){{
            put("dealUserId","001");
            put("result",result);
        }};

        // 获取请假审批任务节点
        String Id = workFlowService.getCurrentTask(pId);
        // 完成请假审批任务节点
        workFlowService.continueWorkflow(Id,param);
        return Result.success().reSetMsg("审批成功");
    }

    @PostMapping("/delegate")
    @ApiOperation(value="委托请假流程")
    public Result delegateWorkflow(@RequestParam String pId,@RequestParam String userId){
        Map param = new HashMap(2){{
            put("dealUserId",userId);
        }};
        // 获取请假审批任务节点
        String Id = workFlowService.getCurrentTask(pId);
        // 完成请假审批任务节点
        workFlowService.delegateWorkflow(Id,param);
        return Result.success().reSetMsg("委托成功");
    }

    /**
     *  查询用户待办流程实例
     * @param userId
     * @param pdKey
     */
    @GetMapping("/user-process")
    @ApiOperation(value="查询用户待办流程实例")
    public Result findUserProcessIds(@RequestParam String userId, @RequestParam(required = false) String pdKey) {
        if(StringUtils.isBlank(pdKey)){
            pdKey="QjFlow";
        }
        // 获取流程图
        return Result.success().addData("workflow", workFlowService.findUserProcessIds(userId,pdKey,1,0));
    }

    /**
     * 读取流程资源
     * @param pId 流程实例id
     */
    @GetMapping("/read-resource")
    @ApiOperation(value="读取流程资源")
    public void readResource(@RequestParam String pId, HttpServletResponse response) {
        // 获取流程图
        workFlowService.getProcessImage(pId, response);
    }

}
