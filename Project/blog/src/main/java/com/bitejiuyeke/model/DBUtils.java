package com.bitejiuyeke.model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库连接的单例类
 *
 * @Author 比特就业课
 * @Date 2022-07-05
 */
public class DBUtils {
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/BlogSystem?characterEncoding=utf8&useSSL=false";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123456";

    // 这里用饿汉模式，用类对象来保证唯一实例
    private static DataSource dataSource = new MysqlDataSource();
    // 在静态代码块里给DataSource初始化
    static {
        ((MysqlDataSource)dataSource).setURL(URL);
        ((MysqlDataSource)dataSource).setUser(USERNAME);
        ((MysqlDataSource)dataSource).setPassword(PASSWORD);
    }

    // 对外提供一个公开的方法，用来获取数据库连接
    public static Connection getConnection () throws SQLException {
        return dataSource.getConnection();
    }

    // 关闭资源
    public static void close (Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
