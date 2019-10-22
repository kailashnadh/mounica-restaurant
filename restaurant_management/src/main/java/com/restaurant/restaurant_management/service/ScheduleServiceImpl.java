package com.restaurant.restaurant_management.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.restaurant_management.dao.EmployeeDAO;
import com.restaurant.restaurant_management.dao.ScheduleDAO;
import com.restaurant.restaurant_management.model.Schedule;

@Service
public class ScheduleServiceImpl implements ScheduleService {
private ScheduleDAO scheduleDAO;
@Autowired(required=true)
public ScheduleServiceImpl(ScheduleDAO theScheduleDAO) {
	scheduleDAO = theScheduleDAO;
}
	@Override
	public List<Schedule> getAllSchedulesForDate(Date ScheduleDay) {
		// TODO Auto-generated method stub
		return scheduleDAO.getAllSchedulesForDate(ScheduleDay);
	}

	@Override
	@Transactional
	public void save(Schedule schedule) {
		// TODO Auto-generated method stub
	scheduleDAO.save(schedule);

	}
	@Override
	public List<Schedule> getAllSchedulesbetweenDates(Date periodStart, Date periodEnd) {
		// TODO Auto-generated method stub
		return scheduleDAO.getAllSchedulesbetweenDates(periodStart, periodEnd);
	}
	@Override
	@Transactional
	public void deleteSchedule(Long schedule_id) {
		scheduleDAO.deleteSchedule(schedule_id);
		
	}

}