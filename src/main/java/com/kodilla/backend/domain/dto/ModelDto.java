package com.kodilla.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModelDto {
    private Long modelId;
    private String manufacturer;
    private String carModel;
    private Double rate;
}
