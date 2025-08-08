package com.appsoft.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.springdemo.model.Department;

public interface DepartmentRespository extends JpaRepository<Department, Integer> {
	
	
}
