package com.demo.controller;

import com.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {

    // 1.使用属性注入的方式获取 Bean
//    @Autowired
//    @Resource
//    private StudentService studentService;

//    // 2.set 注入
//    private StudentService studentService;
//
//    @Autowired
//    public void setStudentService(StudentService studentService) {
//        this.studentService = studentService;
//    }

//    // 3.构造方法注入
//    private final StudentService studentService;
//
//    @Autowired
////    @Resource
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//
//
//
//    public void sayHi() {
//        // 调用 service 方法
//        studentService.sayHi();
//    }

    //    @Resource(name = "student1")
    @Autowired
    @Qualifier("student2")
    private Student student;

    public void sayHi() {
        System.out.println(student.toString());
    }


}
