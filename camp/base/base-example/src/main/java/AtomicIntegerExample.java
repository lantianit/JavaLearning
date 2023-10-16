import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 使用示例
 */
public class AtomicIntegerExample {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        // 增加计数器的值
        counter.incrementAndGet(); // 增加后的值为 1
        counter.addAndGet(5);      // 增加后的值为 6

        // 减少计数器的值
        counter.decrementAndGet(); // 减少后的值为 5
        counter.addAndGet(-3);     // 减少后的值为 2

        // 获取当前计数器的值
        int currentValue = counter.get(); // 当前值为 2

        // 比较并交换操作
        boolean swapped = counter.compareAndSet(2, 10); // 将值从 2 替换为 10
        int newValue = counter.get(); // 新值为 10
        System.out.println("Swapped: " + swapped); // 输出 true，表示成功替换

        // 自增操作，返回自增前的值
        int originalValue = counter.getAndIncrement(); // 原始值为 10，然后自增为 11

        System.out.println("Original Value: " + originalValue);
        System.out.println("counter Value: " + counter);
    }
}
