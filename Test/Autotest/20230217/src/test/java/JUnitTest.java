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
public class JUnitTest {
//    排序测试
//    @Test
//    @Order(1)
//    void ELoginTest() {
//        System.out.println("ELoginTest");
//    }
//    @Test
//    @Order(2)
//    void AIndexTest() {
//        System.out.println("AIndexTest");
//    }
//    @Test
//    @Order(3)
//    void FEditTest() {
//        System.out.println("FEditTest");
//    }


    // 参数化
    // 单参数
//    @ParameterizedTest
//    @ValueSource(strings = {"小周","killer","小蔡"})
//    void myParamTeat(String name) {
//        System.out.println(name);
//    }

    // 多参数 参数化
//    @ParameterizedTest
//    @CsvSource({"Lucky,12,女", "小周,21,女", "小蔡,24,男"})
//    void muchParamTest(String name, int age, String gender) {
//        System.out.println("name:" + name +" ,age:" + age +" ,gender:" + gender);
//    }

    // 多参数：使用第三方文档导入数据
//    @ParameterizedTest
//    @CsvFileSource(files = "E:\\java-demo\\autoTest\\mycsv.csv")
//    void csvFileParamTest(String name, int age) {
//        System.out.println("name:" + name +" ,age:" + age);
//    }

    // 动态方法提供数据源
    // @ParameterizedTest
    // @MethodSource("methodParam")
    @ParameterizedTest
    @MethodSource
    void dynamicParaTest(String name, int age) {
        System.out.println("name:" + name + " ,age:" + age);
    }
    static Stream<Arguments> dynamicParaTest() throws InterruptedException { // 必须是静态方法！！
        // 构造动态参数
        String[] arr = new String[5];
        for (int i = 0; i < arr.length; i++) {
            Thread.sleep(200);
            arr[i] = System.currentTimeMillis()+""; // 拼接为字符串类型
        }
        return Stream.of(
                Arguments.arguments(arr[0],20),
                Arguments.arguments(arr[1],18),
                Arguments.arguments(arr[2],29),
                Arguments.arguments(arr[3],6),
                Arguments.arguments(arr[4],33)
        );
    }

//    static Stream<Arguments> methodParam() throws InterruptedException { // 必须是静态方法！！
//        // 构造动态参数
//        String[] arr = new String[5];
//        for (int i = 0; i < arr.length; i++) {
//            Thread.sleep(200);
//            arr[i] = System.currentTimeMillis()+""; // 拼接为字符串类型
//        }
//        return Stream.of(
//                Arguments.arguments(arr[0],20),
//                Arguments.arguments(arr[1],18),
//                Arguments.arguments(arr[2],29),
//                Arguments.arguments(arr[3],6),
//                Arguments.arguments(arr[4],33)
//                );
//    }

    void loginTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://140.210.201.164:8080/blog_system/login.html");
        driver.findElement(By.cssSelector("#username")).sendKeys("小小周");
        driver.findElement(By.cssSelector("#password")).sendKeys("xiaozhou");
        driver.findElement(By.cssSelector("#login-button")).click();
        driver.quit();
    }
}
