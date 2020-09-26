package com.kodilla.backend.currencies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class NbpRateDto {
    @JsonProperty("mid")
    private Double mid;
    @JsonProperty("code")
    private String code;
}
