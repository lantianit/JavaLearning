package com.demo.controller;

import com.demo.model.User;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * author：王五
 */
@Controller
public class UserAdviceController {

    @Resource
    private User user1;

    public void getUser() {
        System.out.println("王五 | user1：" + user1);
    }

}
