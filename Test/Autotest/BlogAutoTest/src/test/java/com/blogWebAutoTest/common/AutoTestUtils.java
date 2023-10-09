package com.blogWebAutoTest.common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AutoTestUtils {
    // 为了降低代码冗余度，直接在这里实现代码的复用
    public static ChromeDriver driver;

    // 创建启动对象
    public static ChromeDriver createDriver() {
        // 判断驱动对象是否已经创建
        if(driver == null) {
            driver = new ChromeDriver();
            // 创建隐式等待，确保页面渲染出来
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        }
        return driver;
    }

    /*
    * 保存现场<截图>
    * 把所有用例的执行结果进行保存
    * 注意：保存的屏幕截图的名字应该是动态生成的，否则会进行图片的覆盖
     */
    // 获取动态文件名
    public List<String> getTime() {
        // 期望的时间格式：20230223-时间（到毫秒）
        // 也就是说：进行时间的格式化
        SimpleDateFormat sim1 = new SimpleDateFormat("yyyyMMdd-HHmmssSS");
        String fileName = sim1.format(System.currentTimeMillis());
        // 希望文件按天的维度进行文件夹的分类保存
        SimpleDateFormat sim2 = new SimpleDateFormat("yyyyMMdd");
        String dirName = sim2.format(System.currentTimeMillis()); // 文件夹的名字
        // 使用链表进行保存
        List<String> list = new ArrayList<>();
        list.add(dirName);
        list.add(fileName);
        return list;

    }

    // 为了区分是哪个用例返回的，可以加上用例的名称进行保存
    public void getScreenShot(String str) throws IOException {
        List<String> list = getTime();
        // 文件保存路径：dirName+fileName
        // ./指的是当前路径，这里来说就是BlogAutoTest目录下
        // 如果目前期望的路径：./src/test/java/com/blogWebAutoTest/dirName/fileName
        // 注意图片保存的路径以及名称！
        String fileName = "./src/test/java/com/blogWebAutoTest/"
                + list.get(0) + "/" + str + "_" + list.get(1) + ".png"; // 保存的路径+名称
        File scrFile = driver.getScreenshotAs(OutputType.FILE); // 获取到的屏幕截图
        // 把屏幕截图生成的文件放到指定路径下
        FileUtils.copyFile(scrFile,new File(fileName));
    }
}
