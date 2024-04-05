package old;

public class Framework {
    private Bottom bottom;

    public Framework(int size) {
        bottom = new Bottom(size);
    }

    public void init() {
        System.out.println("执行了 framework init 方法");
        // 依赖底盘
        bottom.init();
    }
}
