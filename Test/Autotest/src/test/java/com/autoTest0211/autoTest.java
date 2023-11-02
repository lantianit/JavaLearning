package com.autoTest0211;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class autoTest {
//    ChromeDriver driver = new ChromeDriver();
//    void implicitlyWait(){
//        //添加隐式等待
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("https://www.baidu.com");
//        driver.findElement(By.cssSelector("#kw")).sendKeys("迪丽热巴");
//        driver.findElement(By.cssSelector("#su")).click();
//        driver.findElement(By.cssSelector("#\\31  > div > div > div > div > div.cos-row.row-text_Johh7.row_5y9Az > div > a > div > p > span > span"));
//        driver.quit();
//    }
//    void webDriverWait(){
//        driver.get("https://www.baidu.com");
//        driver.findElement(By.cssSelector("#kw")).sendKeys("迪丽热巴");
//        driver.findElement(By.cssSelector("#su")).click();
//        //添加显示等待
//        new WebDriverWait(driver,Duration.ofSeconds(5))
//                .until(driver->driver.findElement(By.cssSelector("#\\31  > div > ")));
//        driver.quit();
//    }
//    void navigateConTrol() throws InterruptedException {
//        //请求百度网址的写法是简写
////        driver.get("https://www.baidu.com");
//        driver.navigate().to("https://www.baidu.com");
//        //想要回退到访问百度网址之前的状态
//        driver.navigate().back();
//        //前进,又进入到了百度首页
//        driver.navigate().forward();
//        //刷新百度首页
//        driver.navigate().refresh();
//        driver.quit();
//    }
//    void Alertcontro() throws InterruptedException {
//        //注意：get参数为url，而不是文件路径
//        //在本地打开html文件之后，需要复制浏览器里的链接放到get里，而不是复制html本地的文件路径！！！
////        driver.get("D:\\file\\比特教务\\测试\\selenium4html\\selenium-html\\Prompt.html");
//        driver.get("file:///D:/file/%E6%AF%94%E7%89%B9%E6%95%99%E5%8A%A1/%E6%B5%8B%E8%AF%95/selenium4html/selenium-html/Prompt.html");
//        Thread.sleep(3000);
//        //打开弹窗
//        driver.findElement(By.cssSelector("body > input[type=button]")).click();
//        Thread.sleep(3000);
//        //切换到弹窗进行弹窗的处理
//        Alert alert =  driver.switchTo().alert();
//        Thread.sleep(3000);
//        //弹窗输入文本
//        alert.sendKeys("弹窗里真的输入了文本，不相信请你看页面结果");
//        Thread.sleep(3000);
//        //1、点击确认
//        alert.accept();
//        //2、点击取消
////        alert.dismiss();
//        Thread.sleep(3000);
//        driver.quit();
//    }
//    void selectControll() throws InterruptedException {
//        driver.get("file:///D:/file/%E6%AF%94%E7%89%B9%E6%95%99%E5%8A%A1/%E6%B5%8B%E8%AF%95/selenium4html/selenium-html/select.html");
//        Thread.sleep(3000);
//         WebElement ele = driver.findElement(By.cssSelector("#ShippingMethod"));
//        //先创建选择框对象
//        Select select = new Select(ele);
//        Thread.sleep(3000);
//        //根据文本来选择
////        select.selectByVisibleText("UPS Next Day Air ==> $12.51");
//        //根据属性值来选择
////        select.selectByValue("12.51");
//        //根据序号来选择
//        select.selectByIndex(1);
//        Thread.sleep(3000);
//        driver.quit();
//    }
//    void scriptControll() throws InterruptedException {
////        driver.get("https://image.baidu.com/");
////        Thread.sleep(3000);
////        //执行js命令:让页面置顶/置底
////        //如果想要滑到最小面，值设置的大一些就行
////        driver.executeScript("document.documentElement.scrollTop=500");
////        Thread.sleep(3000);
////        //0就是顶部
////        driver.executeScript("document.documentElement.scrollTop=0");
//        driver.get("https://www.baidu.com");
//        Thread.sleep(3000);
//        driver.executeScript("var texts = document.querySelector('#kw');texts.value='java104&105'");
//        Thread.sleep(3000);
//        driver.quit();
//    }
//    void fileUploadControll() throws InterruptedException {
//        driver.get("file:///D:/file/%E6%AF%94%E7%89%B9%E6%95%99%E5%8A%A1/%E6%B5%8B%E8%AF%95/selenium4html/selenium-html/upload.html");
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("body > div > div > input[type=file]")).sendKeys("D:\\file\\比特教务\\测试\\selenium4html\\selenium-html\\upload.html");
//        Thread.sleep(3000);
//        driver.quit();
//    }
    void paramsControll(){
        //百度搜索迪丽热巴
        //先创建选项对象，然后再设置浏览器参数
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-headless");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("迪丽热巴");
        driver.findElement(By.cssSelector("#su")).click();
        driver.quit();
    }
}
