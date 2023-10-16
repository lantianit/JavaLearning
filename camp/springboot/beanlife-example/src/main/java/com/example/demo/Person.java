package com.example.demo;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class Person {
    public Person() throws InterruptedException {
        System.out.println("执行 Person 构造方法");
        Thread.sleep(3000);
        System.out.println("执行 Person 构造方法 -> END");
    }
}
