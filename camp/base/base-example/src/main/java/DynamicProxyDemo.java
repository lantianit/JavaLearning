import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理演示类 JDK Proxy
 */

// 定义一个接口
interface Hello {
    void sayHello();
}

// 实现接口的类
class HelloImpl implements Hello {
    public void sayHello() {
        System.out.println("Hello, World!");
    }
}

// 实现 InvocationHandler 接口的代理处理器
class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoking method: " + method.getName());
        // 真正执行目标方法
        Object result = method.invoke(target, args);
        System.out.println("After invoking method: " + method.getName());
        return result;
    }
}

public class DynamicProxyDemo {
    public static void main(String[] args) {
        Hello hello = new HelloImpl();

        // 创建代理处理器
        MyInvocationHandler handler = new MyInvocationHandler(hello);

        // 创建动态代理实例
        Hello proxyHello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class[]{Hello.class},
                handler
        );

        // 调用代理方法
        proxyHello.sayHello();
    }
}
