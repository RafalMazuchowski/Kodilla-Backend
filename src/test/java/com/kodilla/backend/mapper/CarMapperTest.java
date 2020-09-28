package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Car;
import com.kodilla.backend.domain.dto.CarDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarMapperTest {

    @Autowired
    CarMapper carMapper;

    @Test
    void mapToCar() {
        //Given
        CarDto carDto = new CarDto(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        //When
        Car mappingResult = carMapper.mapToCar(carDto);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.getCarId()));
        assertEquals("BMW", mappingResult.getManufacturer());
        assertEquals("M3", mappingResult.getModel());
        assertTrue(mappingResult.getBorrowed());
        assertEquals(Date.valueOf("2001-04-20"), mappingResult.getRentDate());
    }

    @Test
    void mapToCarDto() {
        //Given
        Car car = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        //When
        CarDto mappingResult = carMapper.mapToCarDto(car);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.getCarId()));
        assertEquals("BMW", mappingResult.getManufacturer());
        assertEquals("M3", mappingResult.getModel());
        assertTrue(mappingResult.getBorrowed());
        assertEquals(Date.valueOf("2001-04-20"), mappingResult.getRentDate());
    }

    @Test
    public void mapToCarDtoList() {
        //Given
        Car car = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        List<Car> carList = new ArrayList<>();
        carList.add(car);

        //When
        List<CarDto> mappingResult = carMapper.mapToCarDtoList(carList);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.get(0).getCarId()));
        assertEquals("BMW", mappingResult.get(0).getManufacturer());
        assertEquals("M3", mappingResult.get(0).getModel());
        assertTrue(mappingResult.get(0).getBorrowed());
        assertEquals(Date.valueOf("2001-04-20"), mappingResult.get(0).getRentDate());
    }
}