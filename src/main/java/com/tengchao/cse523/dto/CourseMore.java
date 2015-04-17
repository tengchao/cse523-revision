package com.tengchao.cse523.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseMore implements Serializable{
	
	private int cid;
	private String nickId;
	private String courseName;
	private String role;
	private List<Integer> sections;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Integer> getSections() {
		return sections;
	}
	public void setSections(List<Integer> sections) {
		this.sections = sections;
	}
	
	public void addSection(int section){
		if (null == sections){
			sections = new ArrayList<Integer>();
		}
		sections.add(section);
	}
	
}
