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
//        //�����ʽ�ȴ�
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("https://www.baidu.com");
//        driver.findElement(By.cssSelector("#kw")).sendKeys("�����Ȱ�");
//        driver.findElement(By.cssSelector("#su")).click();
//        driver.findElement(By.cssSelector("#\\31  > div > div > div > div > div.cos-row.row-text_Johh7.row_5y9Az > div > a > div > p > span > span"));
//        driver.quit();
//    }
//    void webDriverWait(){
//        driver.get("https://www.baidu.com");
//        driver.findElement(By.cssSelector("#kw")).sendKeys("�����Ȱ�");
//        driver.findElement(By.cssSelector("#su")).click();
//        //�����ʾ�ȴ�
//        new WebDriverWait(driver,Duration.ofSeconds(5))
//                .until(driver->driver.findElement(By.cssSelector("#\\31  > div > ")));
//        driver.quit();
//    }
//    void navigateConTrol() throws InterruptedException {
//        //����ٶ���ַ��д���Ǽ�д
////        driver.get("https://www.baidu.com");
//        driver.navigate().to("https://www.baidu.com");
//        //��Ҫ���˵����ʰٶ���ַ֮ǰ��״̬
//        driver.navigate().back();
//        //ǰ��,�ֽ��뵽�˰ٶ���ҳ
//        driver.navigate().forward();
//        //ˢ�°ٶ���ҳ
//        driver.navigate().refresh();
//        driver.quit();
//    }
//    void Alertcontro() throws InterruptedException {
//        //ע�⣺get����Ϊurl���������ļ�·��
//        //�ڱ��ش�html�ļ�֮����Ҫ���������������ӷŵ�get������Ǹ���html���ص��ļ�·��������
////        driver.get("D:\\file\\���ؽ���\\����\\selenium4html\\selenium-html\\Prompt.html");
//        driver.get("file:///D:/file/%E6%AF%94%E7%89%B9%E6%95%99%E5%8A%A1/%E6%B5%8B%E8%AF%95/selenium4html/selenium-html/Prompt.html");
//        Thread.sleep(3000);
//        //�򿪵���
//        driver.findElement(By.cssSelector("body > input[type=button]")).click();
//        Thread.sleep(3000);
//        //�л����������е����Ĵ���
//        Alert alert =  driver.switchTo().alert();
//        Thread.sleep(3000);
//        //���������ı�
//        alert.sendKeys("����������������ı������������㿴ҳ����");
//        Thread.sleep(3000);
//        //1�����ȷ��
//        alert.accept();
//        //2�����ȡ��
////        alert.dismiss();
//        Thread.sleep(3000);
//        driver.quit();
//    }
//    void selectControll() throws InterruptedException {
//        driver.get("file:///D:/file/%E6%AF%94%E7%89%B9%E6%95%99%E5%8A%A1/%E6%B5%8B%E8%AF%95/selenium4html/selenium-html/select.html");
//        Thread.sleep(3000);
//         WebElement ele = driver.findElement(By.cssSelector("#ShippingMethod"));
//        //�ȴ���ѡ������
//        Select select = new Select(ele);
//        Thread.sleep(3000);
//        //�����ı���ѡ��
////        select.selectByVisibleText("UPS Next Day Air ==> $12.51");
//        //��������ֵ��ѡ��
////        select.selectByValue("12.51");
//        //���������ѡ��
//        select.selectByIndex(1);
//        Thread.sleep(3000);
//        driver.quit();
//    }
//    void scriptControll() throws InterruptedException {
////        driver.get("https://image.baidu.com/");
////        Thread.sleep(3000);
////        //ִ��js����:��ҳ���ö�/�õ�
////        //�����Ҫ������С�棬ֵ���õĴ�һЩ����
////        driver.executeScript("document.documentElement.scrollTop=500");
////        Thread.sleep(3000);
////        //0���Ƕ���
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
//        driver.findElement(By.cssSelector("body > div > div > input[type=file]")).sendKeys("D:\\file\\���ؽ���\\����\\selenium4html\\selenium-html\\upload.html");
//        Thread.sleep(3000);
//        driver.quit();
//    }
    void paramsControll(){
        //�ٶ����������Ȱ�
        //�ȴ���ѡ�����Ȼ�����������������
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-headless");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("�����Ȱ�");
        driver.findElement(By.cssSelector("#su")).click();
        driver.quit();
    }
}
