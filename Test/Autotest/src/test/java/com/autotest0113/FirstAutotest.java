package com.autotest0113;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.List;

public class FirstAutotest {
    //�ٶ���ַ�����ؼ��ʡ������Ȱ͡�
    public void dilirebaTest() throws InterruptedException {
        //��������ʵ���������Ự�����������
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        EdgeDriver driver = new EdgeDriver(options);
        Thread.sleep(5000);
        //�������������ٶ���ַ�����ʰٶ���ҳ
        driver.get("https://www.baidu.com");
        Thread.sleep(5000);
        //�ҵ��ٶ���ҳ�����Ԫ�أ�������ؼ��ʡ������Ȱ͡�
        driver.findElement(By.cssSelector("#kw")).sendKeys("�����Ȱ�");
        Thread.sleep(5000);
        //�ҵ��ٶ���ҳ���ٶ�һ�¡���ť�������һ��
        driver.findElement(By.cssSelector("#su")).click();
        Thread.sleep(5000);
        //�����Ự���ر��������
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

        //Ԫ�صĶ�λ
        //ѡ����selector
//        driver.findElement(By.cssSelector("#ssssssssu"));
        ///
//        driver.findElement(By.className("s-isindex-wrap"));
//        driver.quit();
    }
}
