package com.tengchao.cse523.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CourseDashboard implements Serializable {

	private String semester;
	private List<CourseMore> courses;

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public List<CourseMore> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseMore> courses) {
		this.courses = courses;
	}	
}
