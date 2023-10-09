import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class JUnitTest {
    // @AfterEach
    void baseAnnotate() {
        System.out.println("测试@Test注解");
    }
    // @AfterAll
    static void annotate2() {
        System.out.println("测试2");
    }
   // @Test
    void annotate3() {
        System.out.println("测试3");
    }

    // 断言：
    // 断言获取到的按钮文本是否为“百度一下子”
    // @Test
    void assertAnnotate() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");
        String value = driver.findElement(By.cssSelector("#su")).getAttribute("value");  // 获取属性值
        System.out.println(value);
        // 进行断言判断
        Assertions.assertNotEquals("百度一下子",value);
        driver.quit();
    }

    @Test
    void TFAnnotate1() {
        // 断言真假
        Assertions.assertTrue(1==1);
    }
    @Test
    void TFAnnotate2() {
        // 断言真假
        Assertions.assertFalse(1==2);
    }
    @Test
    void TFAnnotate3() {
        // 断言真假
        // 注意：空字符串不是null
        Assertions.assertNull("");
    }
    @Test
    void TFAnnotate5() {
        // 断言真假
        Assertions.assertNull(null);
    }
    @Test
    void TFAnnotate6() {
        // 断言真假
        Assertions.assertNotNull("nihaoya");
    }

}
