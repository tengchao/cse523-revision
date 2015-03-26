package com.tengchao.cse523.service;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface StudentService {

	public Map<String, Object> getCourseDetails(String semester, int pid, int cid, int section) throws JsonProcessingException;
	
	public int setExpectation(String semester, int pid, int cid, int section, String expectation);
}
