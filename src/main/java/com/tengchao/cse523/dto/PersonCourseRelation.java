package com.tengchao.cse523.dto;

import java.io.Serializable;

public class PersonCourseRelation implements Serializable{

	private int pid;
	private int cid;
	private int section;
	private String role;
	private String nickId;
	private String cname;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	
}
