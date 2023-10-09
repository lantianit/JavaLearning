package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutoTestUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogEditTest extends AutoTestUtils {
    public static ChromeDriver driver = createDriver();
    @BeforeAll
    static void baseControl() {
        driver.get("http://42.192.83.143:8563/blog_system/blog_edit.html");
    }

    /*
    * 测试编辑页可以正常打开
    */
    @Test
    @Order(1)
    void editPageLoadRight() throws IOException {
        // 可以多检查几个，确保正确
        driver.findElement(By.cssSelector("body > div.nav > a:nth-child(6)"));
        driver.findElement(By.cssSelector("#title"));
        getScreenShot(getClass().getName());
    }

    /*
    * 正确编辑并发布博客测试
    */
    @Test
    @Order(2)
    void editAndSubmitBlog() throws IOException {
        String expect = "autoTesty耶耶";
        // 编辑页的md是第三方插件，所以不可以直接使用sendKeys向编辑模块写入内容，但是可以通过点击上方按钮进行内容的插入
        driver.findElement(By.cssSelector("#title")).sendKeys(expect);
        driver.findElement(By.cssSelector("#editor > div.editormd-toolbar > div > ul > li:nth-child(21) > a > i")).click();
        driver.findElement(By.xpath("//*[@id=\"editor\"]/div[5]/div/ul/li[20]/a/i")).click();
        driver.findElement(By.cssSelector("#editor > div.editormd-toolbar > div > ul > li:nth-child(5) > a > i")).click();
        driver.findElement(By.cssSelector("#submit")).click();
        getScreenShot(getClass().getName());
        // 判断是否发布成功：获取第一篇博客的title文本，检查是否相符
        String actual = driver.findElement(By.cssSelector("body > div.container > div.right > div:nth-child(1) > div.title")).getText();
        Assertions.assertEquals(expect,actual);
    }

}
