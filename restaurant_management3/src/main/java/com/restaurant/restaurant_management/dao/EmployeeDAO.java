package com.restaurant.restaurant_management.dao;

import java.util.List;

import com.restaurant.restaurant_management.model.Employee;

public interface EmployeeDAO {
	public List<Employee> findAll();
	
	public Employee findById(Long id);
	public Employee findByEmailId(String emailId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(Long id);
	

}
