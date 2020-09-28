package com.kodilla.backend.domain;

import com.kodilla.backend.domain.repository.EmployeeDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kodilla.backend.domain.enums.Degree.TRAINEE;
import static com.kodilla.backend.domain.enums.Degree.WORKER;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {
    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    @Test
    public void testSaveAndFindAll() {
        //Given
        int initialNumberOfEmployees = employeeDao.findAll().size();
        Employee employee1 = new Employee(1L, "Gruba Ryba", WORKER);
        Employee employee2 = new Employee(14L, "piotr Strus", TRAINEE);

        //When
        employeeDao.save(employee1);
        employeeDao.save(employee2);
        List<Employee> employees = employeeDao.findAll();
        int numberOfEmployees = employees.size();

        //Then
        assertEquals(initialNumberOfEmployees + 2, numberOfEmployees);
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));

        //CleanUp
        employeeDao.delete(employee1);
        employeeDao.delete(employee1);
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Employee employee1 = new Employee(1L, "Gruba Ryba", WORKER);
        employeeDao.save(employee1);

        //When
        Employee foundEmployee = employeeDao.findById(employee1.getEmployeeId()).get();

        //Then
        assertEquals(employee1, foundEmployee);

        //CleanUp
        employeeDao.delete(employee1);
    }

    @Transactional
    @Test
    public void testDelete() {
        //Given
        int initialNumberOfEmployees = employeeDao.findAll().size();
        Employee employee1 = new Employee(1L, "Gruba Ryba", WORKER);
        employeeDao.save(employee1);

        //When
        employeeDao.delete(employee1);
        List<Employee> employees = employeeDao.findAll();
        int numberOfEmployees = employees.size();

        //Then
        assertEquals(initialNumberOfEmployees, numberOfEmployees);
        assertFalse(employees.contains(employee1));
    }
}