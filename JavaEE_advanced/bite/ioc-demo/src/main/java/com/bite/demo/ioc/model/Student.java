package com.bite.demo.ioc.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "student")
@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;
}
