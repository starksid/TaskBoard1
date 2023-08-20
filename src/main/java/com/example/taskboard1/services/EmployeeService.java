package com.example.taskboard1.services;

import com.example.taskboard1.dtos.DtoRequestStatus;
import com.example.taskboard1.models.Employee;
import com.example.taskboard1.models.EmployeeStatus;
import com.example.taskboard1.models.Manager;
import com.example.taskboard1.repositories.EmployeeRepository;
import com.example.taskboard1.repositories.ManagerRepository;
import com.example.taskboard1.repositories.TaskRepository;
import com.example.taskboard1.dtos.DtoRequestStatus;
import com.example.taskboard1.models.Employee;
import com.example.taskboard1.models.EmployeeStatus;
import com.example.taskboard1.models.Manager;
import com.example.taskboard1.repositories.EmployeeRepository;
import com.example.taskboard1.repositories.ManagerRepository;
import com.example.taskboard1.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmployeeService {
    private TaskRepository taskRepository;
    private EmployeeRepository employeeRepository;
    private ManagerRepository managerRepository;
    @Autowired
    public EmployeeService(
            TaskRepository taskRepository,
            EmployeeRepository employeeRepository,
            ManagerRepository managerRepository
    ){
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }

    public Employee signUp(String email, String password, String name, int managerId){
        Optional<Employee> optionalEmployee =employeeRepository.findByEmail(email);
        if(optionalEmployee.isPresent()){
            return null;
        }
        Employee employee = new Employee();
        Optional<Manager> optionalManager = managerRepository.findById(managerId);
        Manager manager = optionalManager.get();
        employee.setEmail(email);
        employee.setName(name);
        employee.setTasks(new ArrayList<>());
        employee.setPassword(password);
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE);
        employee.setManager(manager);
        Employee employee1 = employeeRepository.save(employee);
        manager.getEmployees().add(employee1);
        managerRepository.save(manager);
        return employee1;


    }

    public DtoRequestStatus removeEmployee(int employeeId){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if(optionalEmployee.isEmpty()){
            return DtoRequestStatus.FAILURE;
        }
        Employee employee = optionalEmployee.get();
        employeeRepository.delete(employee);
        return DtoRequestStatus.SUCCESS;
    }

    public DtoRequestStatus makeInactive(int id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isEmpty()){
            return DtoRequestStatus.FAILURE;
        }
        Employee employee = optionalEmployee.get();
        employee.setEmployeeStatus(EmployeeStatus.INACTIVE);
        employeeRepository.save(employee);
        return DtoRequestStatus.SUCCESS;
    }
    public DtoRequestStatus makeActive(int id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isEmpty()){
            return DtoRequestStatus.FAILURE;
        }
        Employee employee = optionalEmployee.get();
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE);
        employeeRepository.save(employee);
        return DtoRequestStatus.SUCCESS;
    }


}
