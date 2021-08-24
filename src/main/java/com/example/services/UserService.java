package com.example.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entities.Role;
import com.example.entities.User;
import com.example.repositories.RoleRepository;
import com.example.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		}  else {
			log.error("User found in the database: {}", username);			
		}	
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	@Autowired
	private RoleRepository roleRepository;

	public User saveUser(User obj) {
		obj.setPassword(passwordEncoder.encode(obj.getPassword()));
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
