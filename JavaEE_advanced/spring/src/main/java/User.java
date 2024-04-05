import org.springframework.stereotype.Component;

@Component
public class User {

    public User() {
        System.out.println("执行了 User 构造方法");
    }

    public String sayHi() {
        return "Hello, world.";
    }
}
