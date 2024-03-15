package com.blogWebAutoTest.Tests;

import com.blogWebAutoTest.common.AutotestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlogLoginTest extends AutotestUtils {
    public static ChromeDriver driver = createDriver();;
    //如果要测试登录页面，以下所有的用例都有一个共同的步骤
    //1、要有浏览器对象  2、访问登录页面的url
    @BeforeAll
    static void baseControl(){
        driver.get("http://42.192.83.143:8563/blog_system/blog_login.html");
    }
    /*
    检查登录页面打开是否正确
    检查点：主页 写博客 元素是否存在
     */
    @Test
    @Order(1)
    void loginPageLoadRight() throws IOException {
        driver.findElement(By.cssSelector("body > div.nav > a:nth-child(4)"));
        driver.findElement(By.xpath("/html/body/div[1]/a[2]"));
        getScreenShot(getClass().getName());
    }

    /**
     * 检查正常登录情况
     */
    @ParameterizedTest
    @CsvSource({"admin,123","lisi,123"})
    @Order(2)
    void loginSuc(String name , String passwd) throws InterruptedException, IOException {
        //这三步只是登录的步骤结束了，能不能保证登录是成功的呢？？
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();

        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys(passwd);
        driver.findElement(By.cssSelector("#submit")).click();
        //对登录结果进行检测,如果跳转到了博客列表页才算是登录成功了
        driver.findElement(By.cssSelector("body > div.container > div.right > div:nth-child(1) > a"));
        getScreenShot(getClass().getName());
        driver.navigate().back();
    }
    @ParameterizedTest
    @CsvSource({"admin,1234"})
    @Order(3)
    void loginFail(String name, String passwd) throws IOException {
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();

        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys(passwd);
        driver.findElement(By.cssSelector("#submit")).click();
        //等登录失败的结果进行检测,不能仅仅通过body来校验结果，因为登录成功的结果页也有body元素
//        String expect = "鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒\uE1E4!";
        String expect = "哈哈哈哈哈哈哈";
        String actual = driver.findElement(By.cssSelector("body")).getText();
        getScreenShot(getClass().getName());
        Assertions.assertEquals(expect,actual);
        driver.navigate().back();
    }

}
