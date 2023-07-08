public class Thread_setDaemon {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("hello");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "这是俺的线程");
        // 使用 setDaemon 设置成后台线程.
        // 设置操作得在 start 之前. 如果线程启动了, 就改不了了!!
        t.setDaemon(true);

        t.start();
        System.out.println("main 线程执行结束. ");
    }
}