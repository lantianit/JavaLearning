package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutoTestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogLoginTest extends AutoTestUtils {
    // 注意：一定要关注测试用例执行顺序的问题

    public static ChromeDriver driver = createDriver();
    // 如果要测试登录页面，则所有的用来都会用到 创建驱动+打开浏览器
    @BeforeAll
    static void baseControl() {
        driver.get("http://42.192.83.143:8563/blog_system/blog_login.html");
    }

    /*
    * 检查登录页面打开是否正确：
    * 检查点：右上角的显示、左上角的显示以及登录框等
    */
    @Test
    @Order(1)
    void loginPageLoadRight() throws IOException {
        driver.findElement(By.cssSelector("body > div.nav > a:nth-child(4)"));
        driver.findElement(By.xpath("/html/body/div[1]/a[2]"));
        driver.findElement(By.cssSelector("body > div.login-container > form > div"));
        getScreenShot(getClass().getName());
    }

    /*
    * 检查登录正常情况：使用多参数测试
    */
    @Order(3)
    @ParameterizedTest
    @CsvSource({"admin,123","lisi,123"})
    void loginSuc(String name, String passwd) throws InterruptedException, IOException {
        // 在每次登录之后都要进行清空，然后才能重新输入
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();

        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys(passwd);
        driver.findElement(By.cssSelector("#submit")).click();
        getScreenShot(getClass().getName());

        // 以上是登录步骤，但是并不能确保就是登录成功的
        // 对登录成功以后的页面进行查找:查找“全文”按钮
        driver.findElement(By.cssSelector("body > div.container > div.right > div:nth-child(1) > a"));
        // 但是因为要多参数，所以在执行完一遍执行下一遍的时候需要进行页面的回退，否则根本找不到登录框
        driver.navigate().back();
//        Thread.sleep(5000); // 强制等待，看看效果
    }

    /*
    * 检查异常登录情况:
    * 多参数测试
    * 登录失败的检查元素和成功是不一样的
    */
    @Order(2)
    @ParameterizedTest
    @CsvSource({"admin,1235", "lisi,12"})
    void loginFail(String name, String passwd) throws InterruptedException, IOException {
        // 在每次登录之后都要进行清空，然后才能重新输入
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();

        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys(passwd);
        driver.findElement(By.cssSelector("#submit")).click();
        getScreenShot(getClass().getName());

        // 登录失败的检测，不能仅仅通过body来进行校验，因为在登录成功的页面也能够获取到
        // 所以使用获取文本进行比对
        String expectNotNull = "鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒\uE1E4!"; // 非空错误情况
        String actual = driver.findElement(By.cssSelector("body")).getText();
        // 这里其实应该分开判断，因为不同的输入对应的是不同的页面
        Assertions.assertEquals(expectNotNull,actual);

        // 导航回登录页
        driver.navigate().back();
//        Thread.sleep(5000);
    }

}
