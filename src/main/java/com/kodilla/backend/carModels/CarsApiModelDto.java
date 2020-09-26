package com.kodilla.backend.carModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CarsApiModelDto {
    @JsonProperty("make")
    private String make;
    @JsonProperty("model")
    private String model;
}
