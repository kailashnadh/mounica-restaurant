package com.restaurant.restaurant_management.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.model.Roles;
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		// create a query
				Query theQuery = 
						entityManager.createQuery("from Employee");
				
				// execute query and get result list
				List<Employee> employees = theQuery.getResultList();
				
				// return the results		
				return employees;
	}

	@Override
	public Employee findById(Long id) {
		// get employee
		Employee theEmployee = 
				entityManager.find(Employee.class, id);
		
		// return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// save or update the employee
				Employee dbEmployee = entityManager.merge(theEmployee);
				
				// update with id from db ... so we can get generated id for save/insert
				theEmployee.setEmp_id(dbEmployee.getEmp_id());

	}

	@Override
	public void deleteById(Long id) {
		// delete object with primary key
		Query theQuery = entityManager.createQuery(
							"delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", id);
		
		theQuery.executeUpdate();
	}

	@Override
	public Employee findByEmailId(String emailId) {
		// get employee
				//Employee theEmployee = 
						//entityManager.find(Employee.class, emailId);
				Query theQuery = entityManager.createQuery(
						"select e from Employee e where email=:emailId");
				theQuery.setParameter("emailId", emailId);
				Employee theEmployee =(Employee)theQuery.getSingleResult();
				// return employee
				return theEmployee;
	}
	@Override
	public Set<Roles> getRoles(Long emp_id) {
		/*Query theQuery = entityManager.createQuery(
				"select r from Roles r JOIN Employee_roles er "
				+ "with er.emp_id=:emp_id");*/
		Query theQuery =entityManager.createNativeQuery("select * from Roles r join Employee_roles er"
				+ "on r.id=er.role_id where er.emp_id=:emp_id");
		theQuery.setParameter("emp_id", emp_id);
		Set<Roles> roles = new HashSet();
			List<Roles> rolesList=theQuery.getResultList();
			for(Roles r :rolesList) {
				if( !roles.contains(r)) {
					roles.add(r);
				}
			}	
		return roles;
	}

}
