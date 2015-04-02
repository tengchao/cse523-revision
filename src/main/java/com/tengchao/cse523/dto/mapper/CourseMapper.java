package com.tengchao.cse523.dto.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tengchao.cse523.dto.Course;

public class CourseMapper implements ResultSetExtractor<Course> {
	@Override
	public Course extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			Course course = new Course();
			course.setCourseId(rs.getInt("cid"));
			course.setNickId(rs.getString("nickId"));
			course.setCourseName(rs.getString("cname"));
			course.setSemester(rs.getString("semester"));
			course.setProfessorId(rs.getInt("professorId"));
			course.setProfessorName(rs.getString("professorName"));
			course.setGradeRange(rs.getString("gradeRange"));
			course.setPercentageFlag(rs.getString("percentageFlag"));
			course.setCategories(rs.getString("categories"));
			return course;
		}
		return null;
	}

}
