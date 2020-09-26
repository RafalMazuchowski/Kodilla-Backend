package com.kodilla.backend.carModels;

import com.kodilla.backend.carModels.CarsApiClient;
import com.kodilla.backend.carModels.CarsApiManufacturerDto;
import com.kodilla.backend.carModels.CarsApiModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ModelService {

    @Autowired
    private CarsApiClient carsApiClient;

    public List<CarsApiManufacturerDto> fetchManufacturers() {
        return carsApiClient.getManufacturers();
    }

    public List<CarsApiModelDto> fetchModels(String manufacturer) {
        List<CarsApiModelDto> models = carsApiClient.getModels();

        return models.stream()
                .filter(m -> m.getMake().equals(manufacturer))
                .collect(Collectors.toList());
    }
}
