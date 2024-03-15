package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutotestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogEditTest extends AutotestUtils {
    public static ChromeDriver driver = createDriver();
    @BeforeAll
    static  void baseControl(){
        driver.get("http://42.192.83.143:8563/blog_system/blog_edit.html");
    }
    /**
     * 检查博客编辑页可以正常打开
     */
    @Test
    @Order(1)
    void editPageLoadRight() throws IOException {
        driver.findElement(By.cssSelector("body > div.nav > a:nth-child(5)"));
        driver.findElement(By.cssSelector("body > div.nav > a:nth-child(6)"));
        getScreenShot(getClass().getName());
    }
    @Test
    @Order(2)
    void editAndSubimitBlog() throws IOException {
        String expect = "java104&105 Autotest";
        driver.findElement(By.cssSelector("#title")).sendKeys(expect);
        //因博客系统使用到的编辑是第三方软件，所以不能直接使用sendKeys向编辑模块发送文本
        driver.findElement(By.cssSelector("#editor > div.editormd-toolbar > div > ul > li:nth-child(21) > a")).click();
        driver.findElement(By.cssSelector("#editor > div.editormd-toolbar > div > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.cssSelector("#submit")).click();
        getScreenShot(getClass().getName());
        //获取列表页第一条博客的标题文本，检查是否跟预期相符
        String actual = driver.findElement(By.cssSelector("body > div.container > div.right > div:nth-child(1) > div.title")).getText();
        Assertions.assertEquals(expect,actual);

    }
}
