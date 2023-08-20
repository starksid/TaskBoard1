package com.example.taskboard1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
public class Task extends BaseModel {
    private String name;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private TaskStatus taskStatus;
    private Date taskToBeCompletedBy;
    private Date taskCompletedAt;
    @ManyToOne
    private Manager manager;
    @ManyToOne
    private Employee employee;



}
