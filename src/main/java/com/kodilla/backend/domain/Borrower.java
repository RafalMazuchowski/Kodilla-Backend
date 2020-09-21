package com.kodilla.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "BORROWERS")
public class Borrower {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "BORROWER_ID", unique = true)
    private Long borrowerId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "REGISTRATION")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    private Date registration;

    public Borrower(Long borrowerId, String firstName, String lastName, Date registration) {
        this.borrowerId = borrowerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = registration;//Date.valueOf(LocalDate.now());
    }
}
