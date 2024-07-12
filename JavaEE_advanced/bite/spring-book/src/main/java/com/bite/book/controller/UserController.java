package com.bite.book.controller;

import com.bite.book.constonts.Constants;
import com.bite.book.model.UserInfo;
import com.bite.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public boolean login(String userName, String password, HttpSession session){
        //校验参数
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)){
            return false;
        }
        //判断数据库的密码和用户输入的密码是否一致
        //查询数据库, 得到数据库的密码
        UserInfo userInfo = userService.queryByName(userName);
        if (userInfo==null){
            return false;
        }
        if (password.equals(userInfo.getPassword())){
            userInfo.setPassword("");
            //密码正确
            session.setAttribute(Constants.USER_SESSION_KEY, userInfo);
            return true;
        }
        return false;
    }
}
