package com.demo.controller;

import com.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * author:李四
 */
@Controller
public class UserController {

    @Autowired
    private User user1;

    public void getUser() {
        System.out.println("User1：" + user1);
        User u = user1;
        u.setName("李四");
        System.out.println("u：" + u);
    }

}
