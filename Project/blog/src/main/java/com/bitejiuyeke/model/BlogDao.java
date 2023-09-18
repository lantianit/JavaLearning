package com.bitejiuyeke.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类提供数据库操作的方法
 *
 * @Author 比特就业课
 * @Date 2022-07-05
 */
public class BlogDao {
    // 1. 新增一篇博客，写入数据库
    public void insert (Blog blog) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 获取数据库连接
            connection = DBUtils.getConnection();
            // 构造SQL语句
            String sql = "insert into blog values (null, ?, ?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getContent());
            statement.setTimestamp(3, blog.getPostTime());
            statement.setInt(4, blog.getUserId());
            // 执行SQL
            int result =statement.executeUpdate();
            if (result != 1) {
                System.out.println("博客写入失败！");
            } else {
                System.out.println("博客写入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(connection, statement, null);
        }
    }

    // 2. 查找博客列表（所有的博客）
    public List<Blog> selectAll () {
        List<Blog> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // 获取数据库连接
            connection = DBUtils.getConnection();
            // 构造SQL语句
            String sql = "select * from blog order by postTime desc;";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // 把查询结果封装成Blog对象
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                String content = resultSet.getString("content");
                // 只保留前100个字符
                if (content != null && content.length() > 100) {
                    blog.setContent(content.substring(0, 100) + "...");
                } else {
                    blog.setContent(content);
                }

                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                // 添加到集合中
                result.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            DBUtils.close(connection, statement, resultSet);
        }
        // 返回结果
        return result;
    }

    // 3. 查找一篇博客
    public Blog selectOne (int blogId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtils.getConnection();
            // 构造SQL语句，按blogId来查
            String sql  = "select * from blog where blogId = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, blogId);
            resultSet = statement.executeQuery();

            // 根据返回结果构建Blog对象
            if (resultSet.next()) {
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                // 返回查询到的对象
                return blog;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(connection, statement, resultSet);
        }
        // 出现异常时会返回null
        return null;
    }

    // 4. 删除一篇博客，添加一个删除按钮
    public void delete (int blogId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBUtils.getConnection();
            // 删除博客的SQL语句
            String sql = "delete from blog where blogId = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, blogId);
            int result = statement.executeUpdate();
            if (result != 1) {
                System.out.println("删除失败！");
            } else {
                System.out.println("删除成功！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(connection, statement, null);
        }
    }
    // 5. 更新一篇博客，修改以前发布过的博客

    public static void main(String[] args) {
        BlogDao blogDao = new BlogDao();

        // 测试写入
        Blog blog = new Blog();
        blog.setTitle("新的博客标题2");
        blog.setContent("新的博客内容2");
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        blog.setUserId(2);
        blogDao.insert(blog);

        // 测试查询所有
//        List<Blog> blogs = blogDao.selectAll();
//        System.out.println(blogs);

        // 测试查询某一个Blog对象
//        Blog blog = blogDao.selectOne(3);
//        System.out.println(blog);

        // 测试删除
//        blogDao.delete(2);
//        List<Blog> blogs = blogDao.selectAll();
//        System.out.println(blogs);
    }
}
