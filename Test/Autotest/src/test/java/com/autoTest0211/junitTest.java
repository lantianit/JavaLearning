package com.autoTest0211;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class junitTest {
//    @BeforeEach
//    @BeforeAll
//    @AfterEach
    static void  basezhujie(){
        System.out.println("basezhujie");
    }
    void aaa(){
        System.out.println("aaaaaaaa");
    }
    void bbb(){
        System.out.println("bbbb");
    }
    void huixiang(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");
        String text = driver.findElement(By.cssSelector("#su")).getAttribute("value");//百度一下
        //假如这里获取到的属性值不是百度一下，而是百度两下
        System.out.println(text);
//        Assertions.assertNotEquals("百度两下",text);

        driver.quit();
    }
    void ddd(){
        Assertions.assertTrue(1==0);//报错
    }
    void eeee(){
        Assertions.assertFalse(1==1);//报错

    }
    void ffff(){
        Assertions.assertFalse(1==0);//错误
    }
    @Test
    void gggg(){
        String aaa=null;
        Assertions.assertNull(aaa);
    }
    @Test
    void hhhh(){
        Assertions.assertNotNull("你好");//通过
    }

}
