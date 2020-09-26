package com.kodilla.backend.currencies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRatesDto {
    private Double usd;
    private Double eur;
    private Double chf;
}
