import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

class ThreadLocaDemo {

    private static ThreadLocal<String> localVar = new ThreadLocal<String>();

    static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + localVar.get());
        //清除本地内存中的本地变量
        localVar.remove();
    }
    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            public void run() {
                ThreadLocaDemo.localVar.set("local_A");
                print("A");
                //打印本地变量
                System.out.println("after remove : " + localVar.get());

            }
        },"A").start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            public void run() {
                ThreadLocaDemo.localVar.set("local_B");
                print("B");
                System.out.println("after remove : " + localVar.get());

            }
        },"B").start();
    }
}




class ObjectCommunicate {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        // 创建线程并执行
        new Thread(() -> {
            System.out.println("线程1: 开始执行");
            synchronized (lock) {
                try {
                    System.out.println("线程1: 进入等待");
                    lock.wait();
                    System.out.println("线程1: 继续执行");
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程1: 执行完成");
            }
        }).start();
        Thread.sleep(1090);
        synchronized (lock)

        {
            // 唤醒线程
            System.out.println("执行 notifyAl1()");
            lock.notifyAll();
        }
    }
}

class ConditionCommunicate {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Condition condition2 = lock.newCondition();
        lock.lock();
        try {
            condition.await();
            condition.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}

class LockSupportCommunicate {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("线程1");
        },"线程1");
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("唤醒线程1");
            LockSupport.unpark(t1);
        },"线程2");
        t2.start();
    }
}

class MyBlockingQueue {
    private int[] items = new int[1000];
    private int tail = 0;
    private int head = 0;
    private int size = 0;

    public void put(int elem) throws InterruptedException {
        synchronized (this) {
            while (size == items.length) {
                this.wait();
            }
            items[tail++] = elem;
            tail++;
            if (tail >= items.length) {
                tail = 0;
            }
            size++;
            this.notify();
        }
    }
    public int take() throws InterruptedException {
        synchronized (this) {
            while (size == 0) {
                this.wait();
            }
            int ret = items[head++];
            if (head >= items.length) {
                head = 0;
            }
            size--;
            this.notify();
            return ret;
        }
    }
}

class MyTask implements Comparable<MyTask> {
    private Runnable runnable;
    private long timeStamp;
    public MyTask(Runnable runnable,long delay) {
        this.runnable = runnable;
        this.timeStamp = System.currentTimeMillis() + delay;
    }
    public Runnable getRunnable() {
        return runnable;
    }

    public long getTime() {
        return timeStamp;
    }
    @Override
    public int compareTo(MyTask o) {
        return 0;
    }
}

class MyTimer {
    private BlockingQueue<MyTask> queue = new PriorityBlockingQueue<>();

    private Object locker = new Object();

    public MyTimer() {
        Thread thread = new Thread(() -> {
           while (true) {
               try {
                   synchronized (locker) {
                       MyTask myTask = queue.take();
                       long curTime = System.currentTimeMillis();
                       if (curTime >= myTask.getTime()) {
                           myTask.getRunnable().run();
                       } else {
                           queue.put(myTask);
                           locker.wait(myTask.getTime() - curTime);
                       }
                   }
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });
        thread.start();
    }
    public void schedule(Runnable runnable, long after) throws InterruptedException {
        synchronized (locker) {
            MyTask myTask = new MyTask(runnable, after);
            queue.put(myTask);
            locker.notify();
        }
    }
}