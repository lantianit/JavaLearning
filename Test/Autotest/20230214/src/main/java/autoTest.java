import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class autoTest {
    // 导入浏览器驱动
    ChromeDriver driver = new ChromeDriver();
    // 常见用户操作
    public void baseControl() throws InterruptedException {
        // 打开浏览器
        driver.get("https://www.baidu.com");
        // 执行速度太快，那就使用线程等待一段时间
        Thread.sleep(3000);

        // 具体的操作

//        // 1. 查找元素
//        WebElement ele = driver.findElement(By.cssSelector("#kw"));
//        // 2. 输入文本
//        ele.sendKeys("努力且上进");

        // 简写方式：
       // driver.findElement(By.cssSelector("#kw")).sendKeys("忠诚且炙热");
        // driver.findElement(By.cssSelector("#su")).sendKeys("耶耶");

        // 3. 点击按钮
        // driver.findElement(By.cssSelector("#su")).click();


        // 提交：通过回车键
        // driver.findElement(By.cssSelector("#su")).submit();


        // 清除功能
//        driver.findElement(By.cssSelector("#kw")).sendKeys("你号");
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#kw")).clear();
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#kw")).sendKeys("你好");


        // 获取文本
//        String text = driver.findElement(By.cssSelector("#s-top-left > div > a")).getText();
//        System.out.println("text: " + text);
//        String newsText = driver.findElement(By.cssSelector("#hotsearch-content-wrapper > li:nth-child(5) > a > span.title-content-title")).getText();
//        System.out.println("newsText: " + newsText);
//        String buttonText = driver.findElement(By.cssSelector("#su")).getText();
//        System.out.println("buttonText: " + buttonText);


        // 获取属性值
//        String buttonValue = driver.findElement(By.cssSelector("#su")).getAttribute("value");
//        System.out.println("buttonValue: " + buttonValue);
//        System.out.println(driver.findElement(By.cssSelector("#su")).getAttribute("id"));


        // 查看title和url
        System.out.println("搜索之前：");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("#kw")).sendKeys("秋招");
        driver.findElement(By.cssSelector("#su")).click();
        Thread.sleep(3000);
        System.out.println("搜索之后：");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        Thread.sleep(3000);

        // 关闭浏览器
        driver.quit();
    }

    // 个人博客系统登录测试
    public void testBlogLogin() throws InterruptedException {
        // 获取地址
        driver.get("http://140.210.201.164:8080/blog_system/login.html");
        Thread.sleep(3000);
        // 输入账号、密码
        driver.findElement(By.cssSelector("#username")).sendKeys("小小周");
        driver.findElement(By.cssSelector("#password")).sendKeys("xiaozhou");
        Thread.sleep(3000);
        // 点击登录按钮
        driver.findElement(By.cssSelector("#login-button")).click();
        Thread.sleep(3000);
        // 关闭浏览器
        driver.quit();
    }


    // 窗口设置
    public void windowControl() throws InterruptedException, IOException {
        // 之前已经设置好了浏览器驱动

//        // 窗口最大化
//        driver.manage().window().maximize();
//        Thread.sleep(3000);
//        // 窗口最小化
//        driver.manage().window().minimize();
//        Thread.sleep(3000);
//        // 全屏
//        driver.manage().window().fullscreen();
//        Thread.sleep(3000);
//        // 手动设置大小
//        driver.manage().window().setSize(new Dimension(1024,780));
//        Thread.sleep(3000);
//        driver.quit();

        // 窗口切换
        // 百度图片 寻找百度一下
//        driver.get("https://www.baidu.com");
//        driver.findElement(By.cssSelector("#s-top-left > a:nth-child(6)")).click();
//        // 获取当前页面的句柄
//        String curHandle = driver.getWindowHandle();
//        System.out.println("获取当前页面句柄： " + curHandle);
//        // 先获取所有标签页的句柄
//        Set<String> handles = driver.getWindowHandles();
//        for (String handle : handles) {
//            // 窗口切换操作
//            if (handle != curHandle) {
//                driver.switchTo().window(handle);
//            }
//        }
//        Thread.sleep(2000);
//        driver.findElement(By.cssSelector("#homeSearchForm > span.s_btn_wr > input"));
//        Thread.sleep(2000);
//        driver.quit();

//        // 发现程序退出，且没有关闭页面

        // 屏幕截图：可以保存现场
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("蔡徐坤");
        driver.findElement(By.cssSelector("#su")).click();
        Thread.sleep(3000);
        // 保存现场：屏幕截图
        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        String filePath = "myScr.jpg";  // 设置保存路径
        FileUtils.copyFile(scrFile,new File(filePath));
        driver.findElement(By.cssSelector("#\\31  > div > div > div > div > div.cos-row.row-text_Johh7.row_5y9Az > div > a > div > p > span > span"));
        driver.quit();

    }



}
