package com.example.taskboard1.dtos;

import com.example.taskboard1.models.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class GetAllTaskByManagerDtoResponse {
    private List<Task> tasks;
}
