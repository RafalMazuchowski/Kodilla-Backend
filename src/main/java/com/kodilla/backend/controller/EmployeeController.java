package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.EmployeeDto;
import com.kodilla.backend.mapper.EmployeeMapper;
import com.kodilla.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping(value = "get/{id}")
    public EmployeeDto getEmployee(@PathVariable long id) {
        return employeeMapper.mapToEmployeeDto(employeeService.getEmployee(id));
    }

    @GetMapping(value = "all")
    public List<EmployeeDto> getEmployees() {
        return employeeMapper.mapToEmployeeDtoList(employeeService.getAllEmployees());
    }

    @PostMapping(value = "add", consumes = APPLICATION_JSON_VALUE)
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeMapper.mapToEmployeeDto(employeeService.saveEmployee(
                employeeMapper.mapToEmployee(employeeDto)));
    }

    @PutMapping(value = "update/{id}", consumes = APPLICATION_JSON_VALUE)
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto, @RequestParam long id) {
        return employeeMapper.mapToEmployeeDto(employeeService.updateEmployee(employeeDto, id));
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }
}
