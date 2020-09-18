package com.kodilla.backend.domain.repository;

import com.kodilla.backend.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentDao extends CrudRepository<Rent, Long> {

    @Override
    Rent save(Rent rent);

    @Override
    List<Rent> findAll();

    @Override
    Optional<Rent> findById(Long id);
}
