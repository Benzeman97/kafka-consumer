package com.benz.consumer.api.service.impl;

import com.benz.consumer.api.listener.EmployeeConsumer;
import com.benz.consumer.api.model.Employee;
import com.benz.consumer.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeConsumer employeeConsumer;

    @Autowired
    public EmployeeServiceImpl(EmployeeConsumer employeeConsumer)
    {
        this.employeeConsumer=employeeConsumer;
    }

    @Override
    public String getTotalSalary() {
           return "Total salary is "+calculateSalary();
    }

   private double calculateSalary()
   {
       double totalSalary=0.0;

       List<Employee> employees=employeeConsumer.employees();

         for(Employee emp : employees)
             totalSalary += emp.getSalary();

         return totalSalary;
   }
}
