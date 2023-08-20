package com.example.taskboard1.repositories;

import com.example.taskboard1.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Optional<Manager> findByEmail(String email);

    @Override
    Optional<Manager> findById(Integer integer);

    @Override
    Manager save(Manager manager);
}
