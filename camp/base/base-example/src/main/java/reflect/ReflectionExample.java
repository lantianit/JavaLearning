package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射示例
 */
class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println("你好，我是 " + name + "，我今年 " + age + " 岁了。");
    }
}

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        // 获取Person类的Class对象
        Class personClass = Class.forName("reflect.Person");
//        Class personClass = Person.class;

        // 获取类的名称
        System.out.println("类名: " + personClass.getName());

        // 获取类的所有字段
        System.out.println("字段列表：");
        Field[] fields = personClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("  " + field.getName());
        }

        // 获取类的所有方法
        System.out.println("方法列表：");
        Method[] methods = personClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("  " + method.getName());
        }

        // 使用反射创建对象
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true); // 如果构造函数是私有的，需要设置为可访问
        // 创建对象
        Person person = constructor.newInstance("javacn", 18);
        person.sayHello();
    }
}
