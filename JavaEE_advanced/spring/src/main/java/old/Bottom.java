package old;

public class Bottom {
    private Tire tire;

    public Bottom(int size) {
        tire = new Tire(size);
    }

    public void init() {
        System.out.println("执行了 bottom init 方法");
        // 依赖轮胎
        tire.init();
    }
}
