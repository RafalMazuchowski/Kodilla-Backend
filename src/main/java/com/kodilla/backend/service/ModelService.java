package com.kodilla.backend.service;

import com.kodilla.backend.carModels.CarsApiClient;
import com.kodilla.backend.carModels.CarsApiManufacturerDto;
import com.kodilla.backend.carModels.CarsApiModelDto;
import com.kodilla.backend.carModels.ManufacturerDto;
import com.kodilla.backend.currencies.NbpCurrencyDto;
import com.kodilla.backend.domain.Model;
import com.kodilla.backend.domain.dto.ModelDto;
import com.kodilla.backend.domain.repository.ModelDao;
import com.kodilla.backend.exceptions.ModelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ModelService {

    @Autowired
    private ModelDao modelDao;

    @Autowired
    private CarsApiClient carsApiClient;

    public List<Model> getAllModels() {
        return modelDao.findAll();
    }

    public Model getModel(Long id) {
        return modelDao.findById(id).orElseThrow(ModelNotFoundException::new);
    }

    public Model saveModel(Model model) {
        return modelDao.save(model);
    }

    public void deleteModel(long id) {
        modelDao.deleteById(id);
    }

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
