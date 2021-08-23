package com.example.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.entities.Course;
import com.example.entities.Department;
import com.example.entities.Instructor;
import com.example.repositories.CourseRepository;
import com.example.repositories.DepartmentRepository;
import com.example.services.exceptions.DatabaseException;
import com.example.services.exceptions.ResourceNotFoundException;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Course findById(Long id) {
		Optional<Course> obj = courseRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Course insert(Course obj) {
		return courseRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			courseRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Course update(Long id, Course obj) {
		try {
			Course entity = courseRepository.getById(id);
			updateData(entity, obj);
			return courseRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void updateData(Course entity, Course obj) {
		entity.setDuration(obj.getDuration());
		entity.setName(obj.getName());
		entity.setDepartment(obj.getDepartment());		
	}
	
	public List<Course> getCoursesByDepartment(Long id) {
		Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		List<Course> list = courseRepository.findByDepartment(department);
		return list;
	}
}
