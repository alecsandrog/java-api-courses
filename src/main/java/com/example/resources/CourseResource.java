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

import com.example.entities.Course;
import com.example.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseResource {
	@Autowired
	private CourseService courseService;

	@GetMapping
	public ResponseEntity<List<Course>> findAll() {
		List<Course> list = courseService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> findById(@PathVariable Long id) {
		Course obj = courseService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Course> insert(@RequestBody Course obj) {
		obj = courseService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		courseService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course obj) {
		obj = courseService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/department/{id}")
	public ResponseEntity<List<Course>> getCoursesByDepartment(@PathVariable Long id) {
		List<Course> list = courseService.getCoursesByDepartment(id);
		return ResponseEntity.ok().body(list);
	}
}
