package com.kodilla.backend.controller;

import com.kodilla.backend.currencies.CurrencyRatesDto;
import com.kodilla.backend.currencies.fasade.CurrencyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/currency")
@CrossOrigin(origins = "*")
public class CurrencyController {

    @Autowired
    private CurrencyFacade currencyFacade;

    @GetMapping(value = "rates")
    public CurrencyRatesDto getCurrencyRates() {
        return currencyFacade.fetchCurrencyRates();
    }
}
