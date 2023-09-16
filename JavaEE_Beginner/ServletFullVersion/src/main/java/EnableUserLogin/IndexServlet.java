package EnableUserLogin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/index")
public class IndexServlet extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     resp.setContentType("text/html; charset=utf-8");
     // 1. 判定当前用户是否已经登陆
     HttpSession session = req.getSession(false);
     if (session == null) {
         // 用户没有登陆, 重定向到 login.html
         resp.sendRedirect("login.html");
         return;
      }
     // 2. 如果已经登陆, 则从 Session 中取出访问次数数据
     String userName = (String)session.getAttribute("username");
     String countString = (String)session.getAttribute("loginCount");
     int loginCount = Integer.parseInt(countString);
     loginCount += 1;
     session.setAttribute("loginCount", loginCount + "");
     // 3. 展示到页面上.
     StringBuilder html = new StringBuilder();
     html.append(String.format("<div>用户名: %s</div>", userName));
     html.append(String.format("<div>loginCount: %d</div>", loginCount));
     resp.getWriter().write(html.toString());
     }
}