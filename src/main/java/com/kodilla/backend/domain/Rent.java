package com.kodilla.backend.domain;

import com.kodilla.backend.domain.enums.Rental;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RENTS")
@EqualsAndHashCode
public class Rent {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "RENT_ID", unique = true)
    private Long rentId;

    @ManyToOne
    @JoinColumn(name = "BORROWER_ID")
    private Borrower borrower;

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @Column(name = "DISTANCE")
    private Integer distance;

    @Column(name = "RENTED_IN")
    private Rental placeOfRent;

    @Column(name = "RETURNED_IN")
    private Rental placeOfReturn;

    @Column(name = "IS_RETURNED")
    private Boolean carReturned;

    @Column(name = "PRICE")
    private Integer price;
}
