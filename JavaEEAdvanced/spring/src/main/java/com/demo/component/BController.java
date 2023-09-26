package com.demo.component;

import org.springframework.stereotype.Controller;

@Controller
public class BController {
    public String sayHi() {
        return "Hi,Controler.";
    }
}
