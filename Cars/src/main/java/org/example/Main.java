package org.example;


import hr.java.car.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);

        Car[] cars = getCars(inputScanner);


        Car theCheapestCar = determineTheCheapestCar(cars);

        System.out.println("Najjeftiniji automobil je: " + theCheapestCar.getManufacturer() + " " + theCheapestCar.getType() + " = " + theCheapestCar.getPrice() + " EUR");


        Car theOldestCar = determineTheOldestCar(cars);

        System.out.println("Najstariji automobil je: " + theCheapestCar.getManufacturer() + " " + theCheapestCar.getType() + " = " + theCheapestCar.getPrice() + " EUR, a proizveden je" + theOldestCar.getManufacturer().formatted(DATE_TIME_FORMAT));


        Car purchasedCar = purchaseCar(cars, inputScanner);

        System.out.println("Cestitamo kupili ste sljedeci auto:\n" + purchasedCar);

        System.out.println("Lista svih automobila: ");

        for(Car car : cars) {

            System.out.println("Proizvodac: " + car.getManufacturer());
            System.out.println("Tip: " + car.getType());
            System.out.println("Cijena: " + car.getPrice());
            System.out.println("Datum proizvodnje: " + car.getManufacturingDate());

            if(car instanceof ElectricCar ec) {
                System.out.println("Kapacitet baterije elektricnog automobila: " + ec.getBattery().capcity());
            } else if(car instanceof FuelCar fc) {
                System.out.println("Kapacitet spremnika goriva konvencionalnog automobila: " + fc.getTankCapacity());
            }
        }

    }

    private static Car purchaseCar(Car[] cars, Scanner inputScanner) {
        Integer chosenCarIndex = null;

        do {

            System.out.println("Unesite automobile koji zelite kupiti: ");

            for (int i = 1; i <= cars.length; i++) {
                System.out.println(i + ") Manufacturer: " + cars[i-1].getManufacturer() + " Type: " + cars[i-1].getType() + " Price: " + cars[i-1].getPrice() + " Date: " + cars[i-1].getManufacturingDate().format(DATE_TIME_FORMAT));
            }

            System.out.print("Odabit >> ");
            chosenCarIndex = inputScanner.nextInt();

            if(chosenCarIndex < 1 || chosenCarIndex > cars.length) {
                System.out.println("Neispravan unos broja automobila, molimo pokusajte ponovno!");
            }

        } while(chosenCarIndex < 1 || chosenCarIndex > cars.length);

        Car purchasedCar = cars[chosenCarIndex-1];
        return purchasedCar;
    }

    private static Car determineTheCheapestCar(Car[] cars) {
        Car theCheapestCar = cars[0];

        for(int i = 1; i < cars.length; i++) {
            if(cars[i].getPrice().compareTo(theCheapestCar.getPrice()) < 0) {
                theCheapestCar = cars[i];
            }
        }
        return theCheapestCar;
    }

    private static Car determineTheOldestCar(Car[] cars) {
        Car theOldestCar = cars[0];

        for(int i = 1; i < cars.length; i++) {
            if(cars[i].getManufacturingDate().isBefore(theOldestCar.getManufacturingDate())) {
                theOldestCar = cars[i];
            }
        }

        return theOldestCar;
    }

    private static Car[] getCars(Scanner inputScanner) {
        System.out.print("Unesi broj automobila koje zelite unijeti: ");
        Integer numberOfCars = inputScanner.nextInt();
        inputScanner.nextLine();

        Car[] cars = new Car[numberOfCars];

        for(int i = 0; i < numberOfCars; i++) {
            enterCar(i, inputScanner, cars);
        }
        return cars;
    }

    private static void enterCar(int i, Scanner inputScanner, Car[] cars) {

        Boolean duplicateCars = false;
        Car newCar = null;

        do {

            System.out.print("Unesite naziv proizvodaca " + (i + 1) + ". automobila: ");
            String carManufacturer = inputScanner.nextLine();

            System.out.print("Unesite tip " + (i + 1) + ". automobila: ");
            String carType = inputScanner.nextLine();

            System.out.print("Unesite cijenu " + (i + 1) + ". automobila: ");
            BigDecimal carPrice = inputScanner.nextBigDecimal();
            inputScanner.nextLine();

            System.out.print("Unesite datum poizvodnje automobila (dd.MM.ggggg.):");
            String manufacturingDateString = inputScanner.nextLine();
            LocalDate manufacturingDate = LocalDate.parse(manufacturingDateString, DATE_TIME_FORMAT);

            System.out.println("Odeberite vrstu pogona automobila (1 - FUEL, ostalo - ELECTRIC CAR): ");
            Integer carTypeCategory = inputScanner.nextInt();

            if(carTypeCategory == Car.CAR_TYPE_CATEGORY_FUEL) {
                System.out.print("Unesite kapacitet spremnika goriva: ");
                Integer fuelTankCapacity = inputScanner.nextInt();

                newCar = new FuelCar(fuelTankCapacity, carManufacturer, carType, carPrice, manufacturingDate);

            } else {
                System.out.print("Unesite kapacitet baterije (Ah): ");
                Integer batteryCapacity = inputScanner.nextInt();

                Battery electricCarBattery = new Battery(batteryCapacity);

                newCar = new ElectricCar(electricCarBattery, carManufacturer, carType, carPrice, manufacturingDate);
            }



            duplicateCars = checkDuplicateCars(newCar, cars);

            if(duplicateCars) {
                System.out.println("Unijeli ste duplikat auta, molimo unesite drugacije vrijednosti.");
            }

        } while(duplicateCars);

        cars[i] = newCar;
    }

    private static Boolean checkDuplicateCars(Car carToCheck, Car[] existingCars) {
        for(Car enteredCar : existingCars) {
            if(carToCheck.equals(enteredCar)) {
                return true;
            }
        }

        return false;
    }
}