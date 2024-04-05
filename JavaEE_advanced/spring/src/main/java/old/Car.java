package old;

public class Car {
    private Framework framework;

    public Car(int size) {
        framework = new Framework(size);
    }

    public void init() {
        System.out.println("执行了 car init 方法");
        // 依赖车身
        framework.init();
    }
}
