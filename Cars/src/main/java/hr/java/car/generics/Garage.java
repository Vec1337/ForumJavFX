package hr.java.car.generics;

import hr.java.car.domain.Car;

import java.util.List;

public class Garage<T extends Car> {
    private List<T> carsInGarage;

    public Garage() {

    }

    public Garage(List<T> carsInGarage) {
        this.carsInGarage = carsInGarage;
    }

    public List<T> getCarsInGarage() {
        return carsInGarage;
    }

    public void setCarsInGarage(List<T> carsInGarage) {
        this.carsInGarage = carsInGarage;
    }
}
