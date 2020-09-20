package com.kodilla.backend.domain;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "BORROWERS")
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

    public Borrower(Long borrowerId, String firstName, String lastName, Date registration) {
        this.borrowerId = borrowerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = Date.valueOf(LocalDate.now());
    }
}
