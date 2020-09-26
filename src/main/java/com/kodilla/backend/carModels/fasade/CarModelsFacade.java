package com.kodilla.backend.carModels.fasade;

import com.kodilla.backend.carModels.ManufacturerDto;
import com.kodilla.backend.domain.dto.ModelDto;
import com.kodilla.backend.mapper.ModelMapper;
import com.kodilla.backend.service.ModelService;
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
        return modelMapper.mapToManufacturerDto(modelService.fetchManufacturers());
    }

    public List<ModelDto> fetchModels(String manufacturer) {
        return modelMapper.mapToModelDtoListNew(modelService.fetchModels(manufacturer));
    }
}
