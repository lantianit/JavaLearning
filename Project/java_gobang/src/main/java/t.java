import org.example.demo.service.AlipayService;
import org.example.demo.service.PayService;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PayServiceJDKInvocationHandler implements InvocationHandler {

    //目标对象即就是被代理对象
    private Object target;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}