package com.appsoft.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.springdemo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
