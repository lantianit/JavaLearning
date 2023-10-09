package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutoTestUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;

// 博客编辑页测试
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogEditTest extends AutoTestUtils {
    public static ChromeDriver driver = createDriver();
    @BeforeAll
    static void baseControl() {
        driver.get("http://140.210.201.164:8080/blog_system/blog_edit.html");
    }

    /*
    * 测试编辑页可以正常打开
    */
    @Test
    @Order(1)
    void editPageLoadRight() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // 可以多检查几个，确保正确
        driver.findElement(By.cssSelector("#blog-title"));
        driver.findElement(By.cssSelector("#editor > div.CodeMirror.cm-s-default.CodeMirror-wrap > div.CodeMirror-scroll"));
        driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        getScreenShot(getClass().getName());
    }

    /*
    * 正确编辑并发布博客测试
    * 目前是在列表页，直接点击列表页的按钮进入编辑页 然后检查元素就行
    */
    @Test
    @Order(3)
    void editAndSubmitBlog() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("/html/body/div[1]/a[2]")).click();
        editPageLoadRight();
        String expect = "autoTest耶耶";
        // 编辑页的md是第三方插件，所以不可以直接使用sendKeys向编辑模块写入内容，但是可以通过点击上方按钮进行内容的插入
        driver.findElement(By.cssSelector("#blog-title")).sendKeys(expect);
        driver.findElement(By.cssSelector("#editor > div.editormd-toolbar > div > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.xpath("//*[@id=\"editor\"]/div[5]/div/ul/li[20]/a")).click();
        driver.findElement(By.cssSelector("#editor > div.editormd-toolbar > div > ul > li:nth-child(6) > a")).click();
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        getScreenShot(getClass().getName());
        // 判断是否发布成功：获取第一篇博客的title文本，检查是否相符
        String actual = driver.findElement(By.cssSelector("body > div.container > div.container-right > div:nth-child(1) > div.title")).getText();
        Assertions.assertEquals(expect,actual);
    }

    /*
    * 测试“写博客”按钮是否可用 + 发布测试（无标题）
    */
    @Test
    @Order(2)
    void editWriteAndNoTitle() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // 进行无标题测试
        driver.findElement(By.cssSelector("#editor > div.editormd-toolbar > div > ul > li:nth-child(5) > a")).click();
        driver.findElement(By.xpath("//*[@id=\"editor\"]/div[5]/div/ul/li[20]/a")).click();
        driver.findElement(By.cssSelector("#editor > div.editormd-toolbar > div > ul > li:nth-child(6) > a")).click();
        driver.findElement(By.cssSelector("#submit")).click();
        getScreenShot(getClass().getName());
        // 进行元素检查[列表页]
        driver.findElement(By.cssSelector("body > div.container > div.container-right > div:nth-child(1) > a"));
    }
}
