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

	private StudentDao studentDao;

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public Map<String, Object> getCourseDetails(int pid, int cid, int section)
			throws JsonProcessingException {
		Map<String, Object> details = new HashMap<String, Object>();
		Course course = studentDao.getCourseBasic(cid);
		if (course == null) {
			throw new DataNotFoundException("course not found with cid: " + cid);
		}
		Record record = studentDao.getRecord(pid, cid, section, "s");
		details.put("course", course);
		details.put("record", record);
		return details;
	}

	@Override
	public int setExpectation(int pid, int cid, int section, String expectation) {
		int personId = studentDao.setException(pid, cid, section, expectation,
				"s");
		return personId;
	}

}
