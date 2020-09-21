package com.kodilla.backend.controller;

import com.kodilla.backend.domain.Borrower;
import com.kodilla.backend.domain.dto.BorrowerDto;
import com.kodilla.backend.mapper.BorrowerMapper;
import com.kodilla.backend.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/borrower")
@CrossOrigin(origins = "*")
public class BorrowerController {

    @Autowired
    BorrowerService borrowerService;

    @Autowired
    BorrowerMapper borrowerMapper;

    @GetMapping(value = "get/{id}")
    public BorrowerDto getBorrower(@PathVariable long id) {
        return borrowerMapper.mapToBorrowerDto(borrowerService.getBorrower(id));
    }

    @GetMapping(value = "all")
    public List<BorrowerDto> getBorrowers() {
        return borrowerMapper.mapToBorrowerDtoList(borrowerService.getAllBorrowers());
    }

    @PostMapping(value = "add", consumes = APPLICATION_JSON_VALUE)
    public Borrower createBorrower(@RequestBody BorrowerDto borrowerDto) {
        return borrowerService.saveBorrower(borrowerMapper.mapToBorrower(borrowerDto));
    }

    @PutMapping(value = "update", consumes = APPLICATION_JSON_VALUE)
    public Borrower updateBorrower(@RequestBody BorrowerDto borrowerDto) {
        return borrowerService.saveBorrower(borrowerMapper.mapToBorrower(borrowerDto));
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteBorrower(@PathVariable long id) {
        borrowerService.deleteBorrower(id);
    }
}
