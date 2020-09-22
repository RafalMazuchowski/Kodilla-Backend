package com.kodilla.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {
    private String to;
    private String subject;
    private String message;
}
