import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    // 这个对象在多个方法中都需要使用
    private ObjectMapper objectMapper = new ObjectMapper();

    // 负责让页面获取到数据
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 显式声明当前的响应数据格式 不要让客户端去猜!!!
        resp.setContentType("application/json; charset=utf8");
        // 把 messageList 转成 json 字符串, 并且返回给页面就行了.
        List<Message> messageList = null;
        try {
            messageList = load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.getWriter().write(objectMapper.writeValueAsString(messageList));
    }

    // 提交数据
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取到 body 中的数据并解析
        Message message = objectMapper.readValue(req.getInputStream(), Message.class);
        // 把这个 message 保存一下. 简单的办法就是保存在内存中.
        // messageList.add(message);
        try {
            save(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setStatus(200);

        System.out.println("提交数据成功: from=" + message.getFrom()
                + ", to=" + message.getTo() + ", message=" + message.getMessage());
    }

    private List<Message> load() throws SQLException {
        // 从数据库查询数据

        // 1. 先有一个数据源
        DataSource dataSource = DBUtil.getDataSource();

        // 2. 建立连接
        Connection connection = dataSource.getConnection();

        // 3. 构造 SQL
        String sql = "select * from messages";
        PreparedStatement statement = connection.prepareStatement(sql);

        // 4. 执行 SQL
        ResultSet resultSet = statement.executeQuery();

        // 5. 遍历结果集合
        List<Message> messageList = new ArrayList<>();
        while (resultSet.next()) {
            Message message = new Message();
            message.setFrom(resultSet.getString("from"));
            message.setTo(resultSet.getString("to"));
            message.setMessage(resultSet.getString("message"));
            messageList.add(message);
        }

        // 6. 关闭连接
        statement.close();
        connection.close();
        return messageList;
    }

    private void save(Message message) throws SQLException {
        // 把数据保存到数据库中

        // 1. 先有一个数据源
        DataSource dataSource = DBUtil.getDataSource();

        // 2. 建立连接
        Connection connection = dataSource.getConnection();

        // 3. 构造 SQL
        String sql = "insert into messages values(?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, message.getFrom());
        statement.setString(2, message.getTo());
        statement.setString(3, message.getMessage());

        // 4. 执行 SQL
        int ret = statement.executeUpdate();
        System.out.println("ret = " + ret);

        // 5. 关闭连接
        statement.close();
        connection.close();
    }
}
