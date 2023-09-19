public class NewCarExample {
    public static void main(String[] args) {
    Car car = new Car();
    car.init();
    }
   /**
   * 汽⻋对象
   */
    static class Car {
        public void init() {
            // 依赖⻋身
            Framework framework = new Framework();
            framework.init();
        }
    }
   /**
   * ⻋身类
   */
    static class Framework {
        public void init() {
            // 依赖底盘
            Bottom bottom = new Bottom();
            bottom.init();
        }
    }
/**
* 底盘类
*/
    static class Bottom {
        public void init() {
            // 依赖轮胎
            Tire tire = new Tire();
            tire.init();
        }
    }
/**
* 轮胎类
*/
    static class Tire {
         // 尺⼨
        private int size = 30;
        public void init() {
            System.out.println("轮胎尺⼨：" + size);
        }
    }
}