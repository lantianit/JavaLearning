import lombok.Data;

/**
 * Java 中实现克隆示例（浅克隆）
 */
@Data
class Person implements Cloneable {
    private String name;
    private int age;
    private Address address;

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    // Getter and Setter methods...

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone();
        person.setAddress(this.address.clone());
        return person;
    }
}

@Data
class Address implements Cloneable {
    private String name;

    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}

public class CloneExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address();
        address.setName("西安");
        Person person = new Person("javacn", 18, address);
        // 进行克隆
        Person clonedPerson = (Person) person.clone();
        clonedPerson.getAddress().setName("北京");
        System.out.println(person);
        System.out.println(clonedPerson);
    }
}