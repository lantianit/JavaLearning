import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    // 用于保存所有的留言
    private List<Message> messages = new ArrayList<Message>();
    // 用于转换 JSON 字符串
    private ObjectMapper objectMapper = new ObjectMapper();
    // 获取所有留言
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String respString = objectMapper.writeValueAsString(messages);
        resp.getWriter().write(respString);
   }
    // 新增留言
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        Message message = objectMapper.readValue(req.getInputStream(), 
Message.class);
        messages.add(message);
        resp.getWriter().write("{ \"ok\": 1 }");
   }
}