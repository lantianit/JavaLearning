package com.example.java_gobang.api;

import com.example.java_gobang.model.User;
import com.example.java_gobang.model.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;

@RestController
public class UserAPI {

    @Resource
    private UserMapper userMapper;

    @PostMapping("/login")
    @ResponseBody
    public Object login(String username, String password, HttpServletRequest req) {
        // 关键操作, 就是根据 username 去数据库中进行查询.
        // 如果能找到匹配的用户, 并且密码也一致, 就认为登录成功
        User user = userMapper.selectByName(username);
        System.out.println("[login] username=" + username);
        if (user == null || !user.getPassword().equals(password)) {
            // 登录失败
            System.out.println("登录失败!");
            return new User();
        }
        HttpSession httpSession = req.getSession(true);
        httpSession.setAttribute("user", user);
        return user;
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(String username, String password) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userMapper.insert(user);
            return user;
        } catch (org.springframework.dao.DuplicateKeyException e) {
            User user = new User();
            return user;
        }
    }

    @GetMapping("/userInfo")
    @ResponseBody
    public Object getUserInfo(HttpServletRequest req) {
        try {
            HttpSession httpSession = req.getSession(false);
            User user = (User) httpSession.getAttribute("user");
            // 拿着这个 user 对象, 去数据库中找, 找到最新的数据
            User newUser = userMapper.selectByName(user.getUsername());
            return newUser;
        } catch (NullPointerException e) {
            return new User();
        }
    }
}
