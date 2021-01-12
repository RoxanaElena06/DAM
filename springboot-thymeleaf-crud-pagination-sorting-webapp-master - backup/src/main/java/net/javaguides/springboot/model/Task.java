package net.javaguides.springboot.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "title")
	private String title;
	
	//Assigned, unassigned or done
	@Column(name = "state")
	private String state;
	
	@Column(name = "value")
	private double value;
	
	@Column(name = "start_date")
	//@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private String startDate;
	
	@Column(name = "dead_line")
	//@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private String deadLine;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	
	
}
