package com.restaurant.restaurant_management.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Table(name="schedule")
@Entity
public class Schedule {
	@Id
	@Column(name = "id")
	private Long schedule_id;
	
	@NotNull
	@ManyToOne
	private Employee emp_id;
	
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date schedule_day;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    @Temporal(TemporalType.TIME)
    private Date schedule_start;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    @Temporal(TemporalType.TIME)
    private Date schedule_end;

	public Long getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(Long schedule_id) {
		this.schedule_id = schedule_id;
	}

	public Employee getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Employee emp_id) {
		this.emp_id = emp_id;
	}

	public Date getSchedule_day() {
		return schedule_day;
	}

	public void setSchedule_day(Date schedule_day) {
		this.schedule_day = schedule_day;
	}

	public Date getSchedule_start() {
		return schedule_start;
	}

	public void setSchedule_start(Date schedule_start) {
		this.schedule_start = schedule_start;
	}

	public Date getSchedule_end() {
		return schedule_end;
	}

	public void setSchedule_end(Date schedule_end) {
		this.schedule_end = schedule_end;
	}
	
}
