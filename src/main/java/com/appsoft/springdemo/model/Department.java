package com.appsoft.springdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "depatrment_tbl")
public class Department {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int deptId;
	private String deptName;
	private String deptPhone;
	private String deptHead;
	
	
}
