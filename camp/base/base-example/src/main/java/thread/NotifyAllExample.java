package thread;

/**
 * notifyAll 示例代码
 */
public class NotifyAllExample {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        // 创建线程 1 并执行
        new Thread(() -> {
            System.out.println("线程1：开始执行");
            synchronized (lock) {
                try {
                    System.out.println("线程1：进入等待");
                    lock.wait();
                    System.out.println("线程1：继续执行");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程1：执行完成");
            }
        }).start();

        // 创建线程 2 并执行
        new Thread(() -> {
            System.out.println("线程2：开始执行");
            synchronized (lock) {
                try {
                    System.out.println("线程2：进入等待");
                    lock.wait();
                    System.out.println("线程2：继续执行");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程2：执行完成");
            }
        }).start();

        Thread.sleep(1000);
        synchronized (lock) {
            System.out.println("执行 notifyAll()");
            lock.notifyAll();
        }

    }
}
