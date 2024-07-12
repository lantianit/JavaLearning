package com.bite.demo.ioc;

import com.bite.demo.ioc.component.UserComponent;
import com.bite.demo.ioc.config.UserConfig;
import com.bite.demo.ioc.controller.UserController;
import com.bite.demo.ioc.controller.UserController2;
import com.bite.demo.ioc.model.UserInfo;
import com.bite.demo.ioc.repo.UserRepository;
import com.bite.demo.ioc.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.bite.demo.ioc")
@SpringBootApplication
public class IocDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(IocDemoApplication.class, args);
//		UserController bean = context.getBean(UserController.class);
//		bean.sayHi();

		UserController2 bean2 = context.getBean(UserController2.class);
		bean2.sayHi();

//		context.getBean();
//		System.out.println(Introspector.decapitalize("UController"));
//		UserService userService = (UserService) context.getBean("userService");
//		userService.sayHi();
//
//		UserComponent userComponent = context.getBean("userComponent", UserComponent.class);
//		userComponent.sayHi();
//
//		UserRepository userRepository = (UserRepository) context.getBean("userRepository");
//		userRepository.sayHi();
//
//		UserConfig userConfig = context.getBean(UserConfig.class);
//		userConfig.sayHi();
//
//		UserInfo u1 = context.getBean("u1",UserInfo.class);
//		System.out.println(u1);
//		UserInfo u2 = context.getBean("u2",UserInfo.class);
//		System.out.println(u2);
	}

}
