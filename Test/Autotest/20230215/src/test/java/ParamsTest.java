import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ParamsTest {
    // 参数设置
    void paramsControl() {
        // 无头模式
        // 百度搜索 蔡徐坤
        // 先创建选项对象，然后再设置浏览器参数
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-headless");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("蔡徐坤");
        driver.findElement(By.cssSelector("#su")).click();
        driver.quit();
    }
}
