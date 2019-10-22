package com.restaurant.restaurant_management.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_management.dto.setManager;
import com.restaurant.restaurant_management.dto.setRole;
import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.service.EmployeeService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
private EmployeeService employeeService;
@Autowired
private BCryptPasswordEncoder bcryptEncoder;
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee) {	
		employee.setPassword(bcryptEncoder.encode(employee.getPassword()));
		employeeService.save(employee);
		return employee;
	}

	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public Iterable<Employee> allEmployees() {
    	List<Employee> e=employeeService.findAll();
    	return e;
	}

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/{employeeId}")
	public Employee employeeById(@PathVariable("employeeId") long employeeId) {

		return employeeService.findById(employeeId);
	}
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
 	@GetMapping("/manager/{employeeId}")
 	public Employee getManagerbyEmployeeId(@PathVariable("employeeId") long employeeId) {

 		return employeeService.getMangerFromEmployeeId(employeeId);
 	}
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
 	@GetMapping("/allManagers")
 	public List<Employee> getAllManagers() {

 		return employeeService.getAllManagers();
 	}
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
   	@PutMapping("/setManager")
   	public void setManagerbyEmployeeId(@RequestBody setManager details) {

   		 employeeService.setMangerForEmployeeId(details.getEmp_id(),details.getManager_id());
   	}
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee employee) {
		if(!employee.getPassword().isEmpty()) {
			employee.setPassword(bcryptEncoder.encode(employee.getPassword()));
		}
		 employeeService.save(employee);
		 return employee;
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{messageId}")
	public void deleteEmployee(@PathVariable("messageId") long employeeId) {
		employeeService.deleteById(employeeId);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/setRole")
	public void setRolebyId(@RequestBody setRole values) {
		
		System.out.println("in controller set roles"+values);
		 employeeService.setRolebyId(values.getEmp_id(), values.getRole_id());
	}
	
	
	
}
