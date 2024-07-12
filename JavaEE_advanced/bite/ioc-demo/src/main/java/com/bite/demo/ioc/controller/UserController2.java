package com.bite.demo.ioc.controller;

import com.bite.demo.ioc.component.UserComponent;
import com.bite.demo.ioc.model.UserInfo;
import com.bite.demo.ioc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController2 {
//    @Qualifier("u1")
//    @Autowired
//    private UserInfo userInfo;

    @Resource(name = "u1")
    private UserInfo userInfo;

    public void sayHi(){
        System.out.println("hello, UserController...");
        System.out.println(userInfo);
    }
}
