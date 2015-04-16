package com.tengchao.cse523.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.tengchao.cse523.dao.BaseDao;
import com.tengchao.cse523.dao.ProfessorDao;
import com.tengchao.cse523.dto.Course;
import com.tengchao.cse523.service.ProfessorService;
import com.tengchao.cse523.util.GeneralUtil;

public class ProfessorServiceImpl implements ProfessorService {

	private ProfessorDao professorDao;

	public void setProfessorDao(ProfessorDao professorDao) {
		this.professorDao = professorDao;
	}

	@Override
	public int createCourse(Map<String, Object> courseMap,
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

	@Override
	@Transactional
	public boolean deleteCourse(int cid, int pid) throws SQLException {
		int rows = professorDao.deleteCourseInCourseTable(pid, cid);
		if (rows != 1) {
			throw new SQLException("delete course with affected rows: " + rows);
		}
		professorDao.deleteCourseInRelationTable(cid);
		return true;
	}

	@Override
	public int createSection(Map<String, Object> infoMap,
			String[] requiredParams, int cid, int pid) throws SQLException {
		
		List<Integer> sections = professorDao.sectionListForCourse(pid, cid);
		int nextSection = 1;
		if (sections != null){
			nextSection = findNextMissingSection(sections);
		}
		
		List<String> toAppend = new ArrayList<String>();
		
		String cidKey = "cid";
		String dataKey = "data";
		String pidKey = "pid";
		String sectionKey = "section";
		
		infoMap.put(pidKey, pid);
		infoMap.put(cidKey, cid);
		infoMap.put(dataKey, "1");
		infoMap.put(sectionKey, nextSection);
		
		toAppend.add(pidKey);
		toAppend.add(cidKey);
		toAppend.add(dataKey);
		toAppend.add(sectionKey);
		String[] params = GeneralUtil.append(requiredParams, toAppend);
		
		professorDao.createSection(infoMap, params);
		return nextSection;
	}
	
	private int findNextMissingSection(List<Integer> sections){
		Set<Integer> sectionSet = new HashSet<Integer>();
		for (int i : sections){
			sectionSet.add(i);
		}
		int nextSection = 1;
		while (sectionSet.contains(nextSection)){
			nextSection++;
		}
		return nextSection;
	}

}
