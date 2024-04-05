package com.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BComponent {

    @Autowired
    private CComponent component;

    @PostConstruct
    public void postConstruct() {
        System.out.println("执行了 B 对象的 PostConstruct 方法");
    }

    public void sayHi() {
        System.out.println("hi,BComponent~");
    }

}
