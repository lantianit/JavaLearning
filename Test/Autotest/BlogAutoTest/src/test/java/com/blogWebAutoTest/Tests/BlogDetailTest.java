package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutoTestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

// 博客详情页测试
public class BlogDetailTest extends AutoTestUtils {
    public static ChromeDriver driver = createDriver();
    @BeforeAll
    static void baseControl() {
        driver.get("http://42.192.83.143:8563/blog_system/blog_detail.html?blogId=87");
    }

    /*
    * 测试详情页正确打开
    * 测试标题以及时间（必定有）
    */
    @Test
    void blogDetailPageRight() throws IOException {
        driver.findElement(By.cssSelector("body > div.container > div.right > div > h3"));
        driver.findElement(By.cssSelector("body > div.container > div.right > div > div.date"));
        getScreenShot(getClass().getName());
    }

//    // 注意更改驱动释放位置（在最后一个类/测试用例之后）
//    @AfterAll
//    static void driverQuite() {
//        driver.quit();
//    }
}
