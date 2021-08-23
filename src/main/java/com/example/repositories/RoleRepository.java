package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
