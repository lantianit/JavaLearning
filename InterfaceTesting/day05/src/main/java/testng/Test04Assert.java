package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test04Assert {
    // 1+1=2
    @Test
    public void test01Add() {
        int result1 = CalUtil.add(1, 1);
        System.out.println("result1=" + result1);
        String s = "123";
        System.out.println(s.charAt());
        // 断言
        Assert.assertEquals(result1, 2);
        Assert.assertNotEquals(1, 2);

        boolean isSuccess = result1 == 2;
        Assert.assertTrue(isSuccess);

        // 是否包含
        Assert.assertTrue("登录成功".contains("成功"));
    }

    // 10+20=30
    @Test
    public void test02Add() {
        int result2 = CalUtil.add(10, 20);
        System.out.println("result2=" + result2);

        // 断言- 如果断言失败了打印：断言失败了
        try {
            Assert.assertEquals(result2, 300);
        } catch (AssertionError e) {
            // 断言失败后的业务处理：截图、打印日志、、、
            System.out.println("断言失败了");
            throw e;
        }
    }

    // 100+200=300


}
