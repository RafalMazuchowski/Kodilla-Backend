package com.kodilla.backend.service;

import com.kodilla.backend.domain.Employee;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kodilla.backend.domain.enums.Degree.TRAINEE;
import static com.kodilla.backend.domain.enums.Degree.WORKER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Transactional
    @Test
    public void testGetAllEmployees() {
        //Given
        int initialNumberOfEmployees = employeeService.getAllEmployees().size();
        Employee employee1 = new Employee(1L, "Gruba Ryba", WORKER);
        Employee employee2 = new Employee(14L, "Piotr Strus", TRAINEE);

        //When
        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);
        List<Employee> employees = employeeService.getAllEmployees();
        int numberOfEmployees = employees.size();

        //Then
        assertEquals(initialNumberOfEmployees + 2, numberOfEmployees);
        assertEquals(employee1.getFullName(), "Gruba Ryba");
        assertEquals(employee2.getFullName(), "Piotr Strus");

        //CleanUp
        employee1.setEmployeeId(employees.get(initialNumberOfEmployees).getEmployeeId());
        employee2.setEmployeeId(employees.get(initialNumberOfEmployees).getEmployeeId());
        employeeService.deleteEmployee(employee1.getEmployeeId());
        employeeService.deleteEmployee(employee2.getEmployeeId());
    }

    @Transactional
    @Test
    public void testGetEmployee() {
        //Given
        Employee employee1 = new Employee(1L, "Gruba Ryba", WORKER);
        employeeService.saveEmployee(employee1);
        employee1.setEmployeeId(employeeService.getAllEmployees().get(employeeService.getAllEmployees().size() - 1).getEmployeeId());

        //When
        Employee foundEmployee = employeeService.getEmployee(employee1.getEmployeeId());

        //Then
        assertEquals(employee1, foundEmployee);

        //CleanUp
        employeeService.deleteEmployee(employee1.getEmployeeId());
    }

    @Transactional
    @Test
    public void testDeleteEmployee() {
        //Given
        int initialNumberOfEmployees = employeeService.getAllEmployees().size();
        Employee employee1 = new Employee(1L, "Gruba Ryba", WORKER);
        employeeService.saveEmployee(employee1);
        employee1.setEmployeeId(employeeService.getAllEmployees().get(employeeService.getAllEmployees().size() - 1).getEmployeeId());

        //When
        employeeService.deleteEmployee(employee1.getEmployeeId());
        List<Employee> employees = employeeService.getAllEmployees();
        int numberOfEmployees = employees.size();

        //Then
        assertEquals(initialNumberOfEmployees, numberOfEmployees);
        assertFalse(employees.contains(employee1));
    }
}