package com.tsb.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String>{
	
	public Optional<Users> findById(String id); 
}
