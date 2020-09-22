package com.kodilla.backend.service;

import com.kodilla.backend.domain.Car;
import com.kodilla.backend.domain.repository.CarDao;
import com.kodilla.backend.exceptions.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CarService {

    @Autowired
    private CarDao carDao;

    public List<Car> getAllCars() {
        return carDao.findAll();
    }

    public Car getCar(Long id) {
        return carDao.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public Car saveCar(Car car) {
        return carDao.save(car);
    }

    public void deleteCar(long id) {
        carDao.deleteById(id);
    }
}
