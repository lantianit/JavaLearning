package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

@Controller // 当前类为控制器
@ResponseBody // 返回的是数据，而非页面
@PropertySource(value = "application.properties", encoding = "utf-8")
public class TestController {

    @Value("${mytest}")
    private String mytest;

    @Value("${mytest2}")
    private String mytest2;

    @Value("${myString}")
    private String myString;

    @Value("${myString2}")
    private String myString2;

    @Value("${myString3}")
    private String myString3;

    @Autowired
    private Student student;

    @PostConstruct
    public void postConstruct() {

       
        System.out.println(student);
//        System.out.println("myString:" + myString);
//        System.out.println("myString2:" + myString2);
//        System.out.println("myString3:" + myString3);
    }

    @RequestMapping("/hi") // url 路由注册
    public String sayHi(String name) {
        // 为空为 null，默认值处理
//        if (name == null || name.equals("")) {
        if (!StringUtils.hasLength(name)) {
            name = "张三";
        }
        return "你好：" + name;
    }

    @RequestMapping("/getconf")
    public String getConfig() {
        return "mytest:" + mytest +
                " | mytest2:" + mytest2;
    }

}