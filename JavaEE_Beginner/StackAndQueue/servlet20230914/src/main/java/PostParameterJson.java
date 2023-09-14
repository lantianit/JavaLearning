import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/postParameterJson")
public class PostParameterJson extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String body = readBody(req);
        resp.getWriter().write(body);
   }
    private String readBody(HttpServletRequest req) throws IOException {
        int contentLength = req.getContentLength();
        byte[] buffer = new byte[contentLength];
        InputStream inputStream = req.getInputStream();
        inputStream.read(buffer);
        return new String(buffer, "utf-8");
   }
}