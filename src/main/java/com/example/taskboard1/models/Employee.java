package com.example.taskboard1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Employee extends BaseModel {

    private String Name;
    private String email;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private EmployeeStatus employeeStatus;

    @OneToMany
    private List<Task> tasks;
    @ManyToOne
    private Manager manager;
}
