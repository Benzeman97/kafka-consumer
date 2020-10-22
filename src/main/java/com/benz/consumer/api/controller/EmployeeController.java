package com.benz.consumer.api.controller;

import com.benz.consumer.api.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService)
    {
        this.employeeService=employeeService;
    }

    @GetMapping("/salary")
    public ResponseEntity<String> getTotalSalary()
    {
         return ResponseEntity.ok(employeeService.getTotalSalary());
    }
}
