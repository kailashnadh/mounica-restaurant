package com.restaurant.restaurant_management.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.restaurant_management.dao.EmployeeDAO;
import com.restaurant.restaurant_management.model.Employee;


@Service(value="employeeService")
public class EmployeeServiceImpl implements EmployeeService,UserDetailsService{

	private EmployeeDAO employeeDAO;
	@Autowired
	public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(Long id) {
Employee theEmployee = employeeDAO.findById(id);		
		return theEmployee;
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeDAO.save(theEmployee);
		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		employeeDAO.deleteById(id);
		
	}

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Employee emp = employeeDAO.findByEmailId(emailId);
		if(emp == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(emp.getEmail(), emp.getPassword(), getAuthority(emp));
	}
	private Set<SimpleGrantedAuthority> getAuthority(Employee emp) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        emp.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole_name()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	
	
	
}
