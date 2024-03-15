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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           resp.setContentType("text/html; charset=utf-8");
           // 1. 获取到用户提交的用户名和密码
           String username = req.getParameter("username");
           String password = req.getParameter("password");
           // 2. 判定用户名密码是否正确
           if (!username.equals("admin") || !password.equals("123")) {
                 // 登陆失败
                 resp.getWriter().write("登陆失败");
                 return;
         }
           // 登陆成功
           System.out.println("登陆成功");
           // 设置 Session
           HttpSession session = req.getSession(true);
           session.setAttribute("username", "admin");
           session.setAttribute("loginCount", "0");
           resp.sendRedirect("index");
   }
}
