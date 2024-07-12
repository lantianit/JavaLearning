package com.bite.aop.component;

import org.springframework.stereotype.Component;

@Component
public class UserComponent implements Iface{


    public void say() {
        System.out.println("say...");
    }
}
