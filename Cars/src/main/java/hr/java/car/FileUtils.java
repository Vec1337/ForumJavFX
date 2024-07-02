package hr.java.car;

import hr.java.car.domain.*;
import org.example.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static final Integer NUMBER_OF_LINES_PER_CAR = 6;
    private static final String CARS_TEXT_FILE_NAME = "dat/cars.txt";
    private static final String CARS_SERIALIZATION_FILE_NAME = "dat/serialized-cars.txt";

    public static List<Car> getCarsFromFile() {

        List<Car> carsList = new ArrayList<>();

        File carsFile = new File(CARS_TEXT_FILE_NAME);

        try (BufferedReader reader = new BufferedReader(new FileReader(carsFile))) {

            Optional<String> carManufacturerOptional = Optional.empty();

            while((carManufacturerOptional = Optional.ofNullable(reader.readLine())).isPresent()) {

                Optional<Car> newCarOptional = Optional.empty();

                String carManufacturer = carManufacturerOptional.get();
                String carType = reader.readLine();
                String carPriceString = reader.readLine();
                BigDecimal carPrice = new BigDecimal(carPriceString);
                String manufacturerDateString = reader.readLine();
                LocalDate manufacturerDate = LocalDate.parse(manufacturerDateString, Main.DATE_TIME_FORMAT);
                String carTypeCategoryString = reader.readLine();
                Integer carTypeCategory = Integer.parseInt(carTypeCategoryString);

                if(CarTypeCateogry.FUEL.getCode().equals(carTypeCategory)) {
                    String fuelCapacityString = reader.readLine();
                    Integer fuelCapacity = Integer.parseInt(fuelCapacityString);


                    newCarOptional = Optional.of(new FuelCar(fuelCapacity, carManufacturer, carType, carPrice, manufacturerDate));

                }
                else if(CarTypeCateogry.ELECTRIC.getCode().equals(carTypeCategory)) {
                    String batteryCapacityString = reader.readLine();
                    Integer batteryCapacity = Integer.parseInt(batteryCapacityString);
                    Battery battery = new Battery(batteryCapacity);

                    newCarOptional = Optional.of(new ElectricCar(battery, carManufacturer, carType, carPrice, manufacturerDate));
                }

                if(newCarOptional.isPresent()) {
                    carsList.add(newCarOptional.get());
                }
            }

        } catch (IOException e) {
            logger.error("Dogodila se pogreska kod citanja datoteke!", e);
            System.out.println("Dogodila se pogreska kod citanja datoteke!");
        }

        return carsList;
    }

    public static void serializeCars(List<Car> carsList) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARS_SERIALIZATION_FILE_NAME))) {

            oos.writeObject(carsList);

        } catch(IOException e) {
            logger.error("Serialization errror!", e);
            System.out.println(e);
        }



    }


}
