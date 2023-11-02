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
    //���Ҫ���Ե�¼ҳ�棬�������е���������һ����ͬ�Ĳ���
    //1��Ҫ�����������  2�����ʵ�¼ҳ���url
    @BeforeAll
    static void baseControl(){
        driver.get("http://42.192.83.143:8563/blog_system/blog_login.html");
    }
    /*
    ����¼ҳ����Ƿ���ȷ
    ���㣺��ҳ д���� Ԫ���Ƿ����
     */
    @Test
    @Order(1)
    void loginPageLoadRight() throws IOException {
        driver.findElement(By.cssSelector("body > div.nav > a:nth-child(4)"));
        driver.findElement(By.xpath("/html/body/div[1]/a[2]"));
        getScreenShot(getClass().getName());
    }

    /**
     * ���������¼���
     */
    @ParameterizedTest
    @CsvSource({"admin,123","lisi,123"})
    @Order(2)
    void loginSuc(String name , String passwd) throws InterruptedException, IOException {
        //������ֻ�ǵ�¼�Ĳ�������ˣ��ܲ��ܱ�֤��¼�ǳɹ����أ���
        driver.findElement(By.cssSelector("#username")).clear();
        driver.findElement(By.cssSelector("#password")).clear();

        driver.findElement(By.cssSelector("#username")).sendKeys(name);
        driver.findElement(By.cssSelector("#password")).sendKeys(passwd);
        driver.findElement(By.cssSelector("#submit")).click();
        //�Ե�¼������м��,�����ת���˲����б�ҳ�����ǵ�¼�ɹ���
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
        //�ȵ�¼ʧ�ܵĽ�����м��,���ܽ���ͨ��body��У��������Ϊ��¼�ɹ��Ľ��ҳҲ��bodyԪ��
//        String expect = "用户名或密码错�\uE1E4!";
        String expect = "��������������";
        String actual = driver.findElement(By.cssSelector("body")).getText();
        getScreenShot(getClass().getName());
        Assertions.assertEquals(expect,actual);
        driver.navigate().back();
    }

}
