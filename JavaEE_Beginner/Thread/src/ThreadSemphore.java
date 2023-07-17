import java.util.concurrent.Semaphore;

/**
 * @Author zh
 * @Date 2023/7/17 11:14
 * @PackageName:PACKAGE_NAME
 * @ClassName: ThreadSemphore
 * @Description: TODO
 * @Version 1.0
 */
public class ThreadSemphore {

    static class Count{
        Semaphore semaphore = new Semaphore(4);
        int count = 0;
        void add() throws InterruptedException {
            semaphore.acquire();
            count++;
            semaphore.release();
        }
    }




    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(4);
        Runnable runnable = new Runnable() {
        @Override
        public void run() {
        try {
        System.out.println("申请资源");
semaphore.acquire();
        System.out.println("我获取到资源了");
       Thread.sleep(1000);
        System.out.println("我释放资源了");
        semaphore.release();
         } catch (InterruptedException e) {
       e.printStackTrace();
        }
        }
        };
        for (int i = 0; i < 20; i++) {
    Thread t = new Thread(runnable);
      t.start();
        }
        }

        }
