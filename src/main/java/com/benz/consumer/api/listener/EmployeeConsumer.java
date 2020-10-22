package com.benz.consumer.api.listener;

import com.benz.consumer.api.model.Employee;
import com.benz.consumer.api.model.EmployeeList;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeConsumer {

    private List<Employee> employees;

    @KafkaListener(topics = {"Kafka_Employee"},groupId = "group_employee",containerFactory = "employeeKafkaListenerContainerFactory")
    public void consumeEmployees(EmployeeList employees)
    {
          this.employees=employees.getEmployeeList();
    }

    public List<Employee> employees()
    {
        return employees;
    }
}
