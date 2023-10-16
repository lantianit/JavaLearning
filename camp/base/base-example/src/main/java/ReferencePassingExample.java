public class ReferencePassingExample {
    public static void main(String[] args) {
        Integer number = new Integer(10);
        method(number);
        System.out.println("number：" + number); // Output：10
    }

    public static void method(Integer number) {
        number = 20;
    }
}