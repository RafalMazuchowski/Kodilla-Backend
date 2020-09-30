package com.kodilla.backend.service;

import com.kodilla.backend.domain.Borrower;
import com.kodilla.backend.domain.Car;
import com.kodilla.backend.domain.Rent;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static com.kodilla.backend.domain.enums.Rental.KATOWICE;
import static com.kodilla.backend.domain.enums.Rental.KRAKOW;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RentServiceTest {

    @Autowired
    private RentService rentService;

    @Transactional
    @Test
    public void testSaveAndFindAll() {
        //Given
        int initialNumberOfRents = rentService.getAllRents().size();
        Borrower borrower1 = new Borrower(26L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Borrower borrower2 = new Borrower(27L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car1 = new Car(55L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        Car car2 = new Car(34L, "BMW", "M4", true, Date.valueOf("1901-06-15"));

        Rent rent1 = new Rent(63L, borrower1, car1, 55, KATOWICE, KRAKOW, true, 700);
        Rent rent2 = new Rent(93L, borrower2, car2, 55, KRAKOW, KRAKOW, true, 400);

        //When
        rentService.saveRent(rent1);
        rentService.saveRent(rent2);
        List<Rent> rents = rentService.getAllRents();
        int numberOfRents = rents.size();

        //Then
        assertEquals(initialNumberOfRents + 2, numberOfRents);
        assertEquals(rent1.getPrice(), java.util.Optional.of(700));
        assertEquals(rent2.getPrice(), java.util.Optional.of(400));

        //CleanUp
        rent1.setRentId(rents.get(initialNumberOfRents).getRentId());
        rent2.setRentId(rents.get(initialNumberOfRents + 1).getRentId());
        rentService.deleteRent(rent1.getRentId());
        rentService.deleteRent(rent2.getRentId());
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Borrower borrower1 = new Borrower(16L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car1 = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        Rent rent1 = new Rent(1L, borrower1, car1, 55, KATOWICE, KRAKOW, true, 400);
        rentService.saveRent(rent1);
        rent1.setRentId(rentService.getAllRents().get(rentService.getAllRents().size() - 1).getRentId());

        //When
        Rent foundRent = rentService.getRent(rent1.getRentId());

        //Then
        assertEquals(foundRent, rent1);

        //CleanUp
        rentService.deleteRent(rent1.getRentId());
    }

    @Transactional
    @Test
    public void testDelete() {
        //Given
        int initialNumberOfRents = rentService.getAllRents().size();
        Borrower borrower1 = new Borrower(16L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car1 = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        Rent rent1 = new Rent(1L, borrower1, car1, 55, KATOWICE, KRAKOW, true, 400);
        rentService.saveRent(rent1);
        rent1.setRentId(rentService.getAllRents().get(rentService.getAllRents().size() - 1).getRentId());

        //When
        rentService.deleteRent(rent1.getRentId());
        List<Rent> rents = rentService.getAllRents();
        int numberOfRents = rents.size();

        //Then
        assertEquals(initialNumberOfRents, numberOfRents);
        assertFalse(rents.contains(rent1));
    }
}