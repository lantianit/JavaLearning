package jvm;

public class JavaStackExample {
    public static void main(String[] args) {
        int num = 10;
        int num2 = 10;
        incrment(num, num2);
        Integer it = Integer.valueOf(3);
        Integer it2 = Integer.valueOf(4);
    }

    private static void incrment(int num, int num2) {
        String str = "Hello, JavaCN.";
        int num3 = num + num2;
        System.out.println(num3);
    }
}
