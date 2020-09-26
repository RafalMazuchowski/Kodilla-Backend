package com.kodilla.backend.domain;

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
@Entity(name = "LOGS")
public class Log {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "LOGS_ID", unique = true)
    private Long carId;

    @Column(name = "URL")
    private String url;

    @Column(name = "DATE")
    private Long date;

    @Column(name = "DURATION")
    private Long duration;

    public Log(String url, Long date, Long duration) {
        this.url = url;
        this.date = date;
        this.duration = duration;
    }
}
