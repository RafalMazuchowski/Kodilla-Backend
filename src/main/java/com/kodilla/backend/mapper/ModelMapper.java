package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Model;
import com.kodilla.backend.domain.dto.ModelDto;
import com.kodilla.backend.domain.enums.Manufacturer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

    public Model mapToModel(ModelDto modelDto) {
        return new Model(modelDto.getModelId(),
                Manufacturer.valueOf(modelDto.getManufacturer()),
                modelDto.getCarModel(),
                modelDto.getRate());
    }

    public ModelDto mapToModelDto(Model model) {
        return new ModelDto(model.getModelId(),
                model.getManufacturer().toString(),
                model.getCarModel(),
                model.getRate());
    }

    public List<ModelDto> mapToModelDtoList(List<Model> modelList) {
        return modelList.stream()
                .map(m -> new ModelDto(m.getModelId(),
                        m.getManufacturer().toString(),
                        m.getCarModel(),
                        m.getRate()))
                .collect(Collectors.toList());
    }
}
