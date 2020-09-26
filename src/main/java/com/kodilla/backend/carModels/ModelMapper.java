package com.kodilla.backend.carModels;

import com.kodilla.backend.carModels.CarsApiManufacturerDto;
import com.kodilla.backend.carModels.CarsApiModelDto;
import com.kodilla.backend.carModels.ManufacturerDto;
import com.kodilla.backend.carModels.ModelDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

    public List<ManufacturerDto> mapToManufacturerDtoList(List<CarsApiManufacturerDto> manufacturerList) {
        return manufacturerList.stream()
                .map(m -> new ManufacturerDto(m.getName()))
                .collect(Collectors.toList());
    }

    public List<ModelDto> mapToModelDtoList(List<CarsApiModelDto> modelList) {
        return modelList.stream()
                .map(m -> new ModelDto(
                        m.getMake(),
                        m.getModel()))
                .collect(Collectors.toList());
    }
}
