package com.demo.component;

import com.demo.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class StudentBeans {

    //    @Bean(name = {"s1", "s2"})
    @Bean
    public Student student1() {
        // 伪代码，构建对象
        Student stu = new Student();
        stu.setId(1);
        stu.setName("张三");
        stu.setAge(18);
        return stu;
    }

    @Bean
    public Student student2() {
        // 伪代码，构建对象
        Student stu = new Student();
        stu.setId(2);
        stu.setName("李四");
        stu.setAge(20);
        return stu;
    }

}
