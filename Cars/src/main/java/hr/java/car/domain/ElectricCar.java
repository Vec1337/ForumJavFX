package hr.java.car.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public non-sealed class  ElectricCar extends Car implements Electric{

    private Battery battery;
    private Boolean turnedOn;

    public ElectricCar(Battery battery, String manufacturer, String type, BigDecimal price, LocalDate manufacturingDate) {
        super(manufacturer, type, price, manufacturingDate);
        this.turnedOn = false;
        this.battery = battery;
    }

    @Override
    public void turnOn() {
        turnedOn = true;
    }

    @Override
    public void turnOff() {
        turnedOn = false;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }
}
