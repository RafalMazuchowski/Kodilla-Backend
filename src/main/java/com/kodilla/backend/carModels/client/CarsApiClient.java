package com.kodilla.backend.carModels.client;

import com.kodilla.backend.carModels.CarsApiManufacturerDto;
import com.kodilla.backend.carModels.CarsApiModelDto;
import com.kodilla.backend.currencies.client.NbpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CarsApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbpClient.class);

    @Autowired
    private RestTemplate restTemplate;

    public List<CarsApiManufacturerDto> getManufacturers() {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://private-anon-9e25c00a6b-carsapi1.apiary-mock.com/manufacturers")
                .build().encode().toUri();

        try {
            CarsApiManufacturerDto[] manufacturerDto = restTemplate.getForObject(uri, CarsApiManufacturerDto[].class);
            return Arrays.asList(Optional.ofNullable(manufacturerDto).orElse(new CarsApiManufacturerDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<CarsApiModelDto> getModels() {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://private-anon-9e25c00a6b-carsapi1.apiary-mock.com/cars")
                .build().encode().toUri();

        try {
            CarsApiModelDto[] manufacturerDto = restTemplate.getForObject(uri, CarsApiModelDto[].class);
            return Arrays.asList(Optional.ofNullable(manufacturerDto).orElse(new CarsApiModelDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

}
