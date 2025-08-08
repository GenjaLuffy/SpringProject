package com.appsoft.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.springdemo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
