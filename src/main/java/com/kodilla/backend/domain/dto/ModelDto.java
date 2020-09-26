package com.kodilla.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModelDto {
    private Long modelId;
    private String manufacturer;
    private String name;
    private Double rate;
}
