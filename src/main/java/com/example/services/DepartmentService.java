package com.example.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.entities.Department;
import com.example.repositories.DepartmentRepository;
import com.example.services.exceptions.DatabaseException;
import com.example.services.exceptions.ResourceNotFoundException;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository repository;

	public List<Department> findAll() {
		return repository.findAll();
	}

	public Department findById(int id) {
		Optional<Department> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Department insert(Department obj) {
		return repository.save(obj);
	}

	public void delete(int id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Department update(int id, Department obj) {
		try {
			Department entity = repository.getById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Department entity, Department obj) {
		entity.setName(obj.getName());
		;
	}
}
