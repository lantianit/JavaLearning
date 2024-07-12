package com.example.demo.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/login")
@RestController
public class LoginController {
    @RequestMapping("/check")
    public boolean check(String userName, String password, HttpSession session){
        System.out.println("接收到参数 userName:"+userName + ",password:"+password);
        //校验账号和密码是否为空
//        if (userName==null || "".equals(userName) || password==null || "".equals(password)){
//            return false;
//        }
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)){
            return false;
        }
        //校验账号和密码是否正确
        //数据模拟
        if ("zhangsan".equals(userName) && "123456".equals(password)){
            session.setAttribute("userName",userName);
            return true;
        }
        return false;
    }

    @RequestMapping("/index")
    public String index(HttpSession session){
        String userName = (String)session.getAttribute("userName");
        return userName;
    }
}
