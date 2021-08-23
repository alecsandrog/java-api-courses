package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Role;
import com.example.entities.User;
import com.example.repositories.RoleRepository;
import com.example.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public User saveUser(User obj) {
		return userRepository.save(obj);
	}

	public Role saveRole(Role obj) {
		return roleRepository.save(obj);
	}

	public void addRoleToUser(String username, String roleName) {
		User user = userRepository.findByUsername(username);
		List<Role> list = user.getRoles();
		list.add(roleRepository.findByName(roleName));
		user.setRoles(list);
		userRepository.save(user);
	}

	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
