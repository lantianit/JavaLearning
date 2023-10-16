package thread;

public class CreateThreadDemo {
    public static void main(String[] args) {
        // 创建一个新的线程
        Thread thread = new Thread(() -> {
            System.out.println("线程开始执行 ThreadName：" +
                    Thread.currentThread().getName());
            try {
                Thread.sleep(3000); // 模拟线程执行耗时任务
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程执行完成 ThreadName：" +
                    Thread.currentThread().getName());
        });
        // 启动线程
        thread.start();
        thread.start();
        System.out.println("主线程继续执行 ThreadName：" +
                Thread.currentThread().getName());
    }
}