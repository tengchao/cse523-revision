package com.tengchao.cse523.service;

import java.sql.SQLException;
import java.util.Map;

import com.tengchao.cse523.dto.Course;

public interface ProfessorService {

	public int createCourse(Map<String, String> courseMap,
			String[] requiredParams) throws SQLException;
	
	public Course getCourse(int cid, int pid);
	
	public boolean deleteCourse(int cid, int pid) throws SQLException;
}
