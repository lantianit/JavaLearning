package com.example.mq.study;

class Demo03String {
    public static void main(String[] args) {
        String s1 = new String();
        System.out.println(s1);
        String s2 = new String("abc");
        System.out.println(s2);
        char[] chars = {'a','b','c'};
        String s3 = new String(chars);
        System.out.println(s3);
        byte[] bytes1 = {97,98,99};
        String s4 = new String(bytes1);
        System.out.println(s4);
        byte[] bytes2 = {-97,-98,-99};
        String s5 = new String(bytes2);
        System.out.println(s5);
        byte[] bytes3 = {-28,-67,-96};
        String s6 = new String(bytes3);
        System.out.println(s6);
        //5.简化形式
        String s7 = "abc";
        System.out.println(s7);
    }
}