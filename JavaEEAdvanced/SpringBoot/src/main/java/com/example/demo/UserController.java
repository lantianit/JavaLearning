package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author zh
 * @Date 2023/7/24 16:55
 * @PackageName:com.example.demo
 * @ClassName: UserController
 * @Description: TODO
 * @Version 1.0
 */

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/hi")
    public String sayHi(){
        return "<h1>Hi<hi>";
    }
}
