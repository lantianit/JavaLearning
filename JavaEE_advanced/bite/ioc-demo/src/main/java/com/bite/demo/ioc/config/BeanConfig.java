package com.bite.demo.ioc.config;

import com.bite.demo.ioc.controller.LoggerController;
import com.bite.demo.ioc.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfig {

    private static final Logger logger = LoggerFactory.getLogger(BeanConfig.class);

    @Bean({"u1","u2"})
    public UserInfo userInfo1(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(5);
        userInfo.setName("zhangsan");
        userInfo.setAge(18);
        return userInfo;
    }

//    @Primary
    @Bean
    public UserInfo userInfo2(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(6);
        userInfo.setName("lisi");
        userInfo.setAge(19);
        return userInfo;
    }
}
