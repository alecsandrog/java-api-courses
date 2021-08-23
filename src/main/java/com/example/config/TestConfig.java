package com.example.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.entities.Department;
import com.example.entities.Role;
import com.example.entities.User;
import com.example.repositories.DepartmentRepository;
import com.example.services.UserService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		Department d1 = new Department(null, "Comunicação");
		Department d2 = new Department(null, "TI");

		departmentRepository.saveAll(Arrays.asList(d1, d2));		

		userService.saveRole(new Role(null, "ROLE_USER"));
		userService.saveRole(new Role(null, "ROLE_MANAGER"));
		userService.saveRole(new Role(null, "ROLE_ADMIN"));
		userService.saveRole(new Role(null, "ROLE_SUPERADMIN"));

		userService.saveUser(new User(null, "John", "email@email.com", "john", "1234"));
		userService.saveUser(new User(null, "Lee", "email@email.com", "lee", "1234"));
		userService.saveUser(new User(null, "Pam", "email@email.com", "pam", "1234"));

		userService.addRoleToUser("john", "ROLE_USER");
		userService.addRoleToUser("john", "ROLE_MANAGER");
		userService.addRoleToUser("lee", "ROLE_ADMIN");
		userService.addRoleToUser("pam", "ROLE_SUPERADMIN");
		userService.addRoleToUser("john", "ROLE_SUPERADMIN");

	}
}
