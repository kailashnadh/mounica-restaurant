package com.restaurant.restaurant_management.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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
import com.restaurant.restaurant_management.model.Roles;


@Service(value="employeeService")
public class EmployeeServiceImpl implements EmployeeService,UserDetailsService{

	private EmployeeDAO employeeDAO;
	//@Autowired
	//private EmployeeRolesDAO employeeRolesDAO;
	@Autowired(required=true)
	public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}
/*	@Autowired(required=false)
	public EmployeeServiceImpl(EmployeeRolesDAO theEmployeeRolesDAO) {
		employeeRolesDAO=theEmployeeRolesDAO;
	}*/

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
		/*else {
			Long id= emp.getEmp_id();
			emp.setRoles(employeeDAO.getRoles(id));
		}*/
		return new org.springframework.security.core.userdetails.User(emp.getEmail(), emp.getPassword(), getAuthority(emp));
	}
	private Set<SimpleGrantedAuthority> getAuthority(Employee emp) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Set<Roles> roles= emp.getRoles();
        Iterator<Roles> ir= roles.iterator();
        while(ir.hasNext()) {
        	System.out.println(ir.next().getRole_name());
        }
        emp.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole_name()));
		});
		//return authorities;
		// authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		 return authorities;
	}

	
	
	
}
