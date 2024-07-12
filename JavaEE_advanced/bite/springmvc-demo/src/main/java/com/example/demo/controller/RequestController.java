package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@RestController
@RequestMapping("/request")
public class RequestController {
    @RequestMapping("/getCookie")
    public String getCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

//        Arrays.stream(cookies).forEach(x-> System.out.println(x.getName()+":"+x.getValue()));
        if (cookies!=null){
            for (Cookie c: cookies){
                System.out.println(c.getName()+":"+c.getValue());
            }
            return "获取Cookie成功";
        }
        return "Cookie 为空";
    }

    @RequestMapping("/getCookie2")
    public String getCookie2(@CookieValue("bite") String bite){
        return "bite:"+bite;
    }

    @RequestMapping("/setSession")
    public String setSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userName","zhangsan");
        return "设置Session成功";
    }

    @RequestMapping("/getSession")
    public String getSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("userName");
        return "登录用户:"+userName;
    }

    @RequestMapping("/getSession2")
    public String getSession(HttpSession session){
        String userName = (String)session.getAttribute("userName");
        return "登录用户:"+userName;
    }

    @RequestMapping("/getSession3")
    public String getSession(@SessionAttribute(value = "userName", required = false) String userName){
        return "登录用户:"+userName;
    }

    @RequestMapping("/getHeader")
    public String getHeader(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        return "userAgent:"+userAgent;
    }

    @RequestMapping("/getHeader2")
    public String getHeader2(@RequestHeader("User-Agent") String userAgent){
        return "userAgent:"+userAgent;
    }
}
