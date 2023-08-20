package com.example.taskboard1.controllers;

import com.example.taskboard1.dtos.*;
import com.example.taskboard1.models.Employee;
import com.example.taskboard1.models.Manager;
import com.example.taskboard1.models.Task;
import com.example.taskboard1.services.EmployeeService;
import com.example.taskboard1.services.ManagerService;
import com.example.taskboard1.services.TaskService;
import com.example.taskboard1.dtos.CreateTaskDtoRequest;
import com.example.taskboard1.dtos.CreateTaskDtoResponse;
import com.example.taskboard1.dtos.DtoRequestStatus;
import com.example.taskboard1.dtos.MakingActiveToEmployeeDtoResponse;
import com.example.taskboard1.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    ManagerService managerService;
    TaskService taskService;
    EmployeeService employeeService;
    @Autowired
    public ManagerController(
            ManagerService managerService,
            TaskService taskService,
            EmployeeService employeeService
    ){
        this.managerService = managerService;
        this.taskService = taskService;
        this.employeeService = employeeService;
    }
    @PostMapping("/signup")
    public ResponseEntity<SignUpManagerDtoResponse> signUp(@RequestBody SignUpManagerDtoRequest request){
        SignUpManagerDtoResponse response = new SignUpManagerDtoResponse();
        Manager manager = managerService.signUp(request.getName(), request.getEmail(), request.getPassword());
        if(manager==null){
            return ResponseEntity.badRequest().body(response);
        }
        response.setManager(manager);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signin")
    public ResponseEntity<SignInManagerDtoResponse> signIn(@RequestBody SignInManagerDtoRequest request){
        SignInManagerDtoResponse response = new SignInManagerDtoResponse();
        Manager manager = managerService.signIn(request.getEmail(), request.getPassword());
        if(manager==null){
            return ResponseEntity.badRequest().body(response);
        }
        response.setManager(manager);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/createEmployee")
    public ResponseEntity<CreateEmployeeDtoResponse> createEmployee(@RequestBody CreateEmployeeDtoRequest request){
        CreateEmployeeDtoResponse response = new CreateEmployeeDtoResponse();
        Employee employee = employeeService.signUp(request.getEmail(),request.getPassword(), request.getName(), request.getManagerId());
        if(employee==null){
            return ResponseEntity.badRequest().body(response);
        }
        response.setEmployee(employee);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/removeEmployee")
    public ResponseEntity<RemoveEmployeeDtoResponse> removeEmployee(@RequestBody RemoveEmployeeDtoRequest request){
        RemoveEmployeeDtoResponse response = new RemoveEmployeeDtoResponse();
        DtoRequestStatus dtoRequestStatus = employeeService.removeEmployee(request.getEmployeeId());
        if(dtoRequestStatus.equals(DtoRequestStatus.FAILURE)){
            return ResponseEntity.badRequest().body(response);
        }
        response.setDtoRequestStatus(DtoRequestStatus.SUCCESS);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/inactiveEmployee")
    public ResponseEntity<MakingInactiveToEmployeeDtoResponse> makeInactiveEmployee(@RequestBody MakingInactiveToEmployeeDtoRequest request){
        MakingInactiveToEmployeeDtoResponse response = new MakingInactiveToEmployeeDtoResponse();
        DtoRequestStatus requestStatus = employeeService.makeInactive(request.getEmployeeId());
        if(requestStatus.equals(DtoRequestStatus.FAILURE)){
            return ResponseEntity.badRequest().body(response);
        }
        response.setDtoRequestStatus(DtoRequestStatus.SUCCESS);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/activeEmployee")
    public ResponseEntity<MakingActiveToEmployeeDtoResponse> makeInactiveEmployee(@RequestBody MakingActiveToEmployeeDtoRequest request){
        MakingActiveToEmployeeDtoResponse response = new MakingActiveToEmployeeDtoResponse();
        DtoRequestStatus requestStatus = employeeService.makeActive(request.getEmployeeId());
        if(requestStatus.equals(DtoRequestStatus.FAILURE)){
            return ResponseEntity.badRequest().body(response);
        }
        response.setDtoRequestStatus(DtoRequestStatus.SUCCESS);
        return ResponseEntity.ok().build();

    }
    @PostMapping("/createTask")
    public ResponseEntity<CreateTaskDtoResponse> createTask(@RequestBody CreateTaskDtoRequest request){
        CreateTaskDtoResponse response = new CreateTaskDtoResponse();
        Task task = taskService.createTask(request.getName(), request.getDescription(), request.getDateToBeCompleted(), request.getManagerId(), request.getEmployeeId());
        if(task==null){
            return ResponseEntity.badRequest().body(response);
        }
        response.setTask(task);
        return ResponseEntity.ok(response);

    }
    @PostMapping("/getAllTask")
    public ResponseEntity<GetAllTaskByManagerDtoResponse> getAllTask(@RequestBody GetAllTaskByManagerDtoRequest request){
        GetAllTaskByManagerDtoResponse response = new GetAllTaskByManagerDtoResponse();
        List<Task> tasks = taskService.getAllTaskByManager(request.getManagerId());
        if(tasks==null){
            return ResponseEntity.badRequest().body(response);
        }
        response.setTasks(tasks);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getTask")
    public ResponseEntity<GetTaskFromEmployeeByManagerDtoResponse> getTask(@RequestBody GetTaskFromEmployeeByManagerDtoRequest request){
        GetTaskFromEmployeeByManagerDtoResponse response = new GetTaskFromEmployeeByManagerDtoResponse();
        Task task = taskService.getTask(request.getTaskId());
        if(task==null){
            return ResponseEntity.badRequest().body(response);
        }
        response.setTask(task);
        return ResponseEntity.ok(response);
    }


}
