import java.util.Scanner;

public class ThreadVolatile {
    static class Counter {
        public int flag = 0;
//        public volatile int flag = 0;
    }
        public static void main(String[] args) {
            Counter counter = new Counter();
            Thread t1 = new Thread(() -> {
                while (counter.flag == 0) {
                    // do nothing
                }
                System.out.println("循环结束!");
            });
            Thread t2 = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("输入一个整数:");
                counter.flag = scanner.nextInt();
            });
            t1.start();
            t2.start();
        }
}
