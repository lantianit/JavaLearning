package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/resp")
public class RespController {

    @RequestMapping("/hi")
    public String sayHi() {
        return "/index.html";
    }

}
