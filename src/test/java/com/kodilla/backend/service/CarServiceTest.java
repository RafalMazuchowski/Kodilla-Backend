package com.kodilla.backend.service;

import com.kodilla.backend.domain.Car;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarService carService;

    @Transactional
    @Test
    public void testSaveAndFindAll() {
        //Given
        int initialNumberOfCars = carService.getAllCars().size();
        Car car1 = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        Car car2 = new Car(2L, "BMW", "M4", true, Date.valueOf("1901-06-15"));

        //When
        carService.saveCar(car1);
        carService.saveCar(car2);
        List<Car> cars = carService.getAllCars();
        int numberOfCars = cars.size();


        //Then
        assertEquals(initialNumberOfCars + 2, numberOfCars);
        assertEquals(car1.getModel(), "M3");
        assertEquals(car2.getModel(), "M4");

        //CleanUp
        car1.setCarId(cars.get(initialNumberOfCars).getCarId());
        car2.setCarId(cars.get(initialNumberOfCars).getCarId());
        carService.deleteCar(car1.getCarId());
        carService.deleteCar(car2.getCarId());
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Car car1 = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        carService.saveCar(car1);
        car1.setCarId(carService.getAllCars().get(carService.getAllCars().size() - 1).getCarId());

        //When
        Car foundCar = carService.getCar(car1.getCarId());

        //Then
        assertEquals(foundCar, car1);

        //CleanUp
        carService.deleteCar(car1.getCarId());
    }

    @Transactional
    @Test
    public void testDelete() {
        //Given
        int initialNumberOfCars = carService.getAllCars().size();
        Car car1 = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        carService.saveCar(car1);
        car1.setCarId(carService.getAllCars().get(carService.getAllCars().size() - 1).getCarId());

        //When
        carService.deleteCar(car1.getCarId());
        List<Car> cars = carService.getAllCars();
        int numberOfCars = cars.size();

        //Then
        assertEquals(initialNumberOfCars, numberOfCars);
        assertFalse(cars.contains(car1));
    }
}