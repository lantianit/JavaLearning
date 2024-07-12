package com.bite.aop.proxy;

public class RealHouseSubject2 {

    public void rentHouse() {
        System.out.println("我是房东, 我出租房子");
    }


    public void saleHouse() {
        System.out.println("我是房东, 我出售房子");
    }

}
