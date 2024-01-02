package testng;

import org.testng.annotations.*;


public class Test02Time {
    // 前置处理-方法级别
    @BeforeMethod
    public void before() {
        System.out.println("start time==" + System.currentTimeMillis());
    }

    // 后置处理-方法级别
    @AfterMethod
    public void after() {
        System.out.println("end   time==" + System.currentTimeMillis());
    }

    // 前置处理-类级别
    @BeforeClass
    public void beforeClass() {
        System.out.println("beforeClass");
    }

    // 后置处理-类级别
    @AfterClass
    public void afterClass() {
        System.out.println("afterClass");
    }

    @Test
    public void test01() {
        System.out.println("test01");
    }

    @Test
    public void test02() {
        System.out.println("test02");
    }

}
