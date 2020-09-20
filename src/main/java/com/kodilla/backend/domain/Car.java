package com.kodilla.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "CARS")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_ID", unique = true)
    private Long carId;

    @ManyToOne
    @JoinColumn(name = "MODEL_ID")
    private Model modelId;

    @Column(name = "IN_USE")
    private Boolean borrowed;

    @Column(name = "RENT_DATE")
    private Date rentDate;
}
