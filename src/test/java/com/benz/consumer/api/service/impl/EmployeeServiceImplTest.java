package com.benz.consumer.api.service.impl;

import com.benz.consumer.api.listener.EmployeeConsumer;
import com.benz.consumer.api.model.Employee;
import com.benz.consumer.api.service.EmployeeService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@DisplayName("EmployeeService")
public class EmployeeServiceImplTest {

    @MockBean
    private EmployeeConsumer employeeConsumer;

    @Autowired
    private EmployeeService employeeService;

    @Test
    @DisplayName("totalSalary")
    public void testTotalSalary()
    {
       List<Employee> expectedEmployees=getEmployees();

        String expectedResult="Total salary is "+totalSalary();

        Mockito.when(employeeConsumer.employees()).thenReturn(expectedEmployees);

        String actualResult=employeeService.getTotalSalary();

        assertEquals(expectedResult,actualResult,()->"expected salary was '"+expectedResult+"' but returned "+actualResult+"'");


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
