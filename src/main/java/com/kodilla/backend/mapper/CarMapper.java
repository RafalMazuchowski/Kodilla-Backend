package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Car;
import com.kodilla.backend.domain.dto.CarDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {


    public Car mapToCar(CarDto carDto) {
        return new Car(carDto.getCarId(),
                carDto.getBorrowed(),
                carDto.getRentDate());
    }

    public CarDto mapToCarDto(Car car) {
        return new CarDto(
                car.getCarId(),
                car.getBorrowed(),
                car.getRentDate());
    }

    public List<CarDto> mapToCarDtoList(List<Car> carList) {
        return carList.stream()
                .map(c -> new CarDto(
                        c.getCarId(),
                        c.getBorrowed(),
                        c.getRentDate()))
                .collect(Collectors.toList());
    }
}
