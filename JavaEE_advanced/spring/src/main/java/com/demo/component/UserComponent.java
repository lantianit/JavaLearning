package com.demo.component;

import org.springframework.stereotype.Service;

@Service
public class UserComponent {
    public String sayHi() {
        return "Hi,@Component.";
    }
}
