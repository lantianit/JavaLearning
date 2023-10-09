import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutoTest {
    // 浏览器驱动
    ChromeDriver driver = new ChromeDriver();
    // 等待
    public void waitControl() {
        // 隐式等待
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("蔡徐坤");
        driver.findElement(By.cssSelector("#su")).click();
        driver.findElement(By.cssSelector("#\\31  > div > div > div > div > div.cos-row.row-text_Johh7.row_5y9Az > div > a > div > p > span > span"));
        driver.quit();
    }

    // 显式等待
    // 希望只在某行进行等待
    void webDriverWait() {
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("蔡徐坤");
        driver.findElement(By.cssSelector("#su")).click();
        // 显式等待
        new WebDriverWait(driver,Duration.ofSeconds(3)).until(driver -> driver.findElement(By.cssSelector("#\\31  > div > div > div > div > div.cos-row.row-text_Johh7.row_5y9Az > div > a > div > p > span > span")));
        driver.quit();
    }


    // 浏览器导航
    void navigateControl() {
        // 其实这里请求百度首页应该也是导航操作，这里第简写
        // driver.get("https://www.baidu.com");
        // 实际上的写法：
        driver.navigate().to("https://www.baidu.com");
        // 想要进行回退
        driver.navigate().back();
        // 想要往前，又回到百度首页
        driver.navigate().forward();
        // 进行网页的刷新
        driver.navigate().refresh();
        driver.quit();
    }


    // 弹窗
    void alertControl() {
        driver.get("路径");
        // 打开弹窗
        driver.findElement(By.cssSelector("selector")).click();
        // 切换到弹窗上
        // 注意返回的是Alert类型
        Alert alert = driver.switchTo().alert();
        // 进行确认
        alert.accept();
        // 进行取消
        alert.dismiss();
        // 进行文本输入 + 选择确认或取消才能关闭弹窗
        alert.sendKeys("xxx输入文本");
        alert.accept();
        driver.quit();
    }


    // 选择框
    void selectorControl() {
        driver.get("选择框的url");
        // 找到选择框对象
        WebElement ele = driver.findElement(By.cssSelector("选择框的selector/xpath"));
        // 创建选择框对象
        Select select = new Select(ele);
        // 根据文本来选择
        select.selectByVisibleText("直接输入文本就行");
        // 根据属性值来选择
        select.selectByValue("属性值直接输入就行");
        // 根据序号来选择
        select.selectByIndex(2); // 直接输入数字序号就行（从0开始）
        driver.quit();
    }


    // 执行脚本：参数是原生js代码
    void scriptControl() throws InterruptedException {
//        driver.get("https://image.baidu.com/");
//        Thread.sleep(2000);
//        // 执行js命令：让元素置顶/置底
//        // Top = 0 就是顶部
//        driver.executeScript("document.documentElement.scrollTop = 0");
//        Thread.sleep(2000);
//        // 如果想要滑到最下面，设置的top值大一些就ok
//        driver.executeScript("document.documentElement.scrollTop = 500");

        // 百度输入框输入文本
        driver.get("https://www.baidu.com");
        Thread.sleep(2000);
        // 注意：js代码中间是使用分号;进行分隔开的
        driver.executeScript("var inp = document.querySelector(\"#kw\");inp.value = 'struggle'");
        Thread.sleep(2000);
        driver.quit();
    }


    // 文件上传
    void fileUploadControl() {
        driver.get("在浏览器中的url");
        // 选择文件按钮并进行文本输入
        driver.findElement(By.cssSelector("对应的selector")).sendKeys("文件夹路径");
        driver.quit();
    }

}
