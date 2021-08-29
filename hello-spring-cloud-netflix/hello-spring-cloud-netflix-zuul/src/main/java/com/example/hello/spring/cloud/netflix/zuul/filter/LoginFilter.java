package com.example.hello.spring.cloud.netflix.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    /**
     * 配置过滤类型
     * 1. pre：路由前
     * 2. routing：路由时
     * 3. post：路由后
     * 4. error：发送错误调用
     * @return 过滤类型字符串表示
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 配置过滤的顺序
     * @return 数值越小优先级越高
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 配置是否需要过滤
     * @return true or false
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体业务代码
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("{} >>> {}", request.getMethod(), request.getRequestURL().toString());
        String token = request.getParameter("token");
        if (token == null) {
            logger.warn("Token is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                HttpServletResponse response = context.getResponse();
                response.setContentType(MediaType.TEXT_HTML_VALUE+";charset=UTF-8");
                response.getWriter().write("Token is empty!");
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
        return null;
    }
}
