package com.autoTest0209;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.reflect.misc.FieldUtil;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class autoTest {
    ChromeDriver driver = new ChromeDriver();
    public void baseConTrol() throws InterruptedException {
        driver.get("https://www.baidu.com");
        ///////
        //����Ĳ���
//        WebElement ele = driver.findElement(By.cssSelector("#kw"));
//        ele.sendKeys("java104&105 yyds!!!!");

//        driver.findElement(By.cssSelector("#kw")).sendKeys("����������dddd����һ�ּ��ı�д��ʽ");
//        driver.findElement(By.cssSelector("#su")).click();
//        Thread.sleep(3000);
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#kw")).sendKeys("Java104&105");
//        Thread.sleep(2000);
//        //submit
//        driver.findElement(By.cssSelector("#su")).submit();
//        Thread.sleep(2000);
/*        driver.findElement(By.cssSelector("#kw")).sendKeys("���");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#kw")).clear();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#kw")).sendKeys("���");
        Thread.sleep(2000);*/
//        String text = driver.findElement(By.cssSelector("#s-top-left > div > a")).getText();
//        System.out.println("��ȡ�����ı���"+text);
//        String newsText = driver.findElement(By.cssSelector("#hotsearch-content-wrapper > li:nth-child(1) > a > span.title-content-title")).getText();
//        System.out.println("���ţ�"+newsText);
//        String bottonText = driver.findElement(By.cssSelector("#su")).getText();
//        System.out.println(driver.findElement(By.cssSelector("#su")).getAttribute("type"));
//        System.out.println(driver.findElement(By.cssSelector("#su")).getAttribute("id"));
//        System.out.println(driver.findElement(By.cssSelector("#su")).getAttribute("value"));
//        System.out.println(driver.findElement(By.cssSelector("#su")).getAttribute("class"));
//        <input type="submit" id="su" value="�ٶ�һ��" class="bg s_btn">
        System.out.println("����֮ǰ��");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("#kw")).sendKeys("���");
        driver.findElement(By.cssSelector("#su")).click();
        System.out.println("����֮��");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        //////
        driver.quit();
    }
    public void windowConTrol() throws InterruptedException, IOException {
//        Thread.sleep(3000);
//        //�������
//        driver.manage().window().maximize();
//        Thread.sleep(3000);
//        //������С��
//        driver.manage().window().minimize();
//        Thread.sleep(3000);
//        //ȫ��
//        driver.manage().window().fullscreen();
//        Thread.sleep(3000);
//        //�ֶ����ô��ڴ�С
//        driver.manage().window().setSize(new Dimension(1024,768));
//        Thread.sleep(2000);
//
//        driver.quit();

//        driver.get("https://www.baidu.com");
//        driver.findElement(By.cssSelector("#s-top-left > a:nth-child(6)")).click();
//        //��ȡ��ǰҳ��ľ��
//        String curHandle = driver.getWindowHandle();
//        System.out.println("��ǰҳ��ľ����"+curHandle);
//        //�Ȼ�ȡ���б�ǩ�ľ��
//        Set<String> handles = driver.getWindowHandles();
//        for (String handle : handles){
//            if(handle != curHandle){
//                driver.switchTo().window(handle);
//            }
//        }
//        driver.findElement(By.cssSelector("#homeSearchForm > span.s_btn_wr > input"));

        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("�����Ȱ�");
        driver.findElement(By.cssSelector("#su")).click();
        Thread.sleep(3000);
        //��Ļ��ͼ�������ֳ���
        File srcfile = driver.getScreenshotAs(OutputType.FILE);
        //����Ļ��ͼ�õ��ļ��ŵ�ָ����·����
        String filename = "my.png";
        FileUtils.copyFile(srcfile,new File(filename));
        driver.findElement(By.cssSelector("#\\31  > div > div > div > div > div.cos-row.row-text_Johh7.row_5y9Az > div > a > div > p > span > span"));
        driver.quit();
    }
    public void TestBlogLogin() throws InterruptedException {
        driver.get("http://42.192.83.143:8563/blog_system/blog_login.html");
        Thread.sleep(3000);
        //�����˺ź�����
        driver.findElement(By.cssSelector("#username")).sendKeys("admin");
        driver.findElement(By.cssSelector("#password")).sendKeys("123");
        Thread.sleep(3000);
        //�����½��ť
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(3000);
        driver.quit();
    }
}
