package com.bite.captcha;

class WaitNotifyExample {
    
    private final Object lock = new Object();
    private boolean flag = false;
    
    public void producer() {
        synchronized (lock) {
            while (flag) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("producer生产数据");
            flag = true;
            lock.notify();
        }
    }
    
    public void consumer() {
        synchronized (lock) {
            while (!flag) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("consumer消费数据");
            flag = false;
            lock.notify();
        }
    }

    public static void main(String[] args) {
        WaitNotifyExample waitNotifyExample = new WaitNotifyExample();
        waitNotifyExample.consumer();
        new Thread(()->{
            waitNotifyExample.producer();
        }).start();
    }
    
}