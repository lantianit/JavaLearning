package com.bite.demo.ioc.controller;

import com.bite.demo.ioc.component.UserComponent;
import com.bite.demo.ioc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    //把userService 注入进来
    //属性注入
    @Autowired
    private UserService userService;

    @Autowired
    private UserComponent userComponent;

    /**
     * 以下为构造函数注入
     */
//    private UserService userService;
//
//    private UserConfig userConfig;
//
//    public UserController() {
//    }
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Autowired
//    public UserController(UserService userService, UserConfig userConfig) {
//        this.userService = userService;
//        this.userConfig = userConfig;
//    }
    /**
     * 以下为setter方法注入
     */
//    private UserService userService;
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    public void sayHi(){
        System.out.println("hello, UserController...");
        userService.sayHi();
        userComponent.sayHi();
    }
}
