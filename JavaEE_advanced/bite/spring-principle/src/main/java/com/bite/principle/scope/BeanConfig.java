package com.bite.principle.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class BeanConfig {

    @Bean
    public Dog dog(){
        Dog dog = new Dog("zhangsan");
        return dog;
    }

    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    public Dog singleDog(){
        return new Dog();
    }

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean
    public Dog prototypeDog(){
        return new Dog();
    }

    @RequestScope
    @Bean
    public Dog requestDog(){
        return new Dog();
    }

    @SessionScope
    @Bean
    public Dog sessionDog(){
        return new Dog();
    }

    @ApplicationScope
    @Bean
    public Dog applicationDog(){
        return new Dog();
    }

}
