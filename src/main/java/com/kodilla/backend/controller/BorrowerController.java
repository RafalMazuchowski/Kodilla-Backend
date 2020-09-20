package com.kodilla.backend.controller;

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
    public BorrowerDto createBorrower(@RequestBody BorrowerDto borrowerDto) {
        return borrowerMapper.mapToBorrowerDto(borrowerService.saveBorrower(
                borrowerMapper.mapToBorrower(borrowerDto)));
    }

    @PutMapping(value = "update/{id}", consumes = APPLICATION_JSON_VALUE)
    public BorrowerDto updateBorrower(@RequestBody BorrowerDto borrowerDto, @RequestParam long id) {
        return borrowerMapper.mapToBorrowerDto(borrowerService.updateBorrower(borrowerDto, id));
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteBorrower(@PathVariable long id) {
        borrowerService.deleteBorrower(id);
    }
}
