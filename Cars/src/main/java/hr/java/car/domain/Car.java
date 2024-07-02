package hr.java.car.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Car implements Serializable {

    public static final Integer CAR_TYPE_CATEGORY_ELECTRIC = 2;
    public static final Integer CAR_TYPE_CATEGORY_FUEL = 1;


    private String manufacturer;
    private String type;
    private BigDecimal price;

    private LocalDate manufacturingDate;

    public Car(String manufacturer, String type, BigDecimal price, LocalDate manufacturingDate) {
        this.manufacturer = manufacturer;
        this.type = type;
        this.price = price;
        this.manufacturingDate = manufacturingDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return Objects.equals(manufacturer, car.manufacturer) && Objects.equals(type, car.type) && Objects.equals(price, car.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, type, price);
    }

    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", manufacturingDate=" + manufacturingDate +
                '}';
    }
}
