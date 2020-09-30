package com.kodilla.backend.controller;

import com.google.gson.Gson;
import com.kodilla.backend.domain.Borrower;
import com.kodilla.backend.domain.Car;
import com.kodilla.backend.domain.Rent;
import com.kodilla.backend.domain.dto.RentDto;
import com.kodilla.backend.mapper.RentMapper;
import com.kodilla.backend.service.RentService;
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

import static com.kodilla.backend.domain.enums.Degree.WORKER;
import static com.kodilla.backend.domain.enums.Rental.KATOWICE;
import static com.kodilla.backend.domain.enums.Rental.KRAKOW;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentController.class)
public class RentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentService rentService;

    @MockBean
    private RentMapper rentMapper;

    @Test
    public void getRent() throws Exception {
        //Given
        Borrower borrower = new Borrower(26L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car = new Car(55L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        Rent rent = new Rent(63L, borrower, car, 55, KATOWICE, KRAKOW, true, 700);

        RentDto rentDto = new RentDto(63L, 26L, 55L, 55, "KATOWICE", "KRAKOW", true, 700);

        when(rentMapper.mapToRentDto(rent)).thenReturn(rentDto);
        when(rentMapper.mapToRent(rentDto)).thenReturn(rent);
        when(rentService.getRent(63L)).thenReturn(rent);

        //When&Then
        mockMvc.perform(get("/v1/rent/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].rentId", is(63)))
                .andExpect(jsonPath("$[0].borrower", is(borrower)))
                .andExpect(jsonPath("$[0].car", is(car)))
                .andExpect(jsonPath("$[0].distance", is(55)))
                .andExpect(jsonPath("$[0].placeOfRent", is(KATOWICE)))
                .andExpect(jsonPath("$[0].placeOfReturn", is(KRAKOW)))
                .andExpect(jsonPath("$[0].carReturned", is(true)))
                .andExpect(jsonPath("$[0].price", is(700)));
    }

    @Test
    public void getRents() throws Exception {
        //Given
        RentDto rentDto = new RentDto(63L, 26L, 55L, 55, "KATOWICE", "KRAKOW", true, 700);
        List<RentDto> rentDtoList = new ArrayList<>();
        rentDtoList.add(rentDto);

        Borrower borrower = new Borrower(26L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car = new Car(55L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        Rent rent = new Rent(63L, borrower, car, 55, KATOWICE, KRAKOW, true, 700);
        List<Rent> rentList = new ArrayList<>();
        rentList.add(rent);

        when(rentMapper.mapToRentDtoList(rentList)).thenReturn(rentDtoList);
        when(rentService.getAllRents()).thenReturn(rentList);

        //When&Then
        mockMvc.perform(get("/v1/rent/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].rentId", is(63)))
                .andExpect(jsonPath("$[0].borrower", is(borrower)))
                .andExpect(jsonPath("$[0].car", is(car)))
                .andExpect(jsonPath("$[0].distance", is(55)))
                .andExpect(jsonPath("$[0].placeOfRent", is(KATOWICE)))
                .andExpect(jsonPath("$[0].placeOfReturn", is(KRAKOW)))
                .andExpect(jsonPath("$[0].carReturned", is(true)))
                .andExpect(jsonPath("$[0].price", is(700)));
    }

    @Test
    public void createRent() throws Exception {
        //Given
        Borrower borrower = new Borrower(26L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car = new Car(55L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        Rent rent = new Rent(63L, borrower, car, 55, KATOWICE, KRAKOW, true, 700);

        RentDto rentDto = new RentDto(63L, 26L, 55L, 55, "KATOWICE", "KRAKOW", true, 700);

        when(rentMapper.mapToRent(rentDto)).thenReturn(rent);
        when(rentService.saveRent(rent)).thenReturn(rent);
        when(rentMapper.mapToRentDto(rent)).thenReturn(rentDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentDto);

        //When&Then
        mockMvc.perform(get("/v1/rent/add")
                .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateRent() throws Exception {
        //Given
        Borrower borrower = new Borrower(26L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car = new Car(55L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        Rent rent = new Rent(63L, borrower, car, 55, KATOWICE, KRAKOW, true, 700);

        RentDto rentDto = new RentDto(93L, 27L, 34L, 55, "KRAKOW", "KRAKOW", true, 400);

        when(rentMapper.mapToRent(ArgumentMatchers.any(RentDto.class))).thenReturn(rent);
        when(rentService.saveRent(ArgumentMatchers.any(Rent.class))).thenReturn(rent);
        when(rentMapper.mapToRentDto(ArgumentMatchers.any(Rent.class))).thenReturn(rentDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rent);

        //When&Then
        mockMvc.perform(get("/v1/rent/update/93")
                .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].rentId", is(93)))
                .andExpect(jsonPath("$[0].borrower", is(27)))
                .andExpect(jsonPath("$[0].car", is(34)))
                .andExpect(jsonPath("$[0].distance", is(55)))
                .andExpect(jsonPath("$[0].placeOfRent", is("KRAKOW")))
                .andExpect(jsonPath("$[0].placeOfReturn", is("KRAKOW")))
                .andExpect(jsonPath("$[0].carReturned", is(true)))
                .andExpect(jsonPath("$[0].price", is(400)));
    }

    @Test
    public void deleteRent() throws Exception {
        //Given
        Borrower borrower = new Borrower(26L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Car car = new Car(55L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        Rent rent = new Rent(63L, borrower, car, 55, KATOWICE, KRAKOW, true, 700);
        //When & Then
        mockMvc.perform(delete("/v1/rent/delete/63").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
