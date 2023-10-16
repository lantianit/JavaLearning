import java.util.concurrent.locks.LockSupport;

public class LockSupportExample {
    public static void main(String[] args) throws InterruptedException {

        synchronized (LockSupportExample.class) {
            // ...
            synchronized (LockSupportExample.class) {
                // ...
            }
        }


        Thread t1 = new Thread(() -> {
            System.out.println("线程1被休眠了");
            LockSupport.park();
            System.out.println("线程1被唤醒继续执行了");
        }, "线程1");
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println("线程2被休眠了");
            LockSupport.park();
            System.out.println("线程2被唤醒继续执行了");
        }, "线程2");
        t2.start();

        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(1000); // 不会释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("唤醒线程2");
            LockSupport.unpark(t2);
        }, "线程2");
        t3.setPriority(10);
        t3.start();

        Object lock = new Object();
        synchronized (lock) {
            lock.wait(); // 释放锁
        }


    }
}
