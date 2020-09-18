package com.kodilla.backend.domain.repository;

import com.kodilla.backend.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    @Override
    Employee save(Employee employee);

    @Override
    List<Employee> findAll();

    @Override
    Optional<Employee> findById(Long id);
}
