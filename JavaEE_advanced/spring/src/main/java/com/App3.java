package com;

import com.demo.component.AComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App3 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        AComponent aComponent = context.getBean("AComponent", AComponent.class);

    }
}
