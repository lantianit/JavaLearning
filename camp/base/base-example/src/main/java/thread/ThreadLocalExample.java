package thread;

public class ThreadLocalExample {
    // 创建一个ThreadLocal对象
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 设置线程局部变量的值
        threadLocal.set(123);

        // 启动一个新线程
        Thread thread = new Thread(() -> {
            // 在新线程中获取线程局部变量的值
            Integer value = threadLocal.get();
            System.out.println("Thread Local Value: " + value); // 输出：Thread Local Value: null
        });
        thread.start();
    }
}