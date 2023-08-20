package com.example.taskboard1.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskDtoRequest {
    private String name;
    private String description;
    private String dateToBeCompleted;
    private int managerId;
    private int employeeId;
}
