package ioc;

public class Bottom {
    private Tire tire;

    public Bottom(Tire tire) {
        this.tire = tire;
    }

    public void init() {
        System.out.println("执行 Bottom");
        // 依赖轮胎
        tire.init();
    }
}
