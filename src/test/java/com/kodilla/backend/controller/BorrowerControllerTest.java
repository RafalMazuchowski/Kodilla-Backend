package com.kodilla.backend.controller;


import com.google.gson.Gson;
import com.kodilla.backend.domain.Borrower;
import com.kodilla.backend.domain.dto.BorrowerDto;
import com.kodilla.backend.mapper.BorrowerMapper;
import com.kodilla.backend.service.BorrowerService;
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

@WebMvcTest(BorrowerController.class)
public class BorrowerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowerService borrowerService;

    @MockBean
    private BorrowerMapper borrowerMapper;

    @Test
    public void getBorrower() throws Exception {
        //Given
        BorrowerDto borrowerDto = new BorrowerDto(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Borrower borrower = new Borrower(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));

        when(borrowerMapper.mapToBorrowerDto(borrower)).thenReturn(borrowerDto);
        when(borrowerMapper.mapToBorrower(borrowerDto)).thenReturn(borrower);
        when(borrowerService.getBorrower(1L)).thenReturn(borrower);

        //When&Then
        mockMvc.perform(get("/v1/borrower/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].borrowerId", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Adam")))
                .andExpect(jsonPath("$[0].lastName", is("Ford")))
                .andExpect(jsonPath("$[0].registration", is(Date.valueOf("2001-04-20"))));
    }

    @Test
    public void getBorrowers() throws Exception {
        //Given
        BorrowerDto borrowerDto = new BorrowerDto(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        List<BorrowerDto> borrowerDtoList = new ArrayList<>();
        borrowerDtoList.add(borrowerDto);

        Borrower borrower = new Borrower(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        List<Borrower> borrowerList = new ArrayList<>();
        borrowerList.add(borrower);

        when(borrowerMapper.mapToBorrowerDtoList(borrowerList)).thenReturn(borrowerDtoList);
        when(borrowerService.getAllBorrowers()).thenReturn(borrowerList);

        //When&Then
        mockMvc.perform(get("/v1/borrower/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].borrowerId", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Adam")))
                .andExpect(jsonPath("$[0].lastName", is("Ford")))
                .andExpect(jsonPath("$[0].registration", is(Date.valueOf("2001-04-20"))));
    }

    @Test
    public void createBorrower() throws Exception {
        //Given
        Borrower borrower = new Borrower(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        BorrowerDto borrowerDto = new BorrowerDto(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));

        when(borrowerMapper.mapToBorrower(borrowerDto)).thenReturn(borrower);
        when(borrowerService.saveBorrower(borrower)).thenReturn(borrower);
        when(borrowerMapper.mapToBorrowerDto(borrower)).thenReturn(borrowerDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(borrowerDto);

        //When&Then
        mockMvc.perform(get("/v1/borrower/add")
                .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBorrower() throws Exception {
        //Given
        Borrower borrower = new Borrower(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        BorrowerDto borrowerDto = new BorrowerDto(2L, "Piotr", "Richardson", Date.valueOf("2001-08-11"));

        when(borrowerMapper.mapToBorrower(ArgumentMatchers.any(BorrowerDto.class))).thenReturn(borrower);
        when(borrowerService.saveBorrower(ArgumentMatchers.any(Borrower.class))).thenReturn(borrower);
        when(borrowerMapper.mapToBorrowerDto(ArgumentMatchers.any(Borrower.class))).thenReturn(borrowerDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(borrower);

        //When&Then
        mockMvc.perform(get("/v1/borrower/update/1")
                .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].borrowerId", is(2)))
                .andExpect(jsonPath("$[0].firstName", is("Piotr")))
                .andExpect(jsonPath("$[0].lastName", is("Richardson")))
                .andExpect(jsonPath("$[0].registration", is(Date.valueOf("2001-08-11"))));
    }

    @Test
    public void deleteBorrower() throws Exception {
        //Given
        Borrower borrower = new Borrower(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        //When & Then
        mockMvc.perform(delete("/v1/borrower/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
