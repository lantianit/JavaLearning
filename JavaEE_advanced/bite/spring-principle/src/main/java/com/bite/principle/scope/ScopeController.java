package com.bite.principle.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
public class ScopeController {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private Dog dog;

    @Autowired
    private Dog singleDog;

    @Autowired
    private Dog prototypeDog;
    @Autowired
    private Dog requestDog;
    @Autowired
    private Dog sessionDog;
    @Autowired
    private Dog applicationDog;

//    @RequestMapping("/getDog")
//    public String getDog(){
//        Dog contextDog = context.getBean(Dog.class);
//        Dog contextDog2 = context.getBean(Dog.class);
//        Dog contextDog3 = context.getBean(Dog.class);
//        System.out.println(contextDog);
//        System.out.println(contextDog2);
//        System.out.println(contextDog3);
//        return "dog:"+dog.toString()+",context:"+contextDog.toString();
//    }

    @RequestMapping("/single")
    public String single(){
        Dog contextDog = (Dog)context.getBean("singleDog");
        return "singleDog:"+singleDog.toString()+",context:"+contextDog.toString();
    }

    @RequestMapping("/prototype")
    public String prototype(){
        Dog contextDog = (Dog)context.getBean("prototypeDog");
        return "prototypeDog:"+prototypeDog.toString()+",context:"+contextDog.toString();
    }

    @RequestMapping("/request")
    public String request(){
        Dog contextDog = (Dog)context.getBean("requestDog");
        return "requestDog:"+requestDog.toString()+",context:"+contextDog.toString();
    }

    @RequestMapping("/session")
    public String session(){
        Dog contextDog = (Dog)context.getBean("sessionDog");
        return "sessionDog:"+sessionDog.toString()+",context:"+contextDog.toString();
    }

    @RequestMapping("/application")
    public String application(){
        Dog contextDog = (Dog)context.getBean("applicationDog");
        return "applicationDog:"+applicationDog.toString()+",context:"+contextDog.toString();
    }



}
