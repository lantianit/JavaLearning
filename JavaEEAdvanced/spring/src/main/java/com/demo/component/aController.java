package com.demo.component;

import org.springframework.stereotype.Controller;

@Controller
public class aController {
    public String sayHi() {
        return "Hi,Controler.";
    }
}
