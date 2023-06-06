package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
@ResponseBody // 用来设置当前类中所有的方法返回的是数据而非页面
public class UserController {

    // 1.得到日志对象
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/hi")
    public String sayHi() {
        // 写日志
        logger.trace("我是 trace");
        logger.debug("我是 debug");
        logger.info("我是 info");
        logger.warn("我是 warn");
        logger.error("我是 error");
        System.out.println("我是 System.");
        return "Hi,Spring Boot.";
    }

}