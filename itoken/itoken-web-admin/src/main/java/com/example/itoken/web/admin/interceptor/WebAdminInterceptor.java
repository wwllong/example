package com.example.itoken.web.admin.interceptor;

import cn.hutool.core.util.StrUtil;
import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.common.utils.CookieUtils;
import com.example.itoken.service.api.facade.SSOServiceClientFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;


public class WebAdminInterceptor implements HandlerInterceptor {

    private final String ADMIN = "admin";
    private final String TOKEN = "token";

    @Autowired
    private SSOServiceClientFacade authServiceClientFacade;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = CookieUtils.getCookieValue(request, TOKEN);

        if (StrUtil.isBlank(token)) {
            request.getSession();
            response.sendRedirect("http://localhost:8503/login?redirectUrl=http://localhost:8601");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws IOException {

        HttpSession session = request.getSession();

        TbSysUser tbSysUser = (TbSysUser) session.getAttribute(ADMIN);
        // 已登录状态，校验token是否有效
        if (Objects.nonNull(tbSysUser)) {
            String token = CookieUtils.getCookieValue(request, TOKEN);
            if (StrUtil.isNotBlank(token)) {
                tbSysUser = authServiceClientFacade.getTbSysUserByToken(token);
                if (Objects.isNull(tbSysUser)) {
                    request.getSession().removeAttribute(ADMIN);
                }
            }else{
                tbSysUser = null;
            }
        }
        // 局部未登录，尝试从token获取
        else {
            String token = CookieUtils.getCookieValue(request, TOKEN);
            if (StrUtil.isNotBlank(token)) {
                tbSysUser = authServiceClientFacade.getTbSysUserByToken(token);
            }
        }

        // 二次确认
        if (Objects.isNull(tbSysUser)) {
            response.sendRedirect("http://localhost:8503/login?redirectUrl=http://localhost:8601");
        }else if (Objects.nonNull(modelAndView)){
            modelAndView.addObject(ADMIN, tbSysUser);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
