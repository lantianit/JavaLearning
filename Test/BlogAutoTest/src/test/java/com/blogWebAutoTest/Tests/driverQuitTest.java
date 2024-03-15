package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutotestUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class driverQuitTest extends AutotestUtils {
    public static ChromeDriver driver = createDriver();
    @Test
    void driverQuit(){
        driver.quit();
    }
}
