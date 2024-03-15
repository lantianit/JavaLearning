package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutotestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class BlogDetailTest extends AutotestUtils {
    public static ChromeDriver driver = createDriver();
    @BeforeAll
    static  void baseControl(){
        driver.get("http://42.192.83.143:8563/blog_system/blog_detail.html?blogId=79");
    }
    @Test
    void blogDeailLoadRight() throws IOException{
        driver.findElement(By.cssSelector("body > div.container > div.right > div > h3"));
        driver.findElement(By.cssSelector("body > div.container > div.right > div > div.date"));
        getScreenShot(getClass().getName());

    }
}
