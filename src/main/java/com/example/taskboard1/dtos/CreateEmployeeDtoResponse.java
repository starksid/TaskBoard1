package com.example.taskboard1.dtos;

import com.example.taskboard1.models.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeDtoResponse {
    private Employee employee;
}
