package com.kodilla.backend.domain;

import com.kodilla.backend.domain.enums.Degree;
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
@Entity(name = "EMPLOYEES")
public class Employee {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "RENT_ID", unique = true)
    private Long employeeId;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "DEGREE")
    private Degree degree;
}
