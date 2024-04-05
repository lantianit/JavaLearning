package ioc;

public class App {
    public static void main(String[] args) {
        Tire tire = new Tire(30, "粉色");
        Bottom bottom = new Bottom(tire);
        Framework framework = new Framework(bottom);
        Car car = new Car(framework);
        car.init();
    }
}
