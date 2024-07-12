package com.bite.demo.ioc.controller;

import com.bite.demo.ioc.model.Dbtypes;
import com.bite.demo.ioc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/yml")
@RestController
public class YmlController {
    @Value("${string.value2}")
    private String key1;
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${string.str1}")
    private String str1;
    @Value("${string.str2}")
    private String str2;
    @Value("${string.str3}")
    private String str3;

    @RequestMapping("/readValue")
    public String readValue(){
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        return "从yml中读取配置文件, key1:"+key1;
    }
    @RequestMapping("/url")
    public String readUrl(){
        return "从yml中读取配置文件, url:"+url;
    }

    @Autowired
    private Student student;

    @RequestMapping("/readStu")
    public String readStu(){
        return student.toString();
    }

    @Autowired
    private Dbtypes dbtypes;
    @RequestMapping("/types")
    public String readTypes(){
        System.out.println(dbtypes.getName().size());
        return dbtypes.toString();
    }
}
