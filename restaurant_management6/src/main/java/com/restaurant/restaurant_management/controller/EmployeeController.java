package com.restaurant.restaurant_management.controller;

import java.util.Optional;

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
		return employeeService.findAll();
	}

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/{employeeId}")
	public Employee employeeById(@PathVariable("employeeId") long employeeId) {

		return employeeService.findById(employeeId);
	}

	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employee.setPassword(bcryptEncoder.encode(employee.getPassword()));
		 employeeService.save(employee);
		 return employee;
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{messageId}")
	public void deleteCountry(@PathVariable("employeeId") long employeeId) {
		employeeService.deleteById(employeeId);
	}
}
