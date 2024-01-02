package testng;

import org.testng.annotations.Test;

public class Test06Enabled {

    // 把测试方法标记成禁用
    @Test(enabled = false)
    public void test01() {
        System.out.println("test01");
    }


    @Test
    public void test02() {
        System.out.println("test02");
    }
}
