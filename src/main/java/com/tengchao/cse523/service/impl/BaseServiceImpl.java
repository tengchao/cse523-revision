package com.tengchao.cse523.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tengchao.cse523.dao.BaseDao;
import com.tengchao.cse523.dto.Course;
import com.tengchao.cse523.dto.CourseDashboard;
import com.tengchao.cse523.dto.CourseMore;
import com.tengchao.cse523.dto.Person;
import com.tengchao.cse523.dto.PersonCourseRelation;
import com.tengchao.cse523.exception.DataNotFoundException;
import com.tengchao.cse523.service.BaseService;

public class BaseServiceImpl implements BaseService {

	private BaseDao baseDao;

	private final static Logger LOGGER = LogManager
			.getLogger(BaseServiceImpl.class);

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public Person getPersonInfo(int pid) throws JsonProcessingException {
		Person person = baseDao.getPersonInfo(pid);
		if (null == person) {
			LOGGER.error("not find the person with pid:  " + pid);
			throw new DataNotFoundException("not find the person with pid:  "
					+ pid);
		}
		return person;
	}

	public Person getPersonInfo(int pid, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updatePersonInfo(Person person) {
		int pid = baseDao.updatePersonInfo(person);
		return pid;
	}

	public CourseDashboard getCourseDashboard(int pid, String semester) {
		List<PersonCourseRelation> relations = baseDao
				.getCoursesForPersonSemester(pid, semester);
		CourseDashboard dashboard = new CourseDashboard();
		dashboard.setSemester(semester);
		Map<Integer, CourseMore> courseMap = new HashMap<Integer, CourseMore>();
		for (PersonCourseRelation relation : relations){
			int cid = relation.getCid();
			CourseMore courseSpecialized = null;
			if (!courseMap.containsKey(cid)){
				courseSpecialized = new CourseMore();
				courseSpecialized.setCid(cid);
				courseSpecialized.setNickId(relation.getNickId());
				courseSpecialized.setCourseName(relation.getCourseName());
				courseSpecialized.setRole(relation.getRole());
			} else {
				courseSpecialized = courseMap.get(cid);
			}
			courseSpecialized.addSection(relation.getSection());
			courseMap.put(cid, courseSpecialized);
		}
		List<CourseMore> courses = new ArrayList<CourseMore>();
		courses.addAll(courseMap.values());
		if (LOGGER.isDebugEnabled()){
			for (CourseMore course : courses){
				LOGGER.debug("fetch course info for this person: " + course);
			}
		}
		dashboard.setCourses(courses);
		return dashboard;
	}
}
