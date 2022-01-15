package com.example.itoken.common.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 初始化常量拦截器
 */
public class ConstantInterceptor implements HandlerInterceptor {

    private final String HOST_CDN = "http://192.168.0.111:81";

    private final String TEMPLATE_ADMIN_LTE = "/admin-lte/v3.3.7";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (Objects.nonNull(modelAndView)) {
            modelAndView.addObject("adminLte", String.format("%s%s", HOST_CDN, TEMPLATE_ADMIN_LTE));
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
