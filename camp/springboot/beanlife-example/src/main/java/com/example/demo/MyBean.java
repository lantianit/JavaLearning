package com.example.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MyBean implements InitializingBean, BeanNameAware {
    /**
     * 构造方法
     */
    public MyBean() {
        System.out.println("执行构造方法");
    }

    @Autowired
    private Person person;

    /**
     * 初始化方法
     */
    @PostConstruct
    public void postConstruct() {
        System.out.println("执行初始化方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Do afterPropertiesSet().");
    }

    public void sayHello() {
        System.out.println("Hello, lei.");
    }

    /**
     * 销毁方法
     */
    @PreDestroy
    public void preDestroy() {
        System.out.println("Do preDestroy().");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name Aware -> " + name);
    }
}
