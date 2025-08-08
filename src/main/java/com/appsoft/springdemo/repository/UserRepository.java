package com.appsoft.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.springdemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	User  findByUsernameAndPassword(String un, String psw);
}
