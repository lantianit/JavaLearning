package juc;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ThreadPoolExecutor semaphoreThread = new ThreadPoolExecutor(10, 50, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));
        for (int i = 0; i < 5; i++) {
            semaphoreThread.execute(() -> {
                try {
                    // 堵塞获取许可
                    semaphore.acquire();
                    System.out.println("Thread：" + Thread.currentThread().getName() + " 时间：" + LocalDateTime.now());
                    TimeUnit.SECONDS.sleep(2);
                    // 释放许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
