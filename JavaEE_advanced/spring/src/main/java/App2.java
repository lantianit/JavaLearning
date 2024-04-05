import com.demo.component.BeanLifeComponent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring-config.xml");
        BeanLifeComponent beanLifeComponent = applicationContext.getBean("myComponent",
                BeanLifeComponent.class);
        System.out.println("使用 Bean");
        // 销毁 Bean
        applicationContext.destroy();
    }
}
