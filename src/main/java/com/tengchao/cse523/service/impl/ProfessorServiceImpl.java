package com.tengchao.cse523.service.impl;

import java.sql.SQLException;
import java.util.Map;

import com.tengchao.cse523.dao.BaseDao;
import com.tengchao.cse523.dao.ProfessorDao;
import com.tengchao.cse523.dto.Course;
import com.tengchao.cse523.service.ProfessorService;

public class ProfessorServiceImpl implements ProfessorService {

	private ProfessorDao professorDao;
	
	public void setProfessorDao(ProfessorDao professorDao) {
		this.professorDao = professorDao;
	}
	
	@Override
	public int createCourse(Map<String, String> courseMap,
			String[] requiredParams) throws SQLException {
		int cid = professorDao.createCourse(courseMap, requiredParams);
		return cid;
	}
	
	@Override
	public Course getCourse(int cid, int pid) {
		Course course = null;
		course = professorDao.getCourseBasic(cid, pid);
		return course;
	}
	
	
}
