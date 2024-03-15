import java.util.Random;

class ThreadDemo {
    private static class MyThread extends Thread {
        @Override
        public void run() {
            Random random = new Random();
            while (true) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(random.nextInt());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

class CreateThread {
    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("继承Thread类 创建线程");
        }
    }
    private static class MyThread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("实现Runnable接口，创建线程");
        }
    }

    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        Thread t2 = new Thread(new MyThread2());
        Thread t3 = new Thread() {
            public void run() {
                System.out.println("内部类创建子对象");
            }
        };
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("内部类实现Runnable");
            }
        });
        Thread t5 = new Thread(() -> System.out.println("lamba创建runnable"));
    }
}
