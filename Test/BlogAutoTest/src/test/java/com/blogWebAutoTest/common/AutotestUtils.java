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

public class AutotestUtils {
    public static ChromeDriver driver;

    //创建驱动对象
    public static ChromeDriver createDriver(){
        //驱动对象已经创建好了/没有创建
        if( driver == null){
            driver = new ChromeDriver();
            //创建隐式等待
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public List<String> getTime(){
        //文件能不能按照天的维度按文件夹进行保存
        //文件格式 20230212-123030毫秒
        SimpleDateFormat sim1 = new SimpleDateFormat("yyyyMMdd-HHmmssSS");
        SimpleDateFormat sim2 = new SimpleDateFormat("yyyyMMdd");
        String filename = sim1.format(System.currentTimeMillis());
        String dirname = sim2.format(System.currentTimeMillis());
        List<String> list = new ArrayList<>();
        list.add(dirname);
        list.add(filename);
        return  list;
    }
    /**
     * 获取屏幕截图，把所有的用例执行的结果保存下来
     */
    public void getScreenShot(String str) throws IOException {
        List<String> list = getTime();
        //dir+filename
        // ./指的是当前的项目路径下，也就是BlogAutoTest下
        // ./src/test/java/com/blogWebAutoTest/dirname/filename
        // ./src/test/java/com/blogWebAutoTest/20230212/logintest_20230212-123030毫秒.png
        String filename = "./src/test/java/com/blogWebAutoTest/"+list.get(0)+"/"+str+"_"+list.get(1)+".png";
        File srcfile = driver.getScreenshotAs(OutputType.FILE);
        //把屏幕截图生成的文件放到指定的路径
        FileUtils.copyFile(srcfile,new File(filename));
    }
}
