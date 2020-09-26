package com.kodilla.backend.controller;

import com.kodilla.backend.carModels.ManufacturerDto;
import com.kodilla.backend.carModels.ModelDto;
import com.kodilla.backend.carModels.ModelMapper;
import com.kodilla.backend.carModels.ModelService;
import com.kodilla.backend.carModels.fasade.CarModelsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/model")
@CrossOrigin(origins = "*")
public class ModelApiController {

    @Autowired
    ModelService modelService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private CarModelsFacade carModelsFacade;

    @GetMapping(value = "manufacturers")
    public List<ManufacturerDto> getApiModels() {
        return carModelsFacade.fetchManufacturers();
    }

    @GetMapping(value = "manufacturer/{name}/models")
    public List<ModelDto> getApiModels(@PathVariable String name) {
        return carModelsFacade.fetchModels(name);
    }
}
