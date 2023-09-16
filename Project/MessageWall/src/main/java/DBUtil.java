import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

// 使用这个类来封装 DataSource 的单例
public class DBUtil {
    private static volatile DataSource dataSource = null;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/MessageWall?characterEncoding=utf8&useSSL=false");
                    ((MysqlDataSource)dataSource).setUser("root");
                    // 我这里的数据库是没密码的.
                    ((MysqlDataSource)dataSource).setPassword("123456");
                }
            }
        }
        return dataSource;
    }

    private DBUtil() {}
}
