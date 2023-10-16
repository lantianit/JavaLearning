package com.example.demo;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Order(5)
//@Component
public class MyService {
    public MyService(){
        System.out.println("Do MyService()");
    }
}
