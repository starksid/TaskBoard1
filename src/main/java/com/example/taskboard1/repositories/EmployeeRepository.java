package com.example.taskboard1.repositories;

import com.example.taskboard1.models.Employee;
import com.example.taskboard1.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Override
    Optional<Employee> findById(Integer integer);

    Optional<Employee> findByEmail(String email);

    @Override
    Employee save(Employee entity);

    @Override
    void delete(Employee entity);
}
