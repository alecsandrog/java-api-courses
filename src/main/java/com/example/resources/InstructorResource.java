package com.example.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.entities.Instructor;
import com.example.services.InstructorService;

@RestController
@RequestMapping(value = "/instructors")
public class InstructorResource {
	@Autowired
	private InstructorService service;

	@GetMapping
	public ResponseEntity<List<Instructor>> findAll() {
		List<Instructor> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Instructor> findById(@PathVariable Long id) {
		Instructor obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Instructor> insert(@RequestBody Instructor obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Instructor> update(@PathVariable Long id, @RequestBody Instructor obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/department/{id}")
	public ResponseEntity<List<Instructor>> getInstructorByDepartments(@PathVariable Long id) {
		List<Instructor> list = service.getInstructorsByDepartment(id);
		return ResponseEntity.ok().body(list);
	}
}
