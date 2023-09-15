package HttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


@WebServlet("/HttpServletRequest.ShowRequest")
public class ShowRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        StringBuilder stringBuilder = new StringBuilder();
        // 返回请求协议的名称和版本号
        stringBuilder.append(req.getProtocol()); // 协议名称: HTTP  版本号: 1.1
        stringBuilder.append("\n");
//        stringBuilder.append("<br>");

        // 放回请求的HTTP方法名称
        stringBuilder.append(req.getMethod()); // GET
        stringBuilder.append("\n");
//        stringBuilder.append("<br>");

        // 从协议名称知道HTTP请求的第一行的查询字符串中, 返回该请求的URL的一部分
        stringBuilder.append(req.getRequestURI()); // /3010/HttpServletRequest.ShowRequest
        stringBuilder.append("\n");
//        stringBuilder.append("<br>");

        stringBuilder.append(req.getRequestURL()); // 返回整个地址 http://127.0.0.1:8080/0310/ShowRequest
//        stringBuilder.append("<br>");
        stringBuilder.append("\n");

        // 返回指示请求上下文的请求URI部分
        stringBuilder.append(req.getContextPath()); // /3010 -
        stringBuilder.append("\n");
//        stringBuilder.append("<br>");

        // 返回包含路径后的请求URL中的查询字符串
        stringBuilder.append(req.getQueryString()); // null 没有QueryString
        stringBuilder.append("\n");
//        stringBuilder.append("<br>");

        // 把请求的header 拼接
        // 获取到所有的headerNames
        // 枚举
        Enumeration<String> headerNames = req.getHeaderNames();
        // has XXX 判断是否有下一个
        while(headerNames.hasMoreElements()) {
            // 遍历获取到每一个header的name值
            String name = headerNames.nextElement(); // next XXX获取下一个
            // 查询header中name对应的值
            String value = req.getHeader(name);
            stringBuilder.append(name + ", " + value);
            stringBuilder.append("\n");

        }
        resp.getWriter().write(stringBuilder.toString());
    }
}