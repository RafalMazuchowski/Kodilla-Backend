package com.kodilla.backend.domain;

import com.kodilla.backend.domain.enums.Manufacturer;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "MODELS")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODEL_ID", unique = true)
    private Long modelId;

    @Column(name = "MANUFACTURER")
    private Manufacturer manufacturer;

    @Column(name = "MODEL")
    private String carModel;

    @Column(name = "RATE")
    private Double rate;
}
