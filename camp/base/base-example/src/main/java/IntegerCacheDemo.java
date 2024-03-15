/**
 * 高速缓存示例
 */
public class IntegerCacheDemo {
    public static void main(String[] args) {
        Integer a = 10; // 缓存范围内的整数，从缓存中获取
        Integer b = 10; // 缓存范围内的整数，从缓存中获取

        Integer c = 129; // 超出缓存范围，创建新的对象
        Integer d = 129; // 超出缓存范围，创建新的对象

        System.out.println("a == b: " + (a == b)); // 输出：a == b: true，因为a和b引用的是缓存中同一个对象
        System.out.println("c == d: " + (c == d)); // 输出：c == d: false，因为c和d是两个独立的对象
    }
}