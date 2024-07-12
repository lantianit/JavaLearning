package com.bite.demo.ioc.v2;

public class Tire {
    private int size;
    private String color;

    public Tire(int size,String color) {
        System.out.println("tire size:"+size+",color:"+color);
    }
}
