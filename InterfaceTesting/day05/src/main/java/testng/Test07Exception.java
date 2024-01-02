package testng;

import org.testng.annotations.Test;

public class Test07Exception {

    // 异常测试
    @Test(expectedExceptions = ArithmeticException.class)
    public void test01Div() {
        double result = CalUtil.div(10, 0);
        System.out.println("result=" + result);
    }

}
