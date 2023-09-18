package com.bitejiuyeke.servlet;

import com.bitejiuyeke.model.Blog;
import com.bitejiuyeke.model.BlogDao;
import com.bitejiuyeke.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * 博客信息
 *
 * @Author 比特就业课
 * @Date 2022-07-06
 */
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    // Jackson实例，用来对象与Json字符串的转换
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码格式，防止乱码
        resp.setContentType("application/json; charset=utf-8");
        // 检查用户的登录状态
        User user = Utils.checkLogin(req);
        if (user == null) {
            // 用ajax的方式请求，服务器的sendRedirect对于页面是无效的
//            resp.sendRedirect("blog_login.html");
            // 我们用一个状态码来判断页面是否跳转
            resp.setStatus(403);
            return;
        }
        // 1. 获取参数
        String blogIdStr = req.getParameter("blogId");
        BlogDao blogDao = new BlogDao();

        if (blogIdStr == null || blogIdStr.equals("")) {
            // 查询所有的博客
            List<Blog> blogList = blogDao.selectAll();
            // 转换为Json后返回
            String result = objectMapper.writeValueAsString(blogList);
            resp.getWriter().write(result);
        } else {
            // 查询指定的博客
            Blog blog = blogDao.selectOne(Integer.parseInt(blogIdStr));
            // 转换为Json后返回
            String result = objectMapper.writeValueAsString(blog);
            resp.getWriter().write(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        // 1. 检查用户是否登录
        User user = Utils.checkLogin(req);
        if (user == null) {
            resp.sendRedirect("blog_login.html");
            return;
        }
        // 2. 接收参数并校验
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (title == null || title.equals("") || content == null || content.equals("")) {
            resp.getWriter().write("<h3>标题和博客内容不能为空.</h3>");
            return;
        }
        // 3. 构造Blog对象并写入数据库
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        // 作者就是当前的登录用户，所以用从Session中获取到的用户信息进行赋值
        blog.setUserId(user.getUserId());
        // 写入数据库
        BlogDao blogDao = new BlogDao();
        blogDao.insert(blog);
        // 4. 成功后跳转到博客列表
        resp.sendRedirect("blog_list.html");
    }
}
