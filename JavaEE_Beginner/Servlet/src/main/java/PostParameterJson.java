import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

// 创建一个新的类表示 JSON 数据, 属性的名字需要和 JSON 字符串中的 key 一致.
class JsonData {
    public String userId;
    public String classId;
}
@WebServlet("/postParameterJson")
public class PostParameterJson extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String body = readBody(req);
        // 创建 ObjectMapper 对象. 这个是 Jackson 中的核心类. 
        ObjectMapper objectMapper = new ObjectMapper();
        // 通过 readValue 方法把 body 这个字符串转成 JsonData 对象
        JsonData jsonData = objectMapper.readValue(body, JsonData.class);
        resp.getWriter().write("userId: " + jsonData.userId + ", " + "classId: "
+ jsonData.classId);
   }
    private String readBody(HttpServletRequest req) throws IOException {
        int contentLength = req.getContentLength();
        byte[] buffer = new byte[contentLength];
        InputStream inputStream = req.getInputStream();
        inputStream.read(buffer);
        return new String(buffer, "utf-8");
   }
}