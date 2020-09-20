package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.CarDto;
import com.kodilla.backend.mapper.CarMapper;
import com.kodilla.backend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/car")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CarMapper carMapper;

    @GetMapping(value = "get/{id}")
    public CarDto getCar(@PathVariable long id) {
        return carMapper.mapToCarDto(carService.getCar(id));
    }

    @GetMapping(value = "all")
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carService.getAllCars());
    }

    @PostMapping(value = "add", consumes = APPLICATION_JSON_VALUE)
    public CarDto createCar(@RequestBody CarDto carDto) {
        return carMapper.mapToCarDto(carService.saveCar(
                carMapper.mapToCar(carDto)));
    }

    @PutMapping(value = "update/{id}", consumes = APPLICATION_JSON_VALUE)
    public CarDto updateCar(@RequestBody CarDto carDto, @RequestParam long id) {
        return carMapper.mapToCarDto(carService.updateCar(carDto, id));
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }
}
