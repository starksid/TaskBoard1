package com.example.taskboard1.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateEmployeeDtoRequest {
    private String name;
    private String email;
    private String password;
    private int managerId;
}
