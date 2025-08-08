package com.appsoft.springdemo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.appsoft.springdemo.model.Employee;
import com.appsoft.springdemo.model.Product;
import com.appsoft.springdemo.repository.ProductRepository;
import com.appsoft.springdemo.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService empService;
	@Autowired
	private ProductRepository productReop;
	
	
	@GetMapping("api/emp/list")
	public List<Employee> getAll(){
		
		return empService.getAllEmps();
	}
	
	@PostMapping("api/emp/add")
	public String add(@RequestBody Employee emp){
		empService.addEmp(emp);
		return "added success";
	}
	
	@GetMapping("/api/emp/{id}")
	public Employee getOne(@PathVariable Long id) {
		
		return empService.getEmpById(id);
	}
	
	@PutMapping("/api/emp/update")
	public String update(@RequestBody Employee emp) {
		
		empService.updateEmp(emp);
		return "Update Success!!";
	}
	@DeleteMapping("/api/emp/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		empService.deleteEmp(id);
		return "Delete Success";
	}
	
	@GetMapping("/api/j2o")
	public String jsonToObject() {
		
		RestTemplate temp = new RestTemplate();
		Employee emp = temp.getForObject("http://localhost:8080/api/emp/2", Employee.class);
		
		
		return"FirstName = " + emp.getFname();
	}
	@GetMapping("/api/ja2oa")
	public String jsonArraytoObjArray() {
		RestTemplate temp = new RestTemplate();
		Employee emps[] =  temp.getForObject("http://localhost:8080/api/emp/list", Employee[].class);
		
		return "Name = " +emps[7].getFname();
	}
	
	
	@GetMapping("/api/loadProduct")
	public String loadProduct() {
		RestTemplate temp = new RestTemplate();
		Product prods[] = temp.getForObject("https://fakestoreapi.com/products", Product[].class);
		productReop.saveAll(List.of(prods));
		return"Success";
	}
	
}
