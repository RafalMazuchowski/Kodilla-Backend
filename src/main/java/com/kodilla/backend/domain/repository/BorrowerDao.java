package com.kodilla.backend.domain.repository;

import com.kodilla.backend.domain.Borrower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowerDao extends CrudRepository<Borrower, Long> {

    @Override
    Borrower save(Borrower borrower);

    @Override
    List<Borrower> findAll();

    @Override
    Optional<Borrower> findById(Long id);
}
