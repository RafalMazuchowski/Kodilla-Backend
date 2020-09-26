package com.kodilla.backend.currencies;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NbpCurrencyRateMapper {

    public CurrencyRatesDto mapToCurrencyRates (List<NbpCurrencyDto> nbpCurrencyDtos){
        CurrencyRatesDto currencyRatesDto = new CurrencyRatesDto();
        if (nbpCurrencyDtos != null && nbpCurrencyDtos.size() > 0) {
            for (NbpRateDto nbpRate : nbpCurrencyDtos.get(0).getRates()) {
                switch (nbpRate.getCode()) {
                    case "USD":
                        currencyRatesDto.setUsd(nbpRate.getMid());
                        break;
                    case "EUR":
                        currencyRatesDto.setEur(nbpRate.getMid());
                        break;
                    case "CHF":
                        currencyRatesDto.setChf(nbpRate.getMid());
                        break;
                }
            }
        }
        return currencyRatesDto;
    }
}
