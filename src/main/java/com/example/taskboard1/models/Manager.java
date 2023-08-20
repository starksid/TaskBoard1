package com.example.taskboard1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Manager extends BaseModel {
    private String Name;
    private String email;
    private String password;
    @OneToMany
    private List<Employee> employees;
    @OneToMany
    private List<Task> tasks;
}
