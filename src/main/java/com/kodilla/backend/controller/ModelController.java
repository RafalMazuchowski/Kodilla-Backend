package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.ModelDto;
import com.kodilla.backend.mapper.ModelMapper;
import com.kodilla.backend.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/model")
@CrossOrigin(origins = "*")
public class ModelController {

    @Autowired
    ModelService modelService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(value = "get/{id}")
    public ModelDto getModel(@PathVariable long id) {
        return modelMapper.mapToModelDto(modelService.getModel(id));
    }

    @GetMapping(value = "all")
    public List<ModelDto> getModels() {
        return modelMapper.mapToModelDtoList(modelService.getAllModels());
    }

    @PostMapping(value = "add", consumes = APPLICATION_JSON_VALUE)
    public ModelDto createModel(@RequestBody ModelDto modelDto) {
        return modelMapper.mapToModelDto(modelService.saveModel(
                modelMapper.mapToModel(modelDto)));
    }

    @PutMapping(value = "update/{id}", consumes = APPLICATION_JSON_VALUE)
    public ModelDto updateModel(@RequestBody ModelDto modelDto, @RequestParam long id) {
        return modelMapper.mapToModelDto(modelService.updateModel(modelDto, id));
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteModel(@PathVariable long id) {
        modelService.deleteModel(id);
    }
}
