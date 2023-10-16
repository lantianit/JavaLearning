package com.example.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/sayhi")
    public String sayHi(){
        String result = "Hi, user service.";
        System.out.println(result);
        return result;
    }

}
