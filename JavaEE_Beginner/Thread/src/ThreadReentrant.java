public class ThreadReentrant {
    static class Counter {
        public int count = 0;
        void increase() {
            synchronized (this){
                count++;
            }
        }
        void increase2() {
            synchronized (this){
                increase();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 50000; i++){
                counter.increase2();
            }
            System.out.println("t1结束");
        });
        Thread t2 = new Thread(()->{
            for(int i = 0; i < 50000; i++){
                counter.increase2();
            }
            System.out.println("t2结束");
        });
        System.out.println("开始");
        System.out.println(counter.count);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("全部结束");
        System.out.println(counter.count);
    }
}
