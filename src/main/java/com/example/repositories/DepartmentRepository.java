package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
