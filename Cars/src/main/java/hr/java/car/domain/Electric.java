package hr.java.car.domain;

public sealed interface Electric permits ElectricCar{

    void turnOn();

    void turnOff();

}
