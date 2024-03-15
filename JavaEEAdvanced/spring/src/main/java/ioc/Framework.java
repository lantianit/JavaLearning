package ioc;

public class Framework {
    private Bottom bottom;

    public Framework(Bottom bottom) {
        this.bottom = bottom;
    }

    public void init() {
        System.out.println("执行 Framework");
        // 依赖底盘
        bottom.init();
    }
}
