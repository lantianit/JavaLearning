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
        String text = driver.findElement(By.cssSelector("#su")).getAttribute("value");//�ٶ�һ��
        //���������ȡ��������ֵ���ǰٶ�һ�£����ǰٶ�����
        System.out.println(text);
//        Assertions.assertNotEquals("�ٶ�����",text);

        driver.quit();
    }
    void ddd(){
        Assertions.assertTrue(1==0);//����
    }
    void eeee(){
        Assertions.assertFalse(1==1);//����

    }
    void ffff(){
        Assertions.assertFalse(1==0);//����
    }
    @Test
    void gggg(){
        String aaa=null;
        Assertions.assertNull(aaa);
    }
    @Test
    void hhhh(){
        Assertions.assertNotNull("���");//ͨ��
    }

}
