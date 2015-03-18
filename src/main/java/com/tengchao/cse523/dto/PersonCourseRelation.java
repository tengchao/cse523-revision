package com.tengchao.cse523.dto;

import java.io.Serializable;

public class PersonCourseRelation implements Serializable{

	private int pid;
	private String role;
	private int cid;
	private String nickId;
	private String cname;
	private String semester;
	private int section;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
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
	
	
}
