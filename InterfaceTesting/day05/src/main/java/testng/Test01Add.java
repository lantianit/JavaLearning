package testng;

import org.testng.annotations.Test;

public class Test01Add {
    // 1+1=2
    @Test
    public void test01Add() {
        int result1 = CalUtil.add(1, 1);
        System.out.println("result1=" + result1);
    }

    // 10+20=30
    @Test
    public void test02Add() {
        int result2 = CalUtil.add(10, 20);
        System.out.println("result2=" + result2);
    }

    // 100+200=300
}
