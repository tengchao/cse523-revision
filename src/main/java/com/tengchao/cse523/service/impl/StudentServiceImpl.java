package com.tengchao.cse523.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tengchao.cse523.dao.BaseDao;
import com.tengchao.cse523.dao.StudentDao;
import com.tengchao.cse523.dto.Course;
import com.tengchao.cse523.dto.Record;
import com.tengchao.cse523.exception.DataNotFoundException;
import com.tengchao.cse523.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private BaseDao baseDao;
	private StudentDao studentDao;

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Map<String, Object> getCourseDetails(String semester, int pid,
			int cid, int section) throws JsonProcessingException {
		Map<String, Object> details = new HashMap<String, Object>();
		Course course = baseDao.getCourseBasic(semester, cid);
		if (course == null) {
			throw new DataNotFoundException("course not found with cid = "
					+ cid + ", semester = " + semester);
		}
		Record record = studentDao.getRecord(pid, cid, section, "s");
		details.put("course", course);
		details.put("record", record);
		return details;
	}

	@Override
	public int setExpectation(String semester, int pid, int cid, int section,
			String expectation) {
		int personId = studentDao.setException(pid, cid, section, semester,
				expectation, "s");
		return personId;
	}

}
