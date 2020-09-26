package com.kodilla.backend.currencies;

import com.kodilla.backend.currencies.client.NbpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private NbpClient nbpClient;

    public List<NbpCurrencyDto> fetchNbpCurrency() {
        return nbpClient.getNbpCurrency();
    }
}