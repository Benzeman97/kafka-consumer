package com.benz.kafka.consumer.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private String userName;
    private String deptName;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
