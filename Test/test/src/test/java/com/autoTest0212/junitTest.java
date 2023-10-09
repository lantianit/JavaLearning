package com.autoTest0212;

import com.beust.jcommander.Strings;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class junitTest {
    @Order(1)
    void editloginTest(){
        System.out.println("loginTest");
    }
    @Order(2)
    void AindexTest(){
        System.out.println("indexTest");

    }
    @Order(3)
    void editTest(){
        System.out.println("editTest");

    }
    @ValueSource(strings = {"lucy","mary","haitaos"})
    void SparamsTest(String name){
        System.out.println(name);
    }
    @CsvSource({"mary,20,女","lucy,25,女","bob,50,男","bob,50,男","bob,50,男","bob,50,男","bob,50,男","bob,50,男","bob,50,男","bob,50,男","bob,50,男","bob,50,男","bob,50,男","bob,50,男","bob,50,男"})
    void muchParamsTest(String name,int age,String sex){
        System.out.println("name:"+name+",age:"+age+",性别："+sex);
    }
    @ParameterizedTest
    @CsvFileSource(files = "D:\\file\\other\\mycsv.csv")
    void csvfileParamsTest(String name , int age){
        System.out.println("name:"+name+",age:"+age);
    }
    @CsvFileSource(files = "D:\\file\\other\\aaaa.csv")
    void csvWrongTest(String name,int age){
        System.out.println("name:"+name+",age:"+age);
    }
    //通过动态方法来提供数据源
    @MethodSource
    void dynamicmethodPramsTest(String name,int age){
        System.out.println("name:"+name+",age:"+age);
    }
    static Stream<Arguments> dynamicmethodPramsTest() throws InterruptedException {
        //构造动态参数
        String[] arr = new String[5];
        for (int i = 0; i< arr.length;i++){
            Thread.sleep(500);
            arr[i] = System.currentTimeMillis()+"";
        }
        return Stream.of(
                Arguments.arguments(arr[0],20),
                Arguments.arguments(arr[1],20),
                Arguments.arguments(arr[2],20),
                Arguments.arguments(arr[3],20),
                Arguments.arguments(arr[4],20)
        );
    }
//    static Stream<Arguments> methodParams() throws InterruptedException {
//        //构造动态参数
//        String[] arr = new String[5];
//        for (int i = 0; i< arr.length;i++){
//            Thread.sleep(500);
//            arr[i] = System.currentTimeMillis()+"";
//        }
//        return Stream.of(
//                Arguments.arguments(arr[0],20),
//                Arguments.arguments(arr[1],20),
//                Arguments.arguments(arr[2],20),
//                Arguments.arguments(arr[3],20),
//                Arguments.arguments(arr[4],20)
//        );
//    }

//    void loginSuc3Test(){
//        ChromeDriver driver = new ChromeDriver();
//        driver.get("http://42.192.83.143:8563/blog_system/blog_login.html");
//        driver.findElement(By.cssSelector("#username")).sendKeys("lisi");
//        driver.findElement(By.cssSelector("#password")).sendKeys("123");
//        driver.findElement(By.cssSelector("#submit")).click();
//        driver.quit();
//    }
}
