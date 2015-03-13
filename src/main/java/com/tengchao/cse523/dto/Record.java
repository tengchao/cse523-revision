package com.tengchao.cse523.dto;

import java.io.Serializable;
import java.util.Map;

public class Record implements Serializable{
	
	private int pid;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private Map<String, Double> data;	// record data
	
	private int cid;
	private String courseName;
	private String nickId;	// ex. CSE 114
	private String semester;
	private int section;
	private String expectation;
	private double score;
	private String grade;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Map<String, Double> getData() {
		return data;
	}
	public void setData(Map<String, Double> data) {
		this.data = data;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getNickId() {
		return nickId;
	}
	public void setNickId(String nickId) {
		this.nickId = nickId;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public String getExpectation() {
		return expectation;
	}
	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	

}
