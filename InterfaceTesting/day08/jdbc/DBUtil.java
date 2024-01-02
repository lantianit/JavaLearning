package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 数据库操作工具类
public class DBUtil {

    // 获取数据库连接对象
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        // 加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 获取数据库连接
        String url = "jdbc:mysql://211.103.136.244:7061/test_db?useUnicode=true&characterEncoding=UTF-8";
        return DriverManager.getConnection(url, "student", "iHRM_student_2021");
    }

    // 查询一条数据--返回list
    // select id,title,pub_date from t_book where id=1
    public static List<Object> getOne(String sql) throws SQLException, ClassNotFoundException {
        List<Object> result = null;
        Connection conn = getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = new ArrayList<>();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            System.out.println("columnCount===" + columnCount);
            for (int i = 1; i <= columnCount; i++) {
                result.add(rs.getObject(i));
            }
        }
        close(conn, ps, rs);
        return result;
    }

    // 查询一条数据--返回map
    // select id,title,pub_date from t_book where id=1
    // {"id":1, "title":"xxxx", "pub_date":"xxx"}
    public static Map<String, Object> getOne2(String sql) throws SQLException, ClassNotFoundException {
        Map<String, Object> result = null;
        Connection conn = getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = new HashMap<>();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            System.out.println("columnCount===" + columnCount);
            for (int i = 1; i <= columnCount; i++) {
                String columnLabel = metaData.getColumnLabel(i);
                Object value = rs.getObject(i);
                result.put(columnLabel, value);
            }
        }
        close(conn, ps, rs);
        return result;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<Object> one = getOne("select id,title,pub_date from t_book where id=1");
        System.out.println("one====" + one);

        Map<String, Object> one2 = getOne2("select id,title,pub_date from t_book where id=1");
        System.out.println("one2====" + one2);
    }


    // 关闭JDBC对象
    public static void close(Connection conn, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(conn);
    }

    // 关闭数据库连接对象
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭Statement对象
    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 关闭ResultSet对象
    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
