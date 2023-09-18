package com.bitejiuyeke.servlet;

import com.bitejiuyeke.model.Blog;
import com.bitejiuyeke.model.BlogDao;
import com.bitejiuyeke.model.User;
import com.bitejiuyeke.model.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户信息
 *
 * @Author 比特就业课
 * @Date 2022-07-06
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    // Jackson实例
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        // 检查用户是否登录
        User user = Utils.checkLogin(req);
        if (user == null) {
            resp.setStatus(403);
            return;
        }
        // 获取参数
        String blogIdStr = req.getParameter("blogId");
        String result = null;
        if (blogIdStr == null) {
            // 查询当前登录用户信息
            result = objectMapper.writeValueAsString(user);
            resp.getWriter().write(result);
        } else {
            // 先查询博客信息
            BlogDao blogDao = new BlogDao();
            Blog blog = blogDao.selectOne(Integer.parseInt(blogIdStr));
            // 再根据博客信息查询作者信息
            UserDao userDao = new UserDao();
            User author = userDao.selectById(blog.getUserId());
            // 是否是本人的博客
            author.setIsYourBlog(user.getUserId() == blog.getUserId() ? 1 : 0);
            result = objectMapper.writeValueAsString(author);
            resp.getWriter().write(result);
        }
    }
}
