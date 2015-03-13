package com.tengchao.cse523.service;

import com.tengchao.cse523.dto.Person;

public interface BaseService {

	public Person getPersonInfo(int pid);
	
	public Person getPersonInfo(int pid, String password);
	
	public int updatePersonInfo(Person p);
}
