public class CreatThread_five {
    private static class MyThread1 extends Thread{
        @Override
        public void run() {
            System.out.println("线程1");
        }
    }

    private static class MyThread2 implements Runnable{
        @Override
        public void run() {
            System.out.println("我是线程2");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new MyThread1();
        Thread t2 = new Thread(new MyThread2());
        Thread t3 = new Thread(){
            @Override
            public void run() {
                System.out.println("我是线程3，通过内部类创建Thread 子对象");
            }
        };
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是线程4，通过内部类创建Runable接口");
            }
        });
        Thread t5 = new Thread(()-> System.out.println("我是线程5"));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}
