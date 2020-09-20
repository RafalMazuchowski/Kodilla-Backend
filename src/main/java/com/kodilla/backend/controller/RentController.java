package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.RentDto;
import com.kodilla.backend.mapper.RentMapper;
import com.kodilla.backend.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/rent")
@CrossOrigin(origins = "*")
public class RentController {

    @Autowired
    RentService rentService;

    @Autowired
    RentMapper rentMapper;

    @GetMapping(value = "get/{id}")
    public RentDto getRent(@PathVariable long id) {
        return rentMapper.mapToRentDto(rentService.getRent(id));
    }

    @GetMapping(value = "all")
    public List<RentDto> getRents() {
        return rentMapper.mapToRentDtoList(rentService.getAllRents());
    }

    @PostMapping(value = "add", consumes = APPLICATION_JSON_VALUE)
    public RentDto createRent(@RequestBody RentDto rentDto) {
        return rentMapper.mapToRentDto(rentService.saveRent(
                rentMapper.mapToRent(rentDto)));
    }

    @PutMapping(value = "update/{id}", consumes = APPLICATION_JSON_VALUE)
    public RentDto updateRent(@RequestBody RentDto rentDto, @RequestParam long id) {
        return rentMapper.mapToRentDto(rentService.updateRent(rentDto, id));
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteRent(@PathVariable long id) {
        rentService.deleteRent(id);
    }
}
