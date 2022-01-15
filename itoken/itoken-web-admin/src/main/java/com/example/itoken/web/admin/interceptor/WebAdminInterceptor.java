package com.example.itoken.web.admin.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.example.itoken.common.domain.TbSysUser;
import com.example.itoken.common.dto.BaseResult;
import com.example.itoken.common.utils.CookieUtils;
import com.example.itoken.web.admin.client.AuthServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;


public class WebAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthServiceClient authServiceClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = CookieUtils.getCookieValue(request, "token");

        if (StrUtil.isBlank(token)) {
            request.getSession();
            response.sendRedirect("http://localhost:8503/login?redirectUrl=http://localhost:8601");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws IOException {

        HttpSession session = request.getSession();

        TbSysUser tbSysUser = (TbSysUser) session.getAttribute("tbSysUser");
        // 已登录状态
        if (Objects.nonNull(tbSysUser)) {
            if (Objects.nonNull(modelAndView)) {
                modelAndView.addObject("tbSysUser", tbSysUser);
            }
        }
        // 未登录
        else {
            String token = CookieUtils.getCookieValue(request, "token");
            if (StrUtil.isBlank(token)) {
                BaseResult tbSysUserByToken = authServiceClient.getTbSysUserByToken(token);
                Object obj = tbSysUserByToken.getData();
                if (Objects.nonNull(obj)) {
                    tbSysUser = JSONUtil.toBean(JSONUtil.parseObj(obj), TbSysUser.class);
                    request.getSession().setAttribute("tbSysUser", tbSysUser);
                }
            }

        }

        // 二次确认
        if (Objects.nonNull(tbSysUser)) {
            response.sendRedirect("http://localhost:8503/login?redirectUrl=http://localhost:8601");
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
