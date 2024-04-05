package com.demo.component;

import com.demo.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 作者：张三
 */
@Component
public class UserBeans {

    @Scope("prototype")
    @Bean
    public User user1() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPassword("123");
        return user;
    }
}
