package testng;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

// 生成生成报告的注解
//@Listeners({HTMLReporter.class})
public class Test08Depend {

    @Test
    public void openApp() {
        System.out.println("打开应用...");
    }

    @Test(dependsOnMethods = "openApp")
    public void inputUsername() {
        System.out.println("输入用户名...");
    }

    @Test(dependsOnMethods = "inputUsername")
    public void login() {
        System.out.println("登录...");
    }

}
