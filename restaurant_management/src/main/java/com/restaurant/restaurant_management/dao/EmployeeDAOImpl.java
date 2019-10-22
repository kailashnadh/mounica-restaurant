package com.restaurant.restaurant_management.dao;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
				
					
				//String asB64 = Base64.getEncoder().encodeToString("some string".getBytes("utf-8"));
				
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
	
	@Override
	public Employee getMangerFromEmployeeId(Long id) {
		System.out.println("id is "+ id);
		Query theQuery = entityManager.createQuery(
				"select m from Employee e join e.manager m where e.emp_id=:empID");
		theQuery.setParameter("empID", id);
		List<Employee> managers= theQuery.getResultList();
		Employee theEmployee=null;
		System.out.println(managers);
		if(managers!=null) {
		 theEmployee =managers.get(0);
		}
		return theEmployee;
	}

	@Override
	public void setRolebyId(Long emp_id, Long role_id) {
		System.out.print("employeeid in setrole"+emp_id);
		System.out.print("roleid in setrole"+role_id);
		// TODO Auto-generated method stub
		Query theQuery=entityManager.createNativeQuery(
				"UPDATE employee_roles SET role_id = ? WHERE employee_roles.emp_id = ?");
		theQuery.setParameter(1, role_id);
		theQuery.setParameter(2, emp_id);
		theQuery.executeUpdate();
		System.out.println(theQuery);
	}

	@Override
	public void setMangerForEmployeeId(Long emp_id, Long manager_id) {
		// TODO Auto-generated method stub
		
		Query theQuery=entityManager.createQuery("update Employee set manager_id=:managerId where emp_id=:empId");
		theQuery.setParameter("managerId", manager_id);
		theQuery.setParameter("empId", emp_id);
		theQuery.executeUpdate();
	}

	@Override
	public List<Employee> getAllManagers() {
		// TODO Auto-generated method stub
	Query theQuery= entityManager.createNativeQuery("select * from employee where emp_id in"
			+ "(select emp_id from employee_roles where role_id=2)",Employee.class);	
	//List<Employee> employees=theQuery.getResultList();
	
	List<Employee> employees= theQuery.getResultList();
	return employees;
	}

}
