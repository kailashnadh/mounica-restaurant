package com.restaurant.restaurant_management.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_management.model.Schedule;
import com.restaurant.restaurant_management.service.ScheduleService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	private ScheduleService scheduleService;
	@Autowired
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	@Transactional
	@PostMapping("/add")
	public Schedule addSchedule(@RequestBody Schedule schedule) {
		scheduleService.save(schedule);
		return schedule;
	}
	@GetMapping("/getSchedule/{ScheduleDay}")
	public List<Schedule> schedulesForDate(@PathVariable("ScheduleDay") String ScheduleDay){
		Date date1=null;
		try {
			System.out.println(ScheduleDay);
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(ScheduleDay);
			System.out.println(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scheduleService.getAllSchedulesForDate(date1);
	}
	@GetMapping("/getScheduleBetweenDates/{periodStart}/{periodEnd}")
	public List<Schedule> schedulesForDates(@PathVariable("periodStart") String periodStart,@PathVariable("periodEnd") String periodEnd){
		Date periodStart1=null,periodEnd1=null;
		try {
		periodStart1 = new SimpleDateFormat("yyyy-MM-dd").parse(periodStart);
		periodEnd1 = new SimpleDateFormat("yyyy-MM-dd").parse(periodEnd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scheduleService.getAllSchedulesbetweenDates(periodStart1, periodEnd1);
	}
//updateSchedule
//delete schedule
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{scheduleId}")
	public void deleteEmployee(@PathVariable("scheduleId") long scheduleId) {
		scheduleService.deleteSchedule(scheduleId);
	}
}
