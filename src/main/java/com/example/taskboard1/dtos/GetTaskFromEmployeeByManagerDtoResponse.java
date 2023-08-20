package com.example.taskboard1.dtos;

import com.example.taskboard1.models.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTaskFromEmployeeByManagerDtoResponse {
    private Task task;
}
