package com.kodilla.backend.domain;

import com.kodilla.backend.domain.repository.BorrowerDao;
import com.kodilla.backend.domain.repository.CarDao;
import com.kodilla.backend.domain.repository.RentDao;
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
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RentTest {

    @Autowired
    private RentDao rentDao;

    @Transactional
    @Test
    public void testSaveAndFindAll() {
        //Given
        int initialNumberOfRents = rentDao.findAll().size();
        Borrower borrower1 = new Borrower(26L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Borrower borrower2 = new Borrower(27L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car1 = new Car(55L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        Car car2 = new Car(34L, "BMW", "M4", true, Date.valueOf("1901-06-15"));

        Rent rent1 = new Rent(63L, borrower1, car1, 55, KATOWICE, KRAKOW, true, 700);
        Rent rent2 = new Rent(93L, borrower2, car2, 55, KRAKOW, KRAKOW, true, 400);

        //When
        rentDao.save(rent1);
        rentDao.save(rent2);
        List<Rent> rents = rentDao.findAll();
        int numberOfRents = rents.size();

        //Then
        assertEquals(initialNumberOfRents + 2, numberOfRents);
        assertEquals(rent1.getPrice(), java.util.Optional.of(700));
        assertEquals(rent2.getPrice(), java.util.Optional.of(400));

        //CleanUp
        rent1.setRentId(rents.get(initialNumberOfRents).getRentId());
        rent2.setRentId(rents.get(initialNumberOfRents).getRentId());
        rentDao.delete(rent1);
        rentDao.delete(rent2);
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Borrower borrower1 = new Borrower(16L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car1 = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        Rent rent1 = new Rent(1L, borrower1, car1, 55, KATOWICE, KRAKOW, true, 400);
        rentDao.save(rent1);
        rent1.setRentId(rentDao.findAll().get(rentDao.findAll().size()-1).getRentId());

        //When
        Rent foundRent = rentDao.findById(rent1.getRentId()).get();

        //Then
        assertEquals(foundRent, rent1);

        //CleanUp
        rentDao.delete(rent1);
    }

    @Transactional
    @Test
    public void testDelete() {
        //Given
        int initialNumberOfRents = rentDao.findAll().size();
        Borrower borrower1 = new Borrower(16L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car1 = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        Rent rent1 = new Rent(1L, borrower1, car1, 55, KATOWICE, KRAKOW, true, 400);
        rentDao.save(rent1);
        rent1.setRentId(rentDao.findAll().get(rentDao.findAll().size()-1).getRentId());

        //When
        rentDao.delete(rent1);
        List<Rent> rents = rentDao.findAll();
        int numberOfRents = rents.size();

        //Then
        assertEquals(initialNumberOfRents, numberOfRents);
        assertFalse(rents.contains(rent1));
    }
}