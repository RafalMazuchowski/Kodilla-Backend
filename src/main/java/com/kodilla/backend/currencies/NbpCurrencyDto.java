package com.kodilla.backend.currencies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class NbpCurrencyDto {
    @JsonProperty("rates")
    private NbpRateDto[] rates;
}
