package com.kodilla.backend.currencies.client;


import com.kodilla.backend.currencies.NbpCurrencyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class NbpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbpClient.class);

    private RestTemplate restTemplate = new RestTemplate();

    public List<NbpCurrencyDto> getNbpCurrency() {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://api.nbp.pl/api/exchangerates/tables/A")
                .queryParam("format", "json")
                .build().encode().toUri();

        try {
            NbpCurrencyDto[] currencyResponse = restTemplate.getForObject(uri, NbpCurrencyDto[].class);
            return Arrays.asList(Optional.ofNullable(currencyResponse).orElse(new NbpCurrencyDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}
