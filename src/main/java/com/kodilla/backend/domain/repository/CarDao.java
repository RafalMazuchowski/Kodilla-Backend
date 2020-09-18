package com.kodilla.backend.domain.repository;

import com.kodilla.backend.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarDao extends CrudRepository<Car, Long> {

    @Override
    Car save(Car car);

    @Override
    List<Car> findAll();

    @Override
    Optional<Car> findById(Long id);
}
