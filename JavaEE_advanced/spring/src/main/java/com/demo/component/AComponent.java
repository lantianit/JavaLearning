package com.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AComponent {

    @Autowired
    private BComponent bComponent; // ①

    @PostConstruct // ②
    public void postConstruct() {
        bComponent.sayHi(); // 先 ① 再 ② 不会有问题，但如果先 ② 再 ① 就会空指针报错
        System.out.println("执行了 A 对象的 PostConstruct 方法");
    }

}
