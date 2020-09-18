package com.kodilla.backend.domain.repository;

import com.kodilla.backend.domain.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelDao extends CrudRepository<Model, Long> {

    @Override
    Model save(Model model);

    @Override
    List<Model> findAll();

    @Override
    Optional<Model> findById(Long id);
}
