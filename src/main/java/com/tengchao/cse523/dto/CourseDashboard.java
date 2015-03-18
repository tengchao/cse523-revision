package com.tengchao.cse523.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CourseDashboard implements Serializable{

	private String semester;
	private List<Course> courses;
	private Map<Integer, String> roleMap;
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public Map<Integer, String> getRoleMap() {
		return roleMap;
	}
	public void setRoleMap(Map<Integer, String> roleMap) {
		this.roleMap = roleMap;
	}
	
	
}
