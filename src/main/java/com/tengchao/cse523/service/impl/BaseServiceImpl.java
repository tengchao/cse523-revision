package com.tengchao.cse523.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tengchao.cse523.dao.BaseDao;
import com.tengchao.cse523.dto.Person;
import com.tengchao.cse523.service.BaseService;

public class BaseServiceImpl implements BaseService {
	
	private BaseDao baseDao;
	
	private final static Logger LOGGER = LogManager.getLogger(BaseServiceImpl.class);
	
	public void setBaseDao(BaseDao baseDao){
		this.baseDao = baseDao;
	}

	public Person getPersonInfo(int pid) {
		return baseDao.getPersonInfo(pid);
	}

	public Person getPersonInfo(int pid, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updatePersonInfo(Person p) {
		// TODO Auto-generated method stub
		return 0;
	}

}
