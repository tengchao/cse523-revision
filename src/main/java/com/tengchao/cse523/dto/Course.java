package com.tengchao.cse523.dto;

import java.io.Serializable;

public class Course implements Serializable{
	
	private int courseId;
	private String nickId;	// such as CSE114
	private String courseName;
	private String semester;
	private int professorId;
	private String professorName;
	private String gradeRange;
	private String percentageFlag;
	private String categories;
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	public String getGradeRange() {
		return gradeRange;
	}
	public void setGradeRange(String gradeRange) {
		this.gradeRange = gradeRange;
	}
	public String getPercentageFlag() {
		return percentageFlag;
	}
	public void setPercentageFlag(String percentageFlag) {
		this.percentageFlag = percentageFlag;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	
}
