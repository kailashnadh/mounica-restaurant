package com.restaurant.restaurant_management.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.model.Messages;
import com.restaurant.restaurant_management.model.Schedule;
@Repository
public class ScheduleDAOImpl implements ScheduleDAO {
	private EntityManager entityManager;
	@Autowired
	public ScheduleDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Schedule> getAllSchedulesForDate(Date ScheduleDay) {
		Query theQuery = 
				entityManager.createQuery("from Schedule where schedule_id=:date");
		theQuery.setParameter("date", ScheduleDay);
		List<Schedule> schedules = theQuery.getResultList();	
		return schedules;
	}

	@Override
	public void save(Schedule schedule) {
		Schedule dbSchedule = entityManager.merge(schedule);
		schedule.setSchedule_id(dbSchedule.getSchedule_id()); 
	}

}
