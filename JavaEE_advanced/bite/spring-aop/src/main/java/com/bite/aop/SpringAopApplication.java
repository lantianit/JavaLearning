package com.bite.aop;

import com.bite.aop.component.Iface;
import com.bite.aop.component.UserComponent;
import com.bite.aop.component.UserComponent2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringAopApplication.class, args);
		//SpringBoot 默认情况下, 接口类和普通类都可以被正确代理
//		UserComponent bean = context.getBean(UserComponent.class);
//		System.out.println(bean.getClass().toString());
//
//		UserComponent2 bean2 = (UserComponent2) context.getBean("userComponent2");
//		System.out.println(bean2.getClass().toString());

		//设置JDK代理
//		UserComponent bean = context.getBean(UserComponent.class);
//		System.out.println(bean.getClass().toString());// JDK不能正常代理

		Iface bean = (Iface) context.getBean("userComponent");
		System.out.println(bean.getClass().toString());   //JDK可以正常代理
//
//		UserComponent2 bean2 = (UserComponent2) context.getBean("userComponent2");
//		System.out.println(bean2.getClass().toString()); // CGLib代理

	}

}
