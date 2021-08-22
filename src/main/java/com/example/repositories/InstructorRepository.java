package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Department;
import com.example.entities.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
	List<Instructor> findByDepartment(Department department);
}
