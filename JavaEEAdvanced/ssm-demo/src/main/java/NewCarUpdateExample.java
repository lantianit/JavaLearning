public class NewCarUpdateExample {
    public static void main(String[] args) {
        Car car = new Car(20);
        car.run();
    }
/**
* 汽⻋对象
*/
    static class Car {
        private Framework framework;
        public Car(int size) {
        framework = new Framework(size);
        }
            public void run() {
            // 依赖⻋身
            framework.init();
        }
    }
/**
* ⻋身类
*/
    static class Framework {
        private Bottom bottom;
        public Framework(int size) {
        bottom = new Bottom(size);
        }
        public void init() {
        // 依赖底盘
        bottom.init();
        }
    }
/**
* 底盘类
*/
    static class Bottom {
        private Tire tire;
        public Bottom(int size) {
        tire = new Tire(size);
        }
        public void init() {
            // 依赖轮胎
            tire.init();
        }
    }
/**
* 轮胎类
*/
    static class Tire {
    // 尺⼨
        private int size;
        public Tire(int size) {
        this.size = size;
        }
        public void init() {
            System.out.println("轮胎尺⼨：" + size);
        }
    }
}