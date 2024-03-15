package com.example.demo;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (beanName.equals("myBean")) {
            System.out.println("Do postProcessBeforeInitialization ()");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (beanName.equals("myBean")) {
            System.out.println("Do postProcessAfterInitialization()");
        }
        return bean;
    }
}