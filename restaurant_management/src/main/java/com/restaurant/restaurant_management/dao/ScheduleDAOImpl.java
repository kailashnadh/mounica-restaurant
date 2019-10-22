package com.restaurant.restaurant_management.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
				entityManager.createQuery("from Schedule where schedule_day=:date");
		theQuery.setParameter("date", ScheduleDay);
		List<Schedule> schedules = theQuery.getResultList();	
		return schedules;
	}

	@Override
	public void save(Schedule schedule) {
		Schedule dbSchedule = entityManager.merge(schedule);
		schedule.setSchedule_id(dbSchedule.getSchedule_id()); 
	}

	@Override
	public List<Schedule> getAllSchedulesbetweenDates(Date periodStart, Date periodEnd) {
		Query theQuery = 
				entityManager.createQuery("from Schedule where schedule_day between :date1 and :date2");
		theQuery.setParameter("date1", periodStart);
		theQuery.setParameter("date2", periodEnd);
		List<Schedule> schedules = theQuery.getResultList();	
		return schedules;
	}

	

	@Override
	public void deleteSchedule(Long schedule_id) {
		// TODO Auto-generated method stub
		Query theQuery = entityManager.createQuery(
				"delete from Schedule where id=:schedule_id");

theQuery.setParameter("schedule_id", schedule_id);

theQuery.executeUpdate();
	}

}