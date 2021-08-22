package com.example.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.entities.Department;
import com.example.entities.Instructor;
import com.example.repositories.DepartmentRepository;
import com.example.repositories.InstructorRepository;
import com.example.services.exceptions.DatabaseException;
import com.example.services.exceptions.ResourceNotFoundException;

@Service
public class InstructorService {
	@Autowired
	private InstructorRepository instructorRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Instructor> findAll() {
		return instructorRepository.findAll();
	}

	public Instructor findById(Long id) {
		Optional<Instructor> obj = instructorRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(obj));
	}

	public Instructor insert(Instructor obj) {
		return instructorRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			instructorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Instructor update(Long id, Instructor obj) {
		try {
			Instructor entity = instructorRepository.getById(id);
			updateData(entity, obj);
			return instructorRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void updateData(Instructor entity, Instructor obj) {
		entity.setName(obj.getName());
		entity.setPhone(obj.getPhone());
		entity.setEmail(obj.getEmail());
		entity.setDepartment(obj.getDepartment());
	}

	public List<Instructor> getInstructorsByDepartment(Long id) {
		Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		List<Instructor> list = instructorRepository.findByDepartment(department);
		return list;
	}

}
