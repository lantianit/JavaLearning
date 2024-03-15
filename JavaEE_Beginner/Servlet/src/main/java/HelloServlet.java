import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zh
 * @Date 2023/7/21 14:53
 * @PackageName:PACKAGE_NAME
 * @ClassName: HelloServlet
 * @Description: TODO
 * @Version 1.0
 */

// 这是一个注解
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        System.out.println("Hello World"); // 这是控制台打印

        // 返回到客户端的打印
        // getWriter 会得到一个 Writer 对象
        resp.getWriter().write("Hello World");
    }
}