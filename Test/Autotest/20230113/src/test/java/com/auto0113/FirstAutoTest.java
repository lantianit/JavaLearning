package com.auto0113;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstAutoTest {
    // 测试1：百度网址搜索关键词“刘亦菲”
    public void feiTest() throws InterruptedException {
        // 1. 创建浏览器驱动
        ChromeDriver driver = new ChromeDriver();

        // 如果要执行慢一点，就给每一步都设置一个时间间隔（可以没有！）
        Thread.sleep(3000);

        // 2. 获取需要的网址
        driver.get("https://www.baidu.com");
        Thread.sleep(3000);

        // 3. 查找输入框并输入关键词
        driver.findElement(By.cssSelector("#kw")).sendKeys("刘亦菲");
        Thread.sleep(3000);

        // 4. 找到“百度一下”的按钮并点击
        driver.findElement(By.cssSelector("#su")).click();
        Thread.sleep(3000);

        // 5. 关闭浏览器
        driver.quit();

    }
}
