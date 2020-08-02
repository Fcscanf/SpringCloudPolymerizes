package com.fcant.service_acti.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Result
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 23:03 2020/8/1/0001
 */
public class Result<T> {

    private int statusCode;
    private String message;
    private Map<String, Object> data = new HashMap<>();
    private Map<String, List<T>> list = new HashMap<>();

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, List<T>> getList() {
        return list;
    }

    public void setList(Map<String, List<T>> list) {
        this.list = list;
    }

    /**
     * 操作失败返回方法
     *
     * @return MsgUtil
     * @author Fcant 下午 19:36:50 2020/4/6/0006
     */
    public static Result fail() {
        Result result = new Result();
        result.setStatusCode(100);
        result.setMessage("操作失败！");
        return result;
    }

    /**
     * 操作成功返回方法
     *
     * @return MsgUtil
     * @author Fcant 下午 19:36:50 2020/4/6/0006
     */
    public static Result success() {
        Result result = new Result();
        result.setStatusCode(200);
        result.setMessage("操作成功！");
        return result;
    }

    /**
     * 添加返回的附加数据对象
     *
     * @param key 返回对象在Map中的key
     * @param data 返回的对象
     * @return MsgUtil
     * @author Fcant 下午 19:35:48 2020/4/6/0006
     */
    public Result addData(String key, Object data) {
        this.getData().put(key, data);
        return this;
    }

    /**
     * 添加返回的附加数据
     *
     * @param key 返回对象在Map中的key
     * @param list 返回的List结果集
     * @return MsgUtil
     * @author Fcant 下午 19:35:20 2020/4/6/0006
     */
    public Result addData(String key, List list) {
        this.getList().put(key, list);
        return this;
    }

    /**
     * 重新设置结果信息
     *
     * @param message 重新设置的信息内容
     * @return MsgUtil
     * @author Fcant 下午 19:33:27 2020/4/6/0006
     */
    public Result reSetMsg(String message) {
        this.setMessage(message);
        return this;
    }
}
