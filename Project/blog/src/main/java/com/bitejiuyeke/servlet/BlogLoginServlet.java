package com.bitejiuyeke.servlet;

import com.bitejiuyeke.model.User;
import com.bitejiuyeke.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户登录
 *
 * @Author 比特就业课
 * @Date 2022-07-06
 */
@WebServlet("/login")
public class BlogLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        // 接收参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 校验参数
        if (username == null || username.equals("") || password == null || password.equals("")) {
            resp.getWriter().write("<h3>用户名或密码错误</h3>");
            return;
        }
        // 查询数据库里的用户信息
        UserDao userDao = new UserDao();
        User user = userDao.selectByName(username);
        if (user == null) {
            resp.getWriter().write("<h3>用户名或密码错误</h3>");
            return;
        }
        // 校验密码是否正确
        if (user.getPassword().equals(password) == false) {
            resp.getWriter().write("<h3>用户名或密码错误</h3>");
            return;
        }
        // 登录成功,创建会话
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        // 重定向到博客列表页面
        resp.sendRedirect("blog_list.html");

    }
}
