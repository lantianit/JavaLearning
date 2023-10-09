package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutoTestUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

// 驱动释放
public class DriverQuiteTest extends AutoTestUtils {
    public static ChromeDriver driver = createDriver();
    // 不要忘记Test注解
    @Test
    void driverQuite() {
        driver.quit();
    }
}
