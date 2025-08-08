package com.appsoft.springdemo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.springdemo.model.Department;
import com.appsoft.springdemo.repository.DepartmentRespository;
import com.appsoft.springdemo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRespository deptRepo;

	@Override
	public void addDept(Department dept) {
		deptRepo.save(dept);
		
	}

	@Override
	public void deleteDept(int id) {
		deptRepo.deleteById(id);
		
	}

	@Override
	public void updateDept(Department dept) {
		deptRepo.save(dept);
		
	}

	@Override
	public Department getDeptById(int id) {
		
		return deptRepo.findById(id).get();
	}

	@Override
	public List<Department> getAllDepts() {
		
		return deptRepo.findAll();
	}

}
