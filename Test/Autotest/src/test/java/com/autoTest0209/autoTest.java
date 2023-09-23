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
        //具体的操作
//        WebElement ele = driver.findElement(By.cssSelector("#kw"));
//        ele.sendKeys("java104&105 yyds!!!!");

//        driver.findElement(By.cssSelector("#kw")).sendKeys("！！、、、dddd这是一种简洁的编写方式");
//        driver.findElement(By.cssSelector("#su")).click();
//        Thread.sleep(3000);
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#kw")).sendKeys("Java104&105");
//        Thread.sleep(2000);
//        //submit
//        driver.findElement(By.cssSelector("#su")).submit();
//        Thread.sleep(2000);
/*        driver.findElement(By.cssSelector("#kw")).sendKeys("你号");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#kw")).clear();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#kw")).sendKeys("你好");
        Thread.sleep(2000);*/
//        String text = driver.findElement(By.cssSelector("#s-top-left > div > a")).getText();
//        System.out.println("获取到的文本："+text);
//        String newsText = driver.findElement(By.cssSelector("#hotsearch-content-wrapper > li:nth-child(1) > a > span.title-content-title")).getText();
//        System.out.println("新闻："+newsText);
//        String bottonText = driver.findElement(By.cssSelector("#su")).getText();
//        System.out.println(driver.findElement(By.cssSelector("#su")).getAttribute("type"));
//        System.out.println(driver.findElement(By.cssSelector("#su")).getAttribute("id"));
//        System.out.println(driver.findElement(By.cssSelector("#su")).getAttribute("value"));
//        System.out.println(driver.findElement(By.cssSelector("#su")).getAttribute("class"));
//        <input type="submit" id="su" value="百度一下" class="bg s_btn">
        System.out.println("搜索之前：");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("#kw")).sendKeys("你好");
        driver.findElement(By.cssSelector("#su")).click();
        System.out.println("搜索之后：");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        //////
        driver.quit();
    }
    public void windowConTrol() throws InterruptedException, IOException {
//        Thread.sleep(3000);
//        //窗口最大化
//        driver.manage().window().maximize();
//        Thread.sleep(3000);
//        //窗口最小化
//        driver.manage().window().minimize();
//        Thread.sleep(3000);
//        //全屏
//        driver.manage().window().fullscreen();
//        Thread.sleep(3000);
//        //手动设置窗口大小
//        driver.manage().window().setSize(new Dimension(1024,768));
//        Thread.sleep(2000);
//
//        driver.quit();

//        driver.get("https://www.baidu.com");
//        driver.findElement(By.cssSelector("#s-top-left > a:nth-child(6)")).click();
//        //获取当前页面的句柄
//        String curHandle = driver.getWindowHandle();
//        System.out.println("当前页面的句柄："+curHandle);
//        //先获取所有标签的句柄
//        Set<String> handles = driver.getWindowHandles();
//        for (String handle : handles){
//            if(handle != curHandle){
//                driver.switchTo().window(handle);
//            }
//        }
//        driver.findElement(By.cssSelector("#homeSearchForm > span.s_btn_wr > input"));

        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("迪丽热巴");
        driver.findElement(By.cssSelector("#su")).click();
        Thread.sleep(3000);
        //屏幕截图（保存现场）
        File srcfile = driver.getScreenshotAs(OutputType.FILE);
        //把屏幕截图好的文件放到指定的路径下
        String filename = "my.png";
        FileUtils.copyFile(srcfile,new File(filename));
        driver.findElement(By.cssSelector("#\\31  > div > div > div > div > div.cos-row.row-text_Johh7.row_5y9Az > div > a > div > p > span > span"));
        driver.quit();
    }
    public void TestBlogLogin() throws InterruptedException {
        driver.get("http://42.192.83.143:8563/blog_system/blog_login.html");
        Thread.sleep(3000);
        //输入账号和密码
        driver.findElement(By.cssSelector("#username")).sendKeys("admin");
        driver.findElement(By.cssSelector("#password")).sendKeys("123");
        Thread.sleep(3000);
        //点击登陆按钮
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(3000);
        driver.quit();
    }
}
