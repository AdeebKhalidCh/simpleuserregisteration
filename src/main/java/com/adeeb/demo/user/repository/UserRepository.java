package com.adeeb.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adeeb.demo.user.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUsername(String username);
 
}