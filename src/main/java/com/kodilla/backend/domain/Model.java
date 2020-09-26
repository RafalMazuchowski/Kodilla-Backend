package com.kodilla.backend.domain;

import com.kodilla.backend.domain.enums.Manufacturer;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "MODELS")
public class Model {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "MODEL_ID", unique = true)
    private Long modelId;

    @Column(name = "MANUFACTURER")
    private Manufacturer manufacturer;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RATE")
    private Double rate;
}
