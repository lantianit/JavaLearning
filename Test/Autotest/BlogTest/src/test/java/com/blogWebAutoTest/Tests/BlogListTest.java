package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutoTestUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;

// 博客列表页测试
// 上一步是在登录成功
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogListTest extends AutoTestUtils {
    public static ChromeDriver driver = createDriver();
    @BeforeAll
    static void baseControl() {
        driver.get("http://140.210.201.164:8080/blog_system/blog_list.html");
    }

    /*
    * 博客列表页可以正常显示
    * 检查：个人信息、菜单、列表以及右上角的模块
    */
    @Test
    @Order(1)
    void listPageLoadRight() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // 可以多检查几个，确保正确
        driver.findElement(By.cssSelector("body > div.container > div.container-right > div:nth-child(1) > div.title"));
        driver.findElement(By.cssSelector("body > div.container > div.container-left > div > img"));
        driver.findElement(By.cssSelector("body > div.nav > a:nth-child(6)"));
        getScreenShot(getClass().getName());
    }

    /*
    * 测试“查看全文“按钮
    * 点击之后，出现页面框 + 删除
    */
    @Test
    @Order(2)
    void listInspectAll() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/a")).click();
        driver.findElement(By.cssSelector("body > div.container > div.container-right"));
        driver.findElement(By.cssSelector("#delete_button"));
        getScreenShot(getClass().getName());
    }

    /*
    * 未登录：直接回到登录页面
    * 点击注销后直接输入链接打开,然后进行元素的检查
    * 最后要进行登录方便后续使用
    */
    @Test
    @Order(3)
    void listFail() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.cssSelector("body > div.nav > a:nth-child(6)")).click();
        driver.get("http://140.210.201.164:8080/blog_system/blog_list.html");
        driver.findElement(By.cssSelector("body > div.login-container > form > div"));
        getScreenShot(getClass().getName());
        // 进行登录
        driver.findElement(By.cssSelector("#username")).sendKeys("小小周");
        driver.findElement(By.cssSelector("#password")).sendKeys("xiaozhou");
        driver.findElement(By.cssSelector("#login-button")).click();
        getScreenShot(getClass().getName());
    }

}
