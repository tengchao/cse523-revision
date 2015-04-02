package com.tengchao.cse523.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tengchao.cse523.dto.CourseDashboard;
import com.tengchao.cse523.dto.Person;

public interface BaseService {

	public Person getPersonInfo(int pid) throws JsonProcessingException;

	public Person getPersonInfo(int pid, String password);

	public int updatePersonInfo(Person p);

	public CourseDashboard getCourseDashboard(int pid, String semester);
}
