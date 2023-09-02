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
    public static void main(String[] args) throws CloneNotSupportedException {
        A a1 = new A(10);
        A a2 = (A) a1.clone();
    }
}