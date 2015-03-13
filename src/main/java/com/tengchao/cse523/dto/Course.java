package com.tengchao.cse523.dto;

import java.io.Serializable;

public class Course implements Serializable{
	
	private int courseId;
	private String nickId;	// such as CSE114
	private String courseName;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getNickId() {
		return nickId;
	}
	public void setNickId(String nickId) {
		this.nickId = nickId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	private int seats;
	private int availableSeats;
	private String semester;
	
	
	

}
