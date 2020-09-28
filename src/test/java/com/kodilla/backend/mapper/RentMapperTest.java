package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Borrower;
import com.kodilla.backend.domain.Car;
import com.kodilla.backend.domain.Rent;
import com.kodilla.backend.domain.dto.RentDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kodilla.backend.domain.enums.Rental.KATOWICE;
import static com.kodilla.backend.domain.enums.Rental.KRAKOW;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentMapperTest {

    @Autowired
    RentMapper rentMapper;

    @Test
    void mapToRent() { // MOCKITO
        //Given
        RentDto rentDto = new RentDto(12L, 3L, 5L, 55, "KATOWICE", "KRAKOW", true, 400);

        Borrower borrower = new Borrower(3L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car = new Car(5L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        //When
        Rent mappingResult = rentMapper.mapToRent(rentDto);

        //Then
        assertEquals(Optional.of(12L), Optional.ofNullable(mappingResult.getRentId()));
        assertSame(borrower.getClass(), mappingResult.getBorrower().getClass());
        assertSame(car.getClass(), mappingResult.getCar().getClass());
        assertEquals(Optional.of(55), Optional.ofNullable(mappingResult.getDistance()));
        assertEquals(KATOWICE, mappingResult.getPlaceOfRent());
        assertEquals(KRAKOW, mappingResult.getPlaceOfReturn());
        assertTrue(mappingResult.getCarReturned());
        assertEquals(Optional.of(400), Optional.ofNullable(mappingResult.getPrice()));
    }

    @Test
    void mapToRentDto() {
        //Given
        Borrower borrower = new Borrower(3L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car = new Car(5L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        Rent rent = new Rent(12L, borrower, car, 55, KATOWICE, KRAKOW, true, 400);

        //When
        RentDto mappingResult = rentMapper.mapToRentDto(rent);

        //Then
        assertEquals(Optional.of(12L), Optional.ofNullable(mappingResult.getRentId()));
        assertEquals(Optional.of(3L), Optional.ofNullable(mappingResult.getBorrowerId()));
        assertEquals(Optional.of(5L), Optional.ofNullable(mappingResult.getCarId()));
        assertEquals(Optional.of(55), Optional.ofNullable(mappingResult.getDistance()));
        assertEquals("KATOWICE", mappingResult.getPlaceOfRent());
        assertEquals("KRAKOW", mappingResult.getPlaceOfReturn());
        assertTrue(mappingResult.getCarReturned());
        assertEquals(Optional.of(400), Optional.ofNullable(mappingResult.getPrice()));
    }

    @Test
    public void mapToRentDtoList() {
        //Given
        Borrower borrower = new Borrower(3L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car = new Car(5L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        Rent rent = new Rent(12L, borrower, car, 55, KATOWICE, KRAKOW, true, 400);
        List<Rent> rentList = new ArrayList<>();
        rentList.add(rent);

        //When
        List<RentDto> mappingResult = rentMapper.mapToRentDtoList(rentList);

        //Then
        assertEquals(Optional.of(12L), Optional.ofNullable(mappingResult.get(0).getRentId()));
        assertEquals(Optional.of(3L), Optional.ofNullable(mappingResult.get(0).getBorrowerId()));
        assertEquals(Optional.of(5L), Optional.ofNullable(mappingResult.get(0).getCarId()));
        assertEquals(Optional.of(55), Optional.ofNullable(mappingResult.get(0).getDistance()));
        assertEquals("KATOWICE", mappingResult.get(0).getPlaceOfRent());
        assertEquals("KRAKOW", mappingResult.get(0).getPlaceOfReturn());
        assertTrue(mappingResult.get(0).getCarReturned());
        assertEquals(Optional.of(400), Optional.ofNullable(mappingResult.get(0).getPrice()));
    }
}