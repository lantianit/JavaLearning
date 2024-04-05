package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class CalcController {

    @RequestMapping("/calc")
    public String calc(Integer num1, Integer num2) {
        if (num1 == null || num2 == null) return "参数错误";
        return "结果=" + (num1 + num2);
    }

}
