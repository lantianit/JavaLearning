package com.bite.aop.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        //静态代理
//        HouseSubject houseSubject = new HouseProxy(new RealHouseSubject());
//        houseSubject.rentHouse();
//        houseSubject.saleHouse();
        //JDK动态代理
        //目标类
//        HouseSubject target = new RealHouseSubject();
//        //生成代理对象
//        HouseSubject proxy = (HouseSubject) Proxy.newProxyInstance(
//                target.getClass().getClassLoader(),
//                new Class[]{HouseSubject.class},
//                new JDKInvocationHandler(target));
//
//        proxy.rentHouse();
//        proxy.saleHouse();
        //使用JDK测试普通类代理, 会报错
//        RealHouseSubject2 target = new RealHouseSubject2();
//        RealHouseSubject2 proxy = (RealHouseSubject2) Proxy.newProxyInstance(
//        target.getClass().getClassLoader(),
//        new Class[]{RealHouseSubject2.class},
//        new JDKInvocationHandler(target));
//
//        proxy.rentHouse();
//        proxy.saleHouse();

        //CGlib动态代理
//        HouseSubject target = new RealHouseSubject();
//        HouseSubject proxy = (HouseSubject) Enhancer.create(
//                target.getClass(),
//                new CGLibMethodInterceptor(target));
//        proxy.rentHouse();
//        proxy.saleHouse();
        //CGLib代理非接口类
        RealHouseSubject2 target = new RealHouseSubject2();
        RealHouseSubject2 proxy = (RealHouseSubject2) Enhancer.create(
                target.getClass(),
                new CGLibMethodInterceptor(target));
        proxy.rentHouse();
        proxy.saleHouse();
    }
}
