package jdbc;

import org.testng.annotations.Test;

import java.sql.*;

/**
 * 代码操作数据库在接口自动化测试中的应用场景：
 * 1.数据构造（数据准备）
 * 2.数据校验
 * 3.数据清理
 */
public class TestJDBC {
    // 获取数据库连接
    @Test
    public void test01GetConn() throws ClassNotFoundException, SQLException {
        // 加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 获取数据库连接
        String url = "jdbc:mysql://211.103.136.244:7061/test_db?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        Connection conn = DriverManager.getConnection(url, "student", "iHRM_student_2021");
        System.out.println(conn);

        // 获取操作数据库的Statement对象
        // 执行SQL

        // 释放资源（关闭JDBC对象）
        conn.close();
    }

    // 获取Statement对象
    @Test
    public void test02Statement() throws ClassNotFoundException, SQLException {
        // 加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 获取数据库连接
        String url = "jdbc:mysql://211.103.136.244:7061/test_db?useUnicode=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "student", "iHRM_student_2021");
        System.out.println(conn);

        // 获取操作数据库的Statement对象
        Statement statement = conn.createStatement();

        // 执行SQL
        //String sql = "INSERT INTO t_book(id,title,pub_date) VALUES(4,'西游记','1986-01-01');";
        String bookName = "西游记";
        String sql = "INSERT INTO t_book(title,pub_date) VALUES('" + bookName + "','1986-01-01');";
        statement.execute(sql);

        // 释放资源（关闭JDBC对象）
        statement.close();
        conn.close();
    }


    // 获取PreparedStatement对象
    @Test
    public void test03PreparedStatement() throws ClassNotFoundException, SQLException {
        // 加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 获取数据库连接
        String url = "jdbc:mysql://211.103.136.244:7061/test_db?useUnicode=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "student", "iHRM_student_2021");
        System.out.println(conn);

        // 获取操作数据库的PreparedStatement对象
        String sql = "INSERT INTO t_book(id,title,pub_date) VALUES(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        // 注意：索引从1开始的
        ps.setInt(1, 4);
        ps.setString(2, "西游记");
        ps.setString(3, "1986-01-01");

        // 执行SQL
        int i = ps.executeUpdate();
        System.out.println("i====" + i);

        // 释放资源（关闭JDBC对象）
        ps.close();
        conn.close();
    }


    // 获取查询结果集
    @Test
    public void test04ResultSet() throws ClassNotFoundException, SQLException {
        // 加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 获取数据库连接
        String url = "jdbc:mysql://211.103.136.244:7061/test_db?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        Connection conn = DriverManager.getConnection(url, "student", "iHRM_student_2021");

        // 获取操作数据库的PreparedStatement对象
        String sql = "select title,id,pub_date from t_book";
        PreparedStatement ps = conn.prepareStatement(sql);

        // 执行SQL
        ResultSet rs = ps.executeQuery();
        // 循环获取结果集数据
//        for(;rs.next();){
//        }
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            Date pub_date = rs.getDate("pub_date");
            System.out.println("id===" + id);
            System.out.println("title===" + title);
            System.out.println("==============================");
        }

        // 释放资源（关闭JDBC对象）
//        rs.close();
//        ps.close();
//        conn.close();
//        DBUtil.closeResultSet(rs);
//        DBUtil.closeStatement(ps);
//        DBUtil.closeConn(conn);
        DBUtil.close(conn, ps, rs);
    }


    // 事务操作
    @Test
    public void test04Transaction() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 获取数据库连接
            String url = "jdbc:mysql://211.103.136.244:7061/test_db?useUnicode=true&characterEncoding=UTF-8";
            conn = DriverManager.getConnection(url, "student", "iHRM_student_2021");

            // 关闭自动提交事务
            conn.setAutoCommit(false);

            // 获取操作数据库的PreparedStatement对象
            String sql = "INSERT INTO t_book(id,title,pub_date) VALUES(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 4);
            ps.setString(2, "西游记");
            ps.setString(3, "1986-01-01");
            ps.executeUpdate();

            // 模拟抛出异常
//            "".charAt(6);

            // 插入英雄人物
            String sql2 = "INSERT INTO t_hero(name,gender,book_id) VALUES('孙悟空',1, 4);";
            ps2 = conn.prepareStatement(sql2);
            ps2.executeUpdate();

            // 提交事务
            conn.commit();
        } catch (Exception e) {
            // 事务回滚
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            // 释放资源（关闭JDBC对象）
            DBUtil.close(ps);
            DBUtil.close(conn, ps2, null);
        }
    }


}
