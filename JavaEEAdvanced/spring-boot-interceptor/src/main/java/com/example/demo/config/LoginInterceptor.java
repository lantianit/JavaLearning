package com.example.demo.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 此方法返回一个 boolean，如果为 true 表示验证成功，可以继续执行后续流程
     * 如果是 false 表示验证失败，后面的流程不能执行了
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 用户登录业务判断
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userinfo") != null) {
            // 说明用户已经登录
            return true;
        }
        // 可以调整到登录页面 or 返回一个 401/403 没有权限码
        response.sendRedirect("/login.html");
//        response.setStatus(403);
        return false;
    }
}
