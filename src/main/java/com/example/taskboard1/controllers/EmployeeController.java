package com.example.taskboard1.controllers;

import com.example.taskboard1.dtos.*;
import com.example.taskboard1.models.Task;
import com.example.taskboard1.services.EmployeeService;
import com.example.taskboard1.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    EmployeeService employeeService;
    TaskService taskService;
    @Autowired
    public EmployeeController(
            EmployeeService employeeService,
            TaskService taskService
    ){
        this.employeeService = employeeService;
        this.taskService = taskService;
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<GetAllTaskByEmployeeDtoResponse> getAllTask(@PathVariable int employeeId){
        GetAllTaskByEmployeeDtoResponse response = new GetAllTaskByEmployeeDtoResponse();
        List<Task> tasks = taskService.getAllTaskByEmployee(employeeId);
        if(tasks==null){
            return ResponseEntity.badRequest().body(response);
        }
        response.setTasks(tasks);
        return ResponseEntity.ok(response);

    }


}
