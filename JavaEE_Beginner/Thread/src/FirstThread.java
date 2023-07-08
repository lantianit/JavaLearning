import static java.lang.Thread.sleep;

public class FirstThread {
    private static class Mythread extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Mythread t1 = new Mythread();
        Mythread t2 = new Mythread();
        Mythread t3 = new Mythread();
        t1.start();
        t2.start();
        t3.start();

        while(true){
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        }

    }

}
