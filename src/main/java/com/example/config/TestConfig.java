package com.example.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.entities.Department;
import com.example.repositories.DepartmentRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public void run(String... args) throws Exception {
		Department d1 = new Department(null, "Comunicação");
		Department d2 = new Department(null, "TI");

		departmentRepository.saveAll(Arrays.asList(d1, d2));
	}
}
