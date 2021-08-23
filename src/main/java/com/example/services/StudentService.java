package com.example.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.entities.Student;
import com.example.repositories.StudentRepository;
import com.example.services.exceptions.DatabaseException;
import com.example.services.exceptions.ResourceNotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	public List<Student> findAll() {
		return repository.findAll();
	}

	public Student findById(Long id) {
		Optional<Student> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(obj));
	}

	public Student insert(Student obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Student update(Long id, Student obj) {
		try {
			Student entity = repository.getById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Student entity, Student obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setCpf(obj.getCpf());
		entity.setCourse(obj.getCourse());
	}

}
