package com.bitejiuyeke.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户表的增删改查
 * 1. 新增用户，相当于“注册”需要涉及到的功能，我们这个练习里不实现这个功能
 * 2. 删除用户，相当于“删号”涉及的功能，我们不实现
 * 3. 修改用户，是对用户“个人信息”的修改功能，我们不实现
 * 4. 这里主要实现查找功能
 *
 * @Author 比特就业课
 * @Date 2022-07-05
 */
public class UserDao {
    // 根据userId来查找用户(根据博客表中的userId来查找用户信息)
    public User selectById (int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtils.getConnection();
            // SQL语句
            String sql = "select * from user where userId = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            // 根据查询结果构造User对象
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(connection, statement, resultSet);
        }
        // 如果有异常就会返回null
        return null;
    }

    // 根据用户名来查找，这里主要用来验证用户登录
    public User selectByName (String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtils.getConnection();
            String sql = "select * from user where username = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(connection, statement, resultSet);
        }
        return null;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        // 测试按userId查询
        User user = userDao.selectById(1);
        System.out.println(user);

        // 测试按username查询
        User user1 = userDao.selectByName("admin");
        System.out.println(user1);
    }

}
