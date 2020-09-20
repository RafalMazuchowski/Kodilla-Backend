package com.kodilla.backend.service;

import com.kodilla.backend.domain.Employee;
import com.kodilla.backend.domain.dto.EmployeeDto;
import com.kodilla.backend.domain.repository.EmployeeDao;
import com.kodilla.backend.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    public Employee getEmployee(Long id) {
        return employeeDao.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    public Employee updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeDao.findById(id).orElseThrow(EmployeeNotFoundException::new);
        return employeeDao.save(employee);
    }

    public void deleteEmployee(long id) {
        employeeDao.deleteById(id);
    }
}
