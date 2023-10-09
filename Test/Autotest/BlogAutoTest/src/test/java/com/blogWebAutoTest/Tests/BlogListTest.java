package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutoTestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

// 博客列表页测试
public class BlogListTest extends AutoTestUtils {
    public static ChromeDriver driver = createDriver();
    @BeforeAll
    static void baseControl() {
        driver.get("http://42.192.83.143:8563/blog_system/blog_list.html");
    }

    /*
    * 博客列表页可以正常显示
    */
    @Test
    void listPageLoadRight() throws IOException {
        // 可以多检查几个，确保正确
        driver.findElement(By.cssSelector("body > div.container > div.right > div:nth-child(1) > a"));
        driver.findElement(By.cssSelector("body > div.container > div.left > div"));
        getScreenShot(getClass().getName());
    }

}
