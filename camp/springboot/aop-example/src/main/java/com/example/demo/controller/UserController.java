package com.example.demo.controller;

import com.example.demo.service.IUserService;
import com.example.demo.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
//@Conditional(value = {UserController.class})
public class UserController {
//    @Autowired
//    private IUserService userService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/sayhi")
    public void sayHi() {
        userService.sayHi();
    }
}
