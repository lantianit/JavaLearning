package clone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
class Person {
    private int id;
    private String name;
    private Address address;

    public Person() {
    }

    public Person(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    // 忽略 set、get 方法
}

@Data
class Address {
    private int id;
    private String city;

    public Address() {
    }

    public Address(int id, String city) {
        this.id = id;
        this.city = city;
    }
    // 忽略 set、get 方法
}

public class DeepCloneExample {
    public static void main(String[] args) throws JsonProcessingException {
        // 创建对象
        Address address = new Address(1, "西安");
        Person person = new Person(1, "javacn.site", address);
        // 深克隆
        ObjectMapper mapper = new ObjectMapper();
        // 序列化
        String jsonString = mapper.writeValueAsString(person);
        // 反序列化
        Person clonePerson = mapper.readValue(jsonString, Person.class);
        // 修改克隆对象
        clonePerson.getAddress().setCity("长安");
        System.out.println("原对象：" + person);
        System.out.println("克隆对象：" + clonePerson);


        Object object = null;
        System.out.println(object.hashCode());

    }
}