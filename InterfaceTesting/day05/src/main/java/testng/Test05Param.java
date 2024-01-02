package testng;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test05Param {
    // 1+1=2
    @Test
    public void test01Add() {
        int result = CalUtil.add(1, 1);
        System.out.println("result=" + result);
        Assert.assertEquals(result, 2);
    }

    // 10+20=30
    @Test
    public void test02Add() {
        int result = CalUtil.add(10, 20);
        System.out.println("result=" + result);
        Assert.assertEquals(result, 300);
    }

    // 100+200=300
    @Test
    public void test03Add() {
        int result = CalUtil.add(100, 200);
        System.out.println("result=" + result);
        Assert.assertEquals(result, 300);
    }

    // 优化
    @Test
    public void test04AddNew() {
        int[][] testData = {{1, 1, 2}, {10, 20, 300}, {100, 200, 300}, {0, 0, 0}};
        for (int[] caseData : testData) {
            int result = CalUtil.add(caseData[0], caseData[1]);
            System.out.println("result=" + result);
            Assert.assertEquals(result, caseData[2]);
        }
    }

    // 定义数据的提供者（构造测试数据）
    @DataProvider
    public Object[][] buildData() {
        // 组装数据
        return new Object[][]{{1, 1, 2}, {10, 20, 300}, {100, 200, 300}};
    }

    // 参数化
    @Test(dataProvider = "buildData")
    public void test05AddParam(int x, int y, int expect) {
        System.out.println("x=" + x + " y=" + y + " expect=" + expect);
        int result = CalUtil.add(x, y);
        System.out.println("result=" + result);
        Assert.assertEquals(result, expect);
    }
}
