package com.example.myblog.controller;

class A {
    public int a;
    public A (int a) {
        this.a = a;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object object = super.clone();
        return object;
    }
}

class B {
    public static void main(String[] args) {
        System.out.println();
    }
}