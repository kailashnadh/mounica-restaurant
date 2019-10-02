package com.restaurant.restaurant_management.service;

import java.util.List;


import com.restaurant.restaurant_management.model.Employee;

public interface EmployeeService {
	public List<Employee> findAll();
	
	public Employee findById(Long id);
	
	public void save(Employee theEmployee);
	
	public void deleteById(Long id);
	public Employee getMangerFromEmployeeId(Long id);
}
