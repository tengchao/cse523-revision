package com.tengchao.cse523.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tengchao.cse523.dto.Course;
import com.tengchao.cse523.dto.Person;
import com.tengchao.cse523.dto.PersonCourseRelation;
import com.tengchao.cse523.dto.mapper.CourseMapper;
import com.tengchao.cse523.dto.mapper.PersonMapper;
import com.tengchao.cse523.dto.mapper.row.PersonCourseRelationRowMapper;
import com.tengchao.cse523.util.QueryUtil;

public class BaseDao {

	private JdbcTemplate jdbcTemplate;

	private final static Logger LOGGER = LogManager.getLogger(BaseDao.class);

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Person getPersonInfo(final int pid) throws JsonProcessingException {
		final String sqlString = new String(
				"select * from `people` where `pid` = ?");

		PreparedStatementSetter psmtSetter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, pid);
			}
		};
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			params.add(pid);
			final String query = QueryUtil.getQuery(params, sqlString);
			LOGGER.debug(query);
		}
		Person person = jdbcTemplate.query(sqlString, psmtSetter,
				new PersonMapper());
		if (LOGGER.isDebugEnabled()) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(person);
			LOGGER.debug("find person: " + jsonStr);
		}
		return person;
	}

	public int updatePersonInfo(final Person person) {
		final String sqlString = new String(
				"UPDATE `people` SET `firstname`=?, "
						+ "`lastname`=?, `email`=?, `role`=?, `last_login_time`=?, `password`=? WHERE `pid`=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, person.getFirstName());
				ps.setString(2, person.getLastName());
				ps.setString(3, person.getEmail());
				ps.setString(4, person.getRole());
				ps.setTimestamp(5, person.getLastLoginTime());
				ps.setString(6, person.getPassword());
				ps.setInt(7, person.getPid());
			}
		};
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			params.add(person.getFirstName());
			params.add(person.getLastName());
			params.add(person.getEmail());
			params.add(person.getRole());
			params.add(person.getLastLoginTime());
			params.add(person.getPassword());
			params.add(person.getPid());
			final String query = QueryUtil.getQuery(params, sqlString);
			LOGGER.debug("update person: " + query);
		}
		int rows = jdbcTemplate.update(sqlString, setter);
		if (0 == rows) {
			LOGGER.error("update person info failure");
			return -1;
		}
		return person.getPid();
	}

	public List<PersonCourseRelation> getCoursesForPersonSemester(
			final int pid, final String semester) {
		final String sqlString = new String(
				"SELECT * FROM `people_courses` where `pid`=? and `semester`=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, pid);
				ps.setString(2, semester);
			}
		};
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			params.add(pid);
			params.add(semester);
			final String query = QueryUtil.getQuery(params, sqlString);
			LOGGER.debug("get course dashboard: " + query);
		}
		List<PersonCourseRelation> relations = jdbcTemplate.query(sqlString,
				setter, new PersonCourseRelationRowMapper());
		return relations;
	}

}
