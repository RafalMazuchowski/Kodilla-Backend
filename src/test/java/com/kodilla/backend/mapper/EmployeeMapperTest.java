package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Employee;
import com.kodilla.backend.domain.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kodilla.backend.domain.enums.Degree.MANAGER;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeMapperTest {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    void mapToEmployee() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto(1L, "Thomas Anderson", "MANAGER");

        //When
        Employee mappingResult = employeeMapper.mapToEmployee(employeeDto);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.getEmployeeId()));
        assertEquals("Thomas Anderson", mappingResult.getFullName());
        assertEquals(MANAGER, mappingResult.getDegree());
    }

    @Test
    void mapToEmployeeDto() {
        //Given
        Employee employee = new Employee(1L, "Thomas Anderson", MANAGER);

        //When
        EmployeeDto mappingResult = employeeMapper.mapToEmployeeDto(employee);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.getEmployeeId()));
        assertEquals("Thomas Anderson", mappingResult.getFullName());
        assertEquals("MANAGER", mappingResult.getDegree());
    }

    @Test
    public void mapToEmployeeDtoList() {
        //Given
        Employee employee = new Employee(1L, "Thomas Anderson", MANAGER);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        //When
        List<EmployeeDto> mappingResult = employeeMapper.mapToEmployeeDtoList(employeeList);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.get(0).getEmployeeId()));
        assertEquals("Thomas Anderson", mappingResult.get(0).getFullName());
        assertEquals("MANAGER", mappingResult.get(0).getDegree());
    }
}
