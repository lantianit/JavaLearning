package much0221;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUnitTest {
    // 动态方法提供数据源
    // @ParameterizedTest
    // @MethodSource("methodParam")
    @ParameterizedTest
    @MethodSource
    void dynamicParaTest(String name, int age) {
        System.out.println("name:" + name + " ,age:" + age);
    }
    static Stream<Arguments> dynamicParaTest() throws InterruptedException { // 必须是静态方法！！
        // 构造动态参数
        String[] arr = new String[5];
        for (int i = 0; i < arr.length; i++) {
            Thread.sleep(200);
            arr[i] = System.currentTimeMillis()+""; // 拼接为字符串类型
        }
        return Stream.of(
                Arguments.arguments(arr[0],20),
                Arguments.arguments(arr[1],18),
                Arguments.arguments(arr[2],29),
                Arguments.arguments(arr[3],6),
                Arguments.arguments(arr[4],33)
        );
    }
}
