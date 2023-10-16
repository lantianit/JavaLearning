package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 使用 FutrueTask 等待线程池执行完全部任务
 */
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 创建任务
        FutureTask<Integer> task1 = new FutureTask<>(() -> {
            System.out.println("Task 1 start");
            Thread.sleep(2000);
            System.out.println("Task 1 end");
            return 1;
        });
        FutureTask<Integer> task2 = new FutureTask<>(() -> {
            System.out.println("Task 2 start");
            Thread.sleep(3000);
            System.out.println("Task 2 end");
            return 2;
        });
        FutureTask<Integer> task3 = new FutureTask<>(() -> {
            System.out.println("Task 3 start");
            Thread.sleep(1500);
            System.out.println("Task 3 end");
            return 3;
        });
        // 提交三个任务给线程池
        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);

        // 等待所有任务执行完毕并获取结果
        int result1 = task1.get();
        int result2 = task2.get();
        int result3 = task3.get();
        System.out.println("Do main thread.");
    }
}
