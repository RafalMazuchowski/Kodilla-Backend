package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Car;
import com.kodilla.backend.domain.dto.CarDto;
import com.kodilla.backend.domain.repository.ModelDao;
import com.kodilla.backend.exceptions.ModelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    @Autowired
    private ModelDao modelDao;

    public Car mapToCar(CarDto carDto) {
        return new Car(carDto.getCarId(),
                modelDao.findById(carDto.getModelId())
                        .orElseThrow(ModelNotFoundException::new),
                carDto.getBorrowed(),
                carDto.getRentDate());
    }

    public CarDto mapToCarDto(Car car) {
        return new CarDto(
                car.getCarId(),
                car.getModelId().getModelId(),
                car.getBorrowed(),
                car.getRentDate());
    }

    public List<CarDto> mapToCarDtoList(List<Car> carList) {
        return carList.stream()
                .map(c -> new CarDto(
                        c.getCarId(),
                        c.getModelId().getModelId(),
                        c.getBorrowed(),
                        c.getRentDate()))
                .collect(Collectors.toList());
    }
}
