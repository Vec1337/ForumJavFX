package hr.java.car.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FuelCar extends Car implements Serializable {

    private Integer tankCapacity;

    public FuelCar(Integer tankCapacity, String manufacturer, String type, BigDecimal price, LocalDate manufacturingDate) {
        super(manufacturer, type, price, manufacturingDate);
        this.tankCapacity = tankCapacity;
    }

    public Integer getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(Integer tankCapacity) {
        this.tankCapacity = tankCapacity;
    }
}
