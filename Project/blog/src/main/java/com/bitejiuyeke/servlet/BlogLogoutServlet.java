package com.bitejiuyeke.servlet;

import com.bitejiuyeke.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 注销
 *
 * @Author 比特就业课
 * @Date 2022-07-06
 */
@WebServlet("/logout")
public class BlogLogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 找到Session对象并销毁
        HttpSession session = req.getSession(false);
        // 用户是未登录状态
        if (session == null) {
            resp.sendRedirect("blog_login.html");
            return;
        }
        // 用户已登录
        // 方法1. 删除之前在session中绑定的user对象，使登录失效
//        session.removeAttribute("user");
        // 方法2. 使session本身失效，并解绑所有对象
        session.invalidate();
        // 2. 重定向到登录页面
        resp.sendRedirect("blog_login.html");

    }
}
