package com.restaurant.restaurant_management.dto;

public class setManager {
	private Long emp_id;
	private Long manager_id;
	
	public setManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public setManager(Long emp_id, Long manager_id) {
		super();
		this.emp_id = emp_id;
		this.manager_id = manager_id;
	}
	public Long getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}
	public Long getManager_id() {
		return manager_id;
	}
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}
	
}
