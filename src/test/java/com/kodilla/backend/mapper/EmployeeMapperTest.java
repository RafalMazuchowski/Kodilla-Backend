package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Employee;
import com.kodilla.backend.domain.dto.EmployeeDto;
import com.kodilla.backend.domain.enums.Degree;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kodilla.backend.domain.enums.Degree.MANAGER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    }/*

    @Test
    void mapToEmployeeDto() {
        //Given
        Employee employee = new Employee(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));

        //When
        EmployeeDto mappingResult = employeeMapper.mapToEmployeeDto(employee);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.getEmployeeId()));
        assertEquals("Adams", mappingResult.getManufacturer());
        assertEquals("Fords", mappingResult.getModel());
        assertTrue(mappingResult.getBorrowed());
        assertEquals(Date.valueOf("2001-04-20"), mappingResult.getRentDate());
    }

    @Test
    public void mapToEmployeeDtoList() {
        //Given
        Employee employee = new Employee(1L, "BMW", "M3", true, Date.valueOf("2001-04-20"));
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        //When
        List<EmployeeDto> mappingResult = employeeMapper.mapToEmployeeDtoList(employeeList);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.get(0).getEmployeeId()));
        assertEquals("Adams", mappingResult.get(0).getManufacturer());
        assertEquals("Fords", mappingResult.get(0).getModel());
        assertTrue(mappingResult.get(0).getBorrowed());
        assertEquals(Date.valueOf("2001-04-20"), mappingResult.get(0).getRentDate());
    }
*/
}
