package com.fcant.gateway_zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

/**
 * AuthorizeFilter
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 15:57 2020/7/30/0030
 */
public class AuthorizeFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizeFilter.class);
    private static String accessToken;

    public AuthorizeFilter() {
        accessToken = UUID.randomUUID().toString();
        LOGGER.info("==> accessToken : {}", accessToken);
    }


    /**
     * 外部请求 -> zuul:pre -> 选择路由的服务:routing -> 请求服务:post -> zuul
     * pre: 在请求路由之前执行
     * routing: 在请求路由之后执行
     * post: 在请求到路由对应的服务之后执行
     * error: 在其他阶段发生错误时执行
     *
     * @return filterType
     * @author Fcant 下午 15:58 2020/7/30/0030
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器执行的顺序：数字越小优先级约高
     *
     * @return 返回代表过滤器执行顺序等级的数字
     * @author Fcant 下午 15:59 2020/7/30/0030
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 需不需要执行此过滤器
     *
     * @return True为执行
     * @author Fcant 下午 16:00 2020/7/30/0030
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体的过滤逻辑
     *
     * @return 返回值被忽略
     * @author Fcant 下午 16:00 2020/7/30/0030
     */
    @Override
    public Object run() throws ZuulException {
        // 获取请求的上下文，注意导入时导入zuul包下的，而非apache包下的
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String accessToken = request.getParameter("access_token");
        // 模拟accessToken验证授权
        // 使用Objects.equals(Object1, Object2)比较两个对象的一直性更具安全性
        if (Objects.equals(accessToken, AuthorizeFilter.accessToken)) {
            currentContext.setResponseStatusCode(HttpStatus.OK.value());
            currentContext.setResponseBody("Authorized");
            // 此处设置停止路由，因为模拟请求未做其他API转发处理
            currentContext.setSendZuulResponse(false);
        } else {
            // 如果验证不通过就返回springframework.http.HttpStatus提供的HTTP状态码
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            currentContext.setResponseBody(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            // 此处设置停止路由转发，因为模拟请求未做其他API转发处理
            currentContext.setSendZuulResponse(false);
        }
        return null;
    }
}
