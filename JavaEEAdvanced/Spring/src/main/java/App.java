import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author zh
 * @Date 2023/7/23 20:29
 * @PackageName:PACKAGE_NAME
 * @ClassName: App
 * @Description: TODO
 * @Version 1.0
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        User user = (User) context.getBean(User.class);
        user.sayHi();
    }
}
