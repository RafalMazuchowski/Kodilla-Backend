package com.kodilla.backend.controller;

import com.google.gson.Gson;
import com.kodilla.backend.domain.Car;
import com.kodilla.backend.domain.dto.CarDto;
import com.kodilla.backend.mapper.CarMapper;
import com.kodilla.backend.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private CarMapper carMapper;

    @Test
    public void getCar() throws Exception {
        //Given
        CarDto carDto = new CarDto(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        Car car = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        when(carMapper.mapToCarDto(car)).thenReturn(carDto);
        when(carMapper.mapToCar(carDto)).thenReturn(car);
        when(carService.getCar(1L)).thenReturn(car);

        //When&Then
        mockMvc.perform(get("/v1/car/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].carId", is(1)))
                .andExpect(jsonPath("$[0].manufacturer", is("BMW")))
                .andExpect(jsonPath("$[0].model", is("M3")))
                .andExpect(jsonPath("$[0].borrowed", is(true)))
                .andExpect(jsonPath("$[0].registration", is(Date.valueOf("2001-04-20"))));
    }

    @Test
    public void getCars() throws Exception {
        //Given
        CarDto carDto = new CarDto(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        List<CarDto> carDtoList = new ArrayList<>();
        carDtoList.add(carDto);

        Car car = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        List<Car> carList = new ArrayList<>();
        carList.add(car);

        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carService.getAllCars()).thenReturn(carList);

        //When&Then
        mockMvc.perform(get("/v1/car/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].carId", is(1)))
                .andExpect(jsonPath("$[0].manufacturer", is("BMW")))
                .andExpect(jsonPath("$[0].model", is("M3")))
                .andExpect(jsonPath("$[0].borrowed", is(true)))
                .andExpect(jsonPath("$[0].registration", is(Date.valueOf("2001-04-20"))));
    }

    @Test
    public void createCar() throws Exception {
        //Given
        Car car = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        CarDto carDto = new CarDto(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        when(carMapper.mapToCar(carDto)).thenReturn(car);
        when(carService.saveCar(car)).thenReturn(car);
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When&Then
        mockMvc.perform(get("/v1/car/add")
                .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCar() throws Exception {
        //Given
        Car car = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        CarDto carDto = new CarDto(2L, "BMW", "M4", true, Date.valueOf("1901-06-15"));

        when(carMapper.mapToCar(ArgumentMatchers.any(CarDto.class))).thenReturn(car);
        when(carService.saveCar(ArgumentMatchers.any(Car.class))).thenReturn(car);
        when(carMapper.mapToCarDto(ArgumentMatchers.any(Car.class))).thenReturn(carDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(car);

        //When&Then
        mockMvc.perform(get("/v1/car/update/1")
                .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].carId", is(2)))
                .andExpect(jsonPath("$[0].manufacturer", is("BMW")))
                .andExpect(jsonPath("$[0].model", is("M4")))
                .andExpect(jsonPath("$[0].borrowed", is(true)))
                .andExpect(jsonPath("$[0].registration", is(Date.valueOf("1901-06-15"))));
    }

    @Test
    public void deleteCar() throws Exception {
        //Given
        Car car = new Car(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        //When & Then
        mockMvc.perform(delete("/v1/car/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
