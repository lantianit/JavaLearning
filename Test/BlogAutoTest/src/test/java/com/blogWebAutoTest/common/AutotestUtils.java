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

    //������������
    public static ChromeDriver createDriver(){
        //���������Ѿ���������/û�д���
        if( driver == null){
            driver = new ChromeDriver();
            //������ʽ�ȴ�
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public List<String> getTime(){
        //�ļ��ܲ��ܰ������ά�Ȱ��ļ��н��б���
        //�ļ���ʽ 20230212-123030����
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
     * ��ȡ��Ļ��ͼ�������е�����ִ�еĽ����������
     */
    public void getScreenShot(String str) throws IOException {
        List<String> list = getTime();
        //dir+filename
        // ./ָ���ǵ�ǰ����Ŀ·���£�Ҳ����BlogAutoTest��
        // ./src/test/java/com/blogWebAutoTest/dirname/filename
        // ./src/test/java/com/blogWebAutoTest/20230212/logintest_20230212-123030����.png
        String filename = "./src/test/java/com/blogWebAutoTest/"+list.get(0)+"/"+str+"_"+list.get(1)+".png";
        File srcfile = driver.getScreenshotAs(OutputType.FILE);
        //����Ļ��ͼ���ɵ��ļ��ŵ�ָ����·��
        FileUtils.copyFile(srcfile,new File(filename));
    }
}
