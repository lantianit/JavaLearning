package com.demo.service;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public void sayHi() {
        System.out.println("Hi，Service。");
    }
}
