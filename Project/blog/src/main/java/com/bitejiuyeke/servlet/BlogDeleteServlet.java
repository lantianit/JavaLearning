package com.bitejiuyeke.servlet;

import com.bitejiuyeke.model.Blog;
import com.bitejiuyeke.model.BlogDao;
import com.bitejiuyeke.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除博客
 * @Author 比特就业课
 * @Date 2022-07-06
 */
@WebServlet("/blogDelete")
public class BlogDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        // 检查用户是否登录
        User user = Utils.checkLogin(req);
        if (user == null) {
            // 这里不涉及ajax，可以使用sendRedirect
            resp.sendRedirect("blog_login.html");
            return;
        }
        // 获取参数
        String blogIdStr = req.getParameter("blogId");
        if (blogIdStr == null || blogIdStr.equals("")) {
            resp.getWriter().write("<h3>当前传入的文章 ID 缺失.</h3>");
            return;
        }
        // 查询数据库，校验文章是否存在
        int blogId = Integer.parseInt(blogIdStr);
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(blogId);
        if (blog == null) {
            resp.getWriter().write("<h3>要删除的文章不存在.</h3>");
            return;
        }
        // 检验是否是本人的文章
        if (user.getUserId() != blog.getUserId()) {
            resp.getWriter().write("<h3>你没有权限删除别人的文章.</h3>");
            return;
        }
        // 真正的删除
        blogDao.delete(blogId);
        resp.sendRedirect("blog_list.html");
    }
}
