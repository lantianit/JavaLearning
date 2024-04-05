package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class Hello {
    public String sayHi(String name) {
        if (name == null || name.equals("")) {
            name = "张三";
        }
        return name;
    }
}
