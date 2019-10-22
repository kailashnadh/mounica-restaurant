package com.restaurant.restaurant_management.service;
import java.util.Date;
import java.util.List;

import com.restaurant.restaurant_management.model.Schedule;

public interface ScheduleService {
	public List<Schedule> getAllSchedulesForDate(Date ScheduleDay);
	public List<Schedule> getAllSchedulesbetweenDates(Date periodStart,Date periodEnd);
	public void save(Schedule schedule);
	//updateSchedule
	//public Schedule updateSchedule(Long schedule_id);
	//delete schedule
	public void deleteSchedule(Long schedule_id);
}
