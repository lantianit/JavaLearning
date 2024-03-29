package com.autotest0113;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.List;

public class FirstAutotest {
    //百度网址搜索关键词“迪丽热巴”
    public void dilirebaTest() throws InterruptedException {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        EdgeDriver driver = new EdgeDriver(options);
        Thread.sleep(5000);
        //在浏览器里输入百度网址，访问百度首页
        driver.get("https://www.baidu.com");
        Thread.sleep(5000);
        //找到百度首页输入框元素，并输入关键词“迪丽热巴”
        driver.findElement(By.cssSelector("#kw")).sendKeys("迪丽热巴");
        Thread.sleep(5000);
        //找到百度首页“百度一下”按钮，并点击一下
        driver.findElement(By.cssSelector("#su")).click();
        Thread.sleep(5000);
        //结束会话（关闭浏览器）
        driver.quit();
    }
    public void methodTest() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        EdgeDriver driver = new EdgeDriver(options);
        driver.get("https://www.baidu.com");
        /////
//        driver.findElement(By.cssSelector("#kwaaaaa"));
//         List<WebElement> eles = driver.findElements(By.className("hotsearch-item"));
//         for (WebElement ele: eles){
//             System.out.println(ele.getText());
//         }

        //元素的定位
        //选择器selector
//        driver.findElement(By.cssSelector("#ssssssssu"));
        ///
        driver.findElement(By.className("s-isindex-wrap"));
        driver.quit();
    }
}
