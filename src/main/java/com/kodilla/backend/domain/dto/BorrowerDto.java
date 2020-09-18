package com.kodilla.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerDto {
    private Long borrowerId;
    private String firstName;
    private String lastName;
    private Date registration;
}
