package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Course;
import com.example.entities.Department;

public interface CourseRepository extends JpaRepository<Course, Long> {
	public List<Course> findByDepartment(Department department);
}
