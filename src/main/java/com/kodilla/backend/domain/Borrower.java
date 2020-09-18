package com.kodilla.backend.domain;

import lombok.*;

import java.sql.Date;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "BORROWER")
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BORROWER_ID", unique = true)
    private Long borrowerId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "REGISTRATION")
    private Date registration;
}
