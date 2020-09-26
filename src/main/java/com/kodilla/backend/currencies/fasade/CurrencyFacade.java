package com.kodilla.backend.currencies.fasade;

import com.kodilla.backend.currencies.CurrencyRatesDto;
import com.kodilla.backend.currencies.NbpCurrencyRateMapper;
import com.kodilla.backend.currencies.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyFacade {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private NbpCurrencyRateMapper nbpCurrencyRateMapper;

    public CurrencyRatesDto fetchCurrencyRates() {
        return nbpCurrencyRateMapper.mapToCurrencyRates(currencyService.fetchNbpCurrency());
    }
}
