package com.kodilla.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {
    private Long rentId;
    private Long borrowerId;
    private Long carId;
    private Integer distance;
    private String placeOfRent;
    private String placeOfReturn;
    private Boolean carReturned;
    private Integer price;
}
