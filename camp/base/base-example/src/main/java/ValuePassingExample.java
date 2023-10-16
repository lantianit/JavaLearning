public class ValuePassingExample {
    public static void main(String[] args) {
        int number = 10;
        method(number);
        System.out.println("number: " + number); // Output：10
    }

    public static void method(int num) {
        num = 20; // 修改num的值，不会影响原始变量的值
    }
}