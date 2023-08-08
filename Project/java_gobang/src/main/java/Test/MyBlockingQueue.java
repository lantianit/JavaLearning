package Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author zh
 * @Date 2023/8/8 9:37
 * @PackageName:Test
 * @ClassName: MyBlockingQueue
 * @Description: TODO
 * @Version 1.0
 */
public class MyBlockingQueue {
    private int[] items = new int[1000];
    private volatile int head = 0;
    private volatile int tail = 0;
    private volatile int size = 0;

    public void put(int elem) throws InterruptedException {
        synchronized (this){
            while(size >= items.length){
                this.wait();
            }
        }
        items[tail] = elem;
        tail++;
        if(tail >= items.length){
            tail = 0;
        }
        size++;
        this.notify();
    }

    public Integer take() throws InterruptedException {
        synchronized (this) {
            while(size == 0){
                this.wait();
            }
        }

        int ret = items[head];
        head++;
        if(head >= items.length){
            head = 0;
        }
        size--;
        this.notify();
        return ret;
    }

}
