package com.kodilla.backend.mapper;

import com.kodilla.backend.carModels.CarsApiManufacturerDto;
import com.kodilla.backend.carModels.CarsApiModelDto;
import com.kodilla.backend.carModels.ManufacturerDto;
import com.kodilla.backend.domain.Model;
import com.kodilla.backend.domain.dto.ModelDto;
import com.kodilla.backend.domain.enums.Manufacturer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

    public Model mapToModel(ModelDto modelDto) {
        return new Model(
                modelDto.getModelId(),
                Manufacturer.valueOf(modelDto.getManufacturer()),
                modelDto.getName(),
                modelDto.getRate());
    }

    public ModelDto mapToModelDto(Model model) {
        return new ModelDto(
                model.getModelId(),
                model.getManufacturer().toString(),
                model.getName(),
                model.getRate());
    }

    public List<ModelDto> mapToModelDtoList(List<Model> modelList) {
        return modelList.stream()
                .map(m -> new ModelDto(
                        m.getModelId(),
                        m.getManufacturer().toString(),
                        m.getName(),
                        m.getRate()))
                .collect(Collectors.toList());
    }

    public List<ManufacturerDto> mapToManufacturerDto(List<CarsApiManufacturerDto> manufacturerList) {
        return manufacturerList.stream()
                .map(m -> new ManufacturerDto(m.getName()))
                .collect(Collectors.toList());
    }

    public List<ModelDto> mapToModelDtoListNew(List<CarsApiModelDto> modelList) {
        return modelList.stream()
                .map(m -> new ModelDto(
                        1L,
                        m.getMake(),
                        m.getModel(),
                        1.0))
                .collect(Collectors.toList());
    }
}
