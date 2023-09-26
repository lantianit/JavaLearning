import com.demo.controller.UserAdviceController;
import com.demo.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");
//        BeanFactory context = new XmlBeanFactory(new ClassPathResource("spring-config.xml"));
        System.out.println("得到 Spring 之后");
        // 1.先得到 Spring 对象
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("spring-config.xml");
        // 2.从 Spring 中取出 Bean 对象
//        User user = (User) context.getBean("user"); // 根据 Bean 名称（标识）来得到 Bean 对象
//        User user = context.getBean(User.class); // 根据 Bean 类型来获取 Bean
//        User user = context.getBean("user", User.class); // 根据 Bean 名称+Bean类型或 Bean 对象
//        // 3.使用 Bean（可选）
//        System.out.println(user.sayHi());


//        ArticleController articleController = context.getBean("articleController",
//                ArticleController.class);
//        System.out.println(articleController.sayHello());

//        aController controller = context.getBean("aController",
//                aController.class);
//        System.out.println(controller.sayHi());

//        BController controller = context.getBean("BController",
//                BController.class);
//        System.out.println(controller.sayHi());

//        String s1 = "UserInfo";
//        System.out.println("s1：" + Introspector.decapitalize(s1));
//
//        String s2 = "userInfo";
//        System.out.println("s2：" + Introspector.decapitalize(s2));
//
//        String s3 = "UInfo";
//        System.out.println("s3：" + Introspector.decapitalize(s3));

//        UserComponent component = context.getBean("userComponent", UserComponent.class);
//        System.out.println(component.sayHi());

//        User user = context.getBean("user", User.class);
//        System.out.println(user.sayHi());

//        Student student = context.getBean("student", Student.class);
//        System.out.println(student);

//        StudentController sc = context.getBean("studentController", StudentController.class);
//        sc.sayHi();


        UserController uc = context.getBean("userController", UserController.class);
        uc.getUser();

        UserAdviceController ua = context.getBean("userAdviceController", UserAdviceController.class);
        ua.getUser();


    }
}
