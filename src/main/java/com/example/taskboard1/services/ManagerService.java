package com.example.taskboard1.services;

import com.example.taskboard1.models.Manager;
import com.example.taskboard1.repositories.ManagerRepository;
import com.example.taskboard1.repositories.TaskRepository;
import com.example.taskboard1.models.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ManagerService {
    TaskRepository taskRepository;
    ManagerRepository managerRepository;
    @Autowired
    public ManagerService(TaskRepository taskRepository, ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
        this.taskRepository = taskRepository;
    }
    public Manager signUp(String name, String email, String password){
        Optional<Manager> optionalManager = managerRepository.findByEmail(email);
        if(optionalManager.isPresent()){
            return null;
        }
        Manager manager = new Manager();
        manager.setEmail(email);
        manager.setName(name);
        manager.setPassword(password);
        manager.setEmployees(new ArrayList<>());
        manager.setTasks(new ArrayList<>());
        Manager manager1 = managerRepository.save(manager);
        return manager1;

    }
    public Manager signIn(String email, String password){
        Optional<Manager> optionalManager = managerRepository.findByEmail(email);
        if(optionalManager.isEmpty()){
            return null;
        }

        Manager manager = optionalManager.get();
        if (manager.getPassword().equals(password)){
            return manager;
        }
        return null;


    }


}
