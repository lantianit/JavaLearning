package com.demo.component;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CComponent {
    @PostConstruct
    public void postConstruct() {
        System.out.println("执行了 C 对象的 PostConstruct 方法");
    }
}
