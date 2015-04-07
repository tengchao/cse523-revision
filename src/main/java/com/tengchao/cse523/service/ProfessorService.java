package com.tengchao.cse523.service;

import java.sql.SQLException;
import java.util.Map;

public interface ProfessorService {

	public int createCourse(Map<String, String> courseMap,
			String[] requiredParams) throws SQLException;
}
