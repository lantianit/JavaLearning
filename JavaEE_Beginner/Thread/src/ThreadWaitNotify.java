public class ThreadWaitNotify {
    static class WaitTask implements Runnable {
        private Object locker;
        public WaitTask(Object locker) {
            this.locker = locker;
        }
        @Override
        public void run() {
            synchronized (locker) {
                while (true) {
                    try {
                        System.out.println("wait 开始");
                        locker.wait();
                        System.out.println("wait 结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    static class NotifyTask implements Runnable {
        private Object locker;
        public NotifyTask(Object locker) {
            this.locker = locker;
        }
        @Override
        public void run() {
            synchronized (locker) {
                System.out.println("notify 开始");
                locker.notify();
                System.out.println("notify 结束");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Object locker = new Object();
        Thread t1 = new Thread(new WaitTask(locker));
        Thread t2 = new Thread(new NotifyTask(locker));
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}
