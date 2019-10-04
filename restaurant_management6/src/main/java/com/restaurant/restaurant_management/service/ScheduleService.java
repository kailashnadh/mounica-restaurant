package com.restaurant.restaurant_management.service;

import java.util.Date;
import java.util.List;

import com.restaurant.restaurant_management.model.Schedule;

public interface ScheduleService {
 public List<Schedule> getAllSchedulesForDate(Date ScheduleDay);
	public void save(Schedule schedule);
}
