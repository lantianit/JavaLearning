package com.bite.demo.ioc.v2;

public class Main {
    public static void main(String[] args) {
        Tire tire = new Tire(19,"red");
        Bottom bottom = new Bottom(tire);
        Framework framework = new Framework(bottom);
        Car car = new Car(framework);
        car.run();
    }
}
