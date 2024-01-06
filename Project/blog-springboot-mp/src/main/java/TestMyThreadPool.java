import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class MyThreadPool {
    BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>();

    public void submit(Runnable runnable) {
        this.queue.add(runnable);
    }

    public MyThreadPool(int n) {
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(() -> {
                while (true) {
                    Runnable runnable = null;
                    try {
                        runnable = queue.take();
                        runnable.run();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            t.start();
        }
    }
}

public class TestMyThreadPool {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            myThreadPool.submit(()->{
                System.out.println(finalI);
            });
        }
    }
}
