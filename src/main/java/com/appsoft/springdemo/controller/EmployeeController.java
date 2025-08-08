package com.appsoft.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.springdemo.model.Employee;
import com.appsoft.springdemo.service.DepartmentService;
import com.appsoft.springdemo.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService deptService;

	
	
	@GetMapping("/employee")
	public String getEmployee(Model model){
		 model.addAttribute("dList",deptService.getAllDepts());
		return "EmployeeForm";
	}
	
	@PostMapping("/employee")
	
	public String postEmployee(@ModelAttribute Employee emp) {
		
		empService.addEmp(emp);
		
		return "redirect:/employee";
	}
	
	@GetMapping("/employeeList")
	public String getAll(Model model) {
		model.addAttribute("eList", empService.getAllEmps());
		return "EmployeeList";
	}
	
	

    @GetMapping("/employee/edit")
    public String editEmployee(@RequestParam int id, Model model) {
        model.addAttribute("employee", empService.getEmpById(id));
        return "EmployeeEditForm";
    }

    @GetMapping("/employee/delete")
    public String deleteEmployee(@RequestParam int id) {
        empService.deleteEmp(id);
        return "redirect:/employeeList";
    }
    
    @PostMapping("/employee/update")
    public String updateEmployee(@ModelAttribute Employee emp) {
        empService.updateEmp(emp);
        return "redirect:/employeeList";
    }

}
