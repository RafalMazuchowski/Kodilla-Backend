package com.kodilla.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "CARS")
public class Car {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CAR_ID", unique = true)
    private Long carId;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "IN_USE")
    private Boolean borrowed;

    @Column(name = "RENT_DATE")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    private Date rentDate;
}
