import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

// 这个类表示一个任务
class MyTask implements Comparable<MyTask> {

    private Runnable runnable;
    private long time;

    public MyTask(Runnable runnable,long delay) {
        this.runnable = runnable;
        this.time = System.currentTimeMillis() + delay;
    }

    @Override
    public int compareTo(MyTask o) {
        return (int)(this.time-o.time);
    }
}

class MyTimer {
    private BlockingQueue<MyTask> queue = new PriorityBlockingQueue<>();

    private Object locker = new Object();

    public MyTimer() {
        // 创建一个扫描线程.
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    synchronized (locker) {
                        // 取出队首元素
                        MyTask task = queue.take();
                        // 假设当前时间是 2:30, 任务设定的时间是 2:30, 显然就要执行任务了.
                        // 假设当前时间是 2:30, 任务设定的时间是 2:29, 也是到点了, 也要执行任务.
                        long curTime = System.currentTimeMillis();
                        if (curTime >= task.getTime()) {
                            // 到点了, 改执行任务了!!
                            task.getRunnable().run();
                        } else {
                            // 还没到点
                            queue.put(task);
                            // 没到点, 就等待
                            locker.wait(task.getTime() - curTime);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void schedule(Runnable runnable, long after) throws InterruptedException {

    }

}

class ThreadMyTimer {
    public static void main(String[] args) throws InterruptedException {
        MyTimer timer = new MyTimer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("时间到1!");
            }
        }, 3000);
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("时间到2!");
            }
        }, 4000);
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("时间到3!");
            }
        }, 5000);
        System.out.println("开始计时");

        ArrayDeque<String> a = new ArrayDeque<>();
        a.peekLast();
    }
}