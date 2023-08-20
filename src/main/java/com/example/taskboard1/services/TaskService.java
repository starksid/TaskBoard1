package com.example.taskboard1.services;

import com.example.taskboard1.models.Employee;
import com.example.taskboard1.models.Manager;
import com.example.taskboard1.models.Task;
import com.example.taskboard1.models.TaskStatus;
import com.example.taskboard1.repositories.EmployeeRepository;
import com.example.taskboard1.repositories.ManagerRepository;
import com.example.taskboard1.repositories.TaskRepository;
import com.example.taskboard1.models.Employee;
import com.example.taskboard1.models.Manager;
import com.example.taskboard1.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    TaskRepository taskRepository;
    ManagerRepository managerRepository;
    EmployeeRepository employeeRepository;
    @Autowired
    public TaskService(
            TaskRepository taskRepository,
            ManagerRepository managerRepository,
            EmployeeRepository employeeRepository
    ){
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }

    public Task createTask(String name, String description, String dateTaskToCompletedBy, int managerId, int employeeId){
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        Optional<Manager> optionalManager = managerRepository.findById(managerId);
        Manager manager = optionalManager.get();
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.get();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = sdf.parse(dateTaskToCompletedBy);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        task.setTaskToBeCompletedBy(date);
        task.setTaskCompletedAt(new Date());
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        task.setManager(manager);
        task.setEmployee(employee);
        Task task1 = taskRepository.save(task);
        manager.getTasks().add(task1);
        employee.getTasks().add(task1);
        employeeRepository.save(employee);
        managerRepository.save(manager);
        return task1;




    }

    public List<Task> getAllTaskByEmployee(int employeeId){
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        if(optional.isEmpty()){
            return null;
        }
        Employee employee = optional.get();
        List<Task> task = employee.getTasks();
        return task;
    }

    public List<Task> getAllTaskByManager(int managerId){
        Optional<Manager> optionalManager = managerRepository.findById(managerId);
        if(optionalManager.isEmpty()){
            return null;
        }
        Manager manager = optionalManager.get();
        List<Task> tasks = manager.getTasks();
        return tasks;
    }

    public Task getTask(int taskId){
        Optional<Task> optional = taskRepository.findById(taskId);
        if(optional.isEmpty()){
            return null;
        }
        return optional.get();

    }


}
