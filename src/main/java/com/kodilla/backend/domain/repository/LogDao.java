package com.kodilla.backend.domain.repository;

import com.kodilla.backend.domain.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends CrudRepository<Log, Long> {

    @Override
    Log save(Log log);
}
