package jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 堆溢出演示
 * -Xmx10m
 */
public class HeapOOMExample {
    static class MyOOMClass {
        // 1M 空间（M KB Byte）
        private byte[] bytes = new byte[1 * 1024 * 1024];
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(15 * 1000);
        ExecutorService service = Executors.newCachedThreadPool();
        Object[] objects = new Object[15];
        for (int i = 0; i < 15; i++) {
            final int finalI = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(finalI * 200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MyOOMClass myOOMClass = new MyOOMClass();
                    objects[finalI] = myOOMClass;
                    System.out.println("任务：" + finalI);
                }
            });
        }
    }

}
