package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutotestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class BlogListTest extends AutotestUtils {
    public static ChromeDriver  driver = createDriver();
    @BeforeAll
    static void baseControl(){
        driver.get("http://42.192.83.143:8563/blog_system/blog_list.html");
    }
    /**
     * 博客列表页可以正常显示
     */
    @Test
    void listPageLoadRight() throws IOException {
        driver.findElement(By.cssSelector("body > div.container > div.left > div > div:nth-child(4) > span:nth-child(1)"));
        driver.findElement(By.cssSelector("body > div.container > div.left > div > div:nth-child(4) > span:nth-child(2)"));
        getScreenShot(getClass().getName());
    }

}
