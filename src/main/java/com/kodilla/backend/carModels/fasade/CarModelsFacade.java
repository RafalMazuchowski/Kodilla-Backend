package com.kodilla.backend.carModels.fasade;

import com.kodilla.backend.carModels.ManufacturerDto;
import com.kodilla.backend.carModels.ModelDto;
import com.kodilla.backend.carModels.ModelMapper;
import com.kodilla.backend.carModels.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarModelsFacade {

    @Autowired
    private ModelService modelService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ManufacturerDto> fetchManufacturers() {
        return modelMapper.mapToManufacturerDtoList(modelService.fetchManufacturers());
    }

    public List<ModelDto> fetchModels(String manufacturer) {
        return modelMapper.mapToModelDtoList(modelService.fetchModels(manufacturer));
    }
}
