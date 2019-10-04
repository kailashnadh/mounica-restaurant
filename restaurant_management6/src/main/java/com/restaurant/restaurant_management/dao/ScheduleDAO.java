package com.restaurant.restaurant_management.dao;

import java.util.Date;
import java.util.List;

import com.restaurant.restaurant_management.model.Schedule;

public interface ScheduleDAO {
	 public List<Schedule> getAllSchedulesForDate(Date ScheduleDay);
		public void save(Schedule schedule);
}
