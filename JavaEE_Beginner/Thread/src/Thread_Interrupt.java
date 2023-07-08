public class Thread_Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("转账开始");
            while (!Thread.interrupted()) {
                System.out.println("正在转账");
            }
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        System.out.println("转账出现问题，停止");
    }
}
