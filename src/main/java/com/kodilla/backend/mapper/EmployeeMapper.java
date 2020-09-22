package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Employee;
import com.kodilla.backend.domain.dto.EmployeeDto;
import com.kodilla.backend.domain.enums.Degree;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getEmployeeId(),
                employeeDto.getFullName(),
                Degree.valueOf(employeeDto.getDegree()));
    }

    public EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getEmployeeId(),
                employee.getFullName(),
                employee.getDegree().toString());
    }

    public List<EmployeeDto> mapToEmployeeDtoList(List<Employee> employeeList) {
        return employeeList.stream()
                .map(e -> new EmployeeDto(
                        e.getEmployeeId(),
                        e.getFullName(),
                        e.getDegree().toString()))
                .collect(Collectors.toList());
    }
}
