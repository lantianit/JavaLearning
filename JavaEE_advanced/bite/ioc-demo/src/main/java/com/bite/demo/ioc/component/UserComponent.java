package com.bite.demo.ioc.component;

import org.springframework.stereotype.Component;

@Component("userComponent")
public class UserComponent {
    public void sayHi(){
        System.out.println("hello, UserComponent...");
    }
}
