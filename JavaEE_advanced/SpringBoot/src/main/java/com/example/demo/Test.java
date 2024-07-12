package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/u")
class t {

    @RequestMapping("/setSession")
    public void test(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username","zhangsan");
        System.out.println("设置session");
        return; 
    }

    @RequestMapping("/getSession")
    public String getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = (String)session.getAttribute("username");
        System.out.println(name);
        return name;
    }

}


