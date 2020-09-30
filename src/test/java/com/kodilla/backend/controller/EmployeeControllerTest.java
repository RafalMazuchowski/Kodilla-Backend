package com.kodilla.backend.controller;

import com.google.gson.Gson;
import com.kodilla.backend.domain.Employee;
import com.kodilla.backend.domain.dto.EmployeeDto;
import com.kodilla.backend.mapper.EmployeeMapper;
import com.kodilla.backend.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.kodilla.backend.domain.enums.Degree.WORKER;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeMapper employeeMapper;

    @Test
    public void getEmployee() throws Exception {
        //Given
        EmployeeDto employeeDto = new EmployeeDto(1L, "Gruba Ryba", "WORKER");
        Employee employee = new Employee(1L, "Gruba Ryba", WORKER);

        when(employeeMapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);
        when(employeeMapper.mapToEmployee(employeeDto)).thenReturn(employee);
        when(employeeService.getEmployee(1L)).thenReturn(employee);

        //When&Then
        mockMvc.perform(get("/v1/employee/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].employeeId", is(1)))
                .andExpect(jsonPath("$[0].fullName", is("Gruba Ryba")))
                .andExpect(jsonPath("$[0].degree", is(WORKER)));
    }

    @Test
    public void getEmployees() throws Exception {
        //Given
        EmployeeDto employeeDto = new EmployeeDto(1L, "Gruba Ryba", "WORKER");
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(employeeDto);

        Employee employee = new Employee(1L, "Gruba Ryba", WORKER);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        when(employeeMapper.mapToEmployeeDtoList(employeeList)).thenReturn(employeeDtoList);
        when(employeeService.getAllEmployees()).thenReturn(employeeList);

        //When&Then
        mockMvc.perform(get("/v1/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].employeeId", is(1)))
                .andExpect(jsonPath("$[0].fullName", is("Gruba Ryba")))
                .andExpect(jsonPath("$[0].degree", is(WORKER)));
    }

    @Test
    public void createEmployee() throws Exception {
        //Given
        Employee employee = new Employee(1L, "Gruba Ryba", WORKER);
        EmployeeDto employeeDto = new EmployeeDto(1L, "Gruba Ryba", "WORKER");

        when(employeeMapper.mapToEmployee(employeeDto)).thenReturn(employee);
        when(employeeService.saveEmployee(employee)).thenReturn(employee);
        when(employeeMapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(employeeDto);

        //When&Then
        mockMvc.perform(get("/v1/employee/add")
                .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateEmployee() throws Exception {
        //Given
        Employee employee = new Employee(1L, "Gruba Ryba", WORKER);
        EmployeeDto employeeDto = new EmployeeDto(14L, "Piotr Strus", "TRAINEE");

        when(employeeMapper.mapToEmployee(ArgumentMatchers.any(EmployeeDto.class))).thenReturn(employee);
        when(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class))).thenReturn(employee);
        when(employeeMapper.mapToEmployeeDto(ArgumentMatchers.any(Employee.class))).thenReturn(employeeDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(employee);

        //When&Then
        mockMvc.perform(get("/v1/employee/update/1")
                .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].employeeId", is(14)))
                .andExpect(jsonPath("$[0].fullName", is("Piotr Strus")))
                .andExpect(jsonPath("$[0].degree", is("TRAINEE")));
    }

    @Test
    public void deleteEmployee() throws Exception {
        //Given
        Employee employee = new Employee(1L, "Gruba Ryba", WORKER);
        //When & Then
        mockMvc.perform(delete("/v1/employee/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
