package com.example.taskboard1.dtos;

import com.example.taskboard1.models.Task;
import com.example.taskboard1.models.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class GetAllTaskByEmployeeDtoResponse {
    private List<Task> tasks;
}
