package com.example.mq.study;

class Add {
    
    public Integer id;
    
    public Add set(Integer id) {
        this.id = id;
        return this;
    }
    
    public Add add(Integer num) {
        this.id += num;
        return this;
    }

    public static void main(String[] args) {
        Add a = new Add();
        a.set(10).add(5);
        System.out.println(a.id);
    }
    
}