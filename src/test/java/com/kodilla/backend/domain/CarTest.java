package com.kodilla.backend.domain;

import com.kodilla.backend.domain.repository.CarDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CarTest {

    @Autowired
    private CarDao carDao;

    @Transactional
    @Test
    public void testSaveAndFindAll() {
        //Given
        int initialNumberOfCars = carDao.findAll().size();
        Car car1 = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        Car car2 = new Car(2L, "BMW", "M4", true, Date.valueOf("1901-06-15"));

        //When
        carDao.save(car1);
        carDao.save(car2);
        List<Car> cars = carDao.findAll();
        int numberOfCars = cars.size();


        //Then
        assertEquals(initialNumberOfCars + 2, numberOfCars);
        assertTrue(cars.contains(car1));
        assertTrue(cars.contains(car2));

        //CleanUp
        carDao.delete(car1);
        carDao.delete(car2);
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Car car1 = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        carDao.save(car1);

        //When
        Car foundCar = carDao.findById(car1.getCarId()).get();

        //Then
        assertEquals(foundCar, car1);

        //CleanUp
        carDao.delete(car1);
    }

    @Transactional
    @Test
    public void testDelete() {
        //Given
        int initialNumberOfCars = carDao.findAll().size();
        Car car1 = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        carDao.save(car1);

        //When
        carDao.delete(car1);
        List<Car> cars = carDao.findAll();
        int numberOfCars = cars.size();

        //Then
        assertEquals(initialNumberOfCars, numberOfCars);
        assertFalse(cars.contains(car1));
    }
}