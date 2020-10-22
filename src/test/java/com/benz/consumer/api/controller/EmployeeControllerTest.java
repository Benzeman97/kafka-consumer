package com.benz.consumer.api.controller;

import com.benz.consumer.api.model.Employee;
import com.benz.consumer.api.service.EmployeeService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({SpringExtension.class})
@WebMvcTest
@DisplayName("EmployeeController")
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("totalSalary")
    public void testGetTotalSalary() throws Exception
    {
        String expectedResult="The total salary is "+totalSalary();

        Mockito.when(employeeService.getTotalSalary()).thenReturn(expectedResult);

        MvcResult result=mockMvc.perform(get("/emp/salary"))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult=result.getResponse().getContentAsString();

        assertEquals(expectedResult,actualResult,()->"expected result was '"+expectedResult+"' but returned '"+actualResult+"'");
    }


    private Employee employee_1()
    {
        Employee employee=new Employee();
        employee.setEmpId(101);
        employee.setEmpName("Nafaz Benzema");
        employee.setSalary(78000.00);
        return employee;
    }
    private List<Employee> getEmployees()
    {
        return new ArrayList<>(Arrays.asList(employee_1(),new Employee(102,"Kelly Brook",12000.00),
                new Employee(103,"Doto Kama",2000.00),new Employee(104,"Chopa Malli",45000.00)));
    }

    private double totalSalary()
    {
        double totalSalary=0.0;

        for(Employee emp:getEmployees())
            totalSalary+=emp.getSalary();

        return totalSalary;
    }
}
