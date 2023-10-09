package com.blogWebAutoTest.Tests;


import com.blogWebAutoTest.common.AutoTestUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;

// 博客详情页测试
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogDetailTest extends AutoTestUtils {
    public static ChromeDriver driver = createDriver();
    @BeforeAll
    static void baseControl() {
        driver.get("http://140.210.201.164:8080/blog_system/blog_detail.html");
    }

    /*
    * 详情页打开正确，但是因为没有blogId会出现Undefined
    */
    @Test
    @Order(1)
    void undefindedTest() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        String expect = "";
        String fact = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]")).getText();
        getScreenShot(getClass().getName());
        Assertions.assertEquals(expect,fact);
    }

    /*
    * 测试详情页正确打开[有blogId]
    * 测试标题以及时间（必定有）
    * 这里其实还有一种情况：不存在blogId
    */
    @Test
    @Order(2)
    void blogDetailPageRight() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("http://140.210.201.164:8080/blog_system/blog_detail.html?blogId=17");
        driver.findElement(By.cssSelector("body > div.container > div.container-right > div > h3"));
        driver.findElement(By.cssSelector("body > div.container > div.container-right > div > div.date"));
        driver.findElement(By.xpath("//*[@id=\"content\"]/p"));
        getScreenShot(getClass().getName());
        driver.findElement(By.xpath("/html/body/div[1]/a[1]")).click(); // 回到列表页
    }

    /*
    * 测试“删除”按钮
    * 删除后会跳回到列表页，然后与最上面的时间进行比对即可【不比较标题，因为标题可能为空】
    * 这里是要删除第一篇博客
    */
    @Test
    @Order(3)
    void deleteTest() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        getScreenShot(getClass().getName());
        driver.findElement(By.cssSelector("body > div.container > div.container-right > div:nth-child(1) > a")).click();
        String before = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]")).getText();
        driver.findElement(By.cssSelector("#delete_button")).click();
        String after = driver.findElement(By.cssSelector("body > div.container > div.container-right > div:nth-child(1) > div.date")).getText();
        getScreenShot(getClass().getName());
        Assertions.assertNotEquals(before,after);  // 比对不同
    }

//    // 注意更改驱动释放位置（在最后一个类/测试用例之后）
//    @AfterAll
//    static void driverQuite() {
//        driver.quit();
//    }
}
