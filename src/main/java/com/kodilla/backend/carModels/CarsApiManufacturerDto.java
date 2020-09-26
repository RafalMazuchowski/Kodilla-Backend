package com.kodilla.backend.carModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CarsApiManufacturerDto {
    @JsonProperty("name")
    private String name;
}
