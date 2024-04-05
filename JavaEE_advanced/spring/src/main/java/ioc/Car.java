package ioc;

public class Car {
    private Framework framework;

    public Car(Framework framework) {
        this.framework = framework;
    }

    public void init() {
        System.out.println("执行 Car");
        // 依赖车身
        framework.init();
    }
}
