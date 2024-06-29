package hr.java.car.comparator;

import hr.java.car.domain.Car;

import java.util.Comparator;

public class CarPriceComparator implements Comparator<Car> {

    @Override
    public int compare(Car c1, Car c2) {

        return c1.getPrice().compareTo(c2.getPrice());

    }
}
