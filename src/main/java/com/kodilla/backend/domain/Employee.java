package com.kodilla.backend.domain;

import com.kodilla.backend.domain.enums.Degree;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "EMPLOYEES")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENT_ID", unique = true)
    private Long employeeId;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "DEGREE")
    private Degree degree;
}
