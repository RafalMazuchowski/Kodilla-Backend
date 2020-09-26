package com.kodilla.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Long carId;
    private String manufacturer;
    private String model;
    private Boolean borrowed;
    private Date rentDate;
}
