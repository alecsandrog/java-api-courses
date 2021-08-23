package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCourseApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_SUPERADMIN"));
//			
//			userService.saveUser(new User(null, "John", "email@email.com", "john", "1234"));
//			userService.saveUser(new User(null, "Lee", "email@email.com", "lee", "1234"));
//			userService.saveUser(new User(null, "Pam", "email@email.com", "pam", "1234"));
//			
//			userService.addRoleToUser("john", "ROLE_USER");
//			userService.addRoleToUser("john", "ROLE_MANAGER");
//			userService.addRoleToUser("lee", "ROLE_ADMIN");
//			userService.addRoleToUser("pam", "ROLE_SUPERADMIN");
//			userService.addRoleToUser("john", "ROLE_SUPERADMIN");
//		};
//	}

}
