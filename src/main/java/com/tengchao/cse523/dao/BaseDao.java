package com.tengchao.cse523.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tengchao.cse523.dto.CourseDashboard;
import com.tengchao.cse523.dto.Person;
import com.tengchao.cse523.dto.PersonCourseRelation;
import com.tengchao.cse523.dto.mapper.PersonCourseRelationMapper;
import com.tengchao.cse523.dto.mapper.PersonRowMapper;
import com.tengchao.cse523.util.QueryUtil;

public class BaseDao {

	private JdbcTemplate jdbcTemplate;
	
	private final static Logger LOGGER = LogManager.getLogger(BaseDao.class);
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Person getPersonInfo(final int pid) throws JsonProcessingException{
		final StringBuilder sqlBuilder = new StringBuilder("select * from `people` where `pid` = ?");
		
		PreparedStatementSetter psmtSetter = new PreparedStatementSetter() {		
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, pid);
			}
		};
		if (LOGGER.isDebugEnabled()){
			List<Object> params = new ArrayList<Object>();
			params.add(pid);
			final String query = QueryUtil.getQuery(params, sqlBuilder.toString());
			LOGGER.debug(query);
		}
		List<Person> people = jdbcTemplate.query(sqlBuilder.toString(), psmtSetter, new PersonRowMapper());
		
		if (people.size() > 0){
			Person person = people.get(0);
			if (LOGGER.isDebugEnabled()){
				ObjectMapper mapper = new ObjectMapper();
				String jsonStr = mapper.writeValueAsString(person);
				LOGGER.debug("find person: " + jsonStr);
			}
			return person;
		}
		return null;
	}
	
	public int updatePersonInfo(final Person person){
		final StringBuilder sqlBuilder = new StringBuilder("UPDATE `people` SET `firstname`=?, "
				+ "`lastname`=?, `email`=?, `role`=?, `last_login_time`=?, `password`=? WHERE pid=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter(){
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
		if (LOGGER.isDebugEnabled()){
			List<Object> params = new ArrayList<Object>();
			params.add(person.getFirstName());
			params.add(person.getLastName());
			params.add(person.getEmail());
			params.add(person.getRole());
			params.add(person.getLastLoginTime());
			params.add(person.getPassword());
			params.add(person.getPid());
			final String query = QueryUtil.getQuery(params, sqlBuilder.toString());
			LOGGER.debug("update person: " + query);
		}
		int rows = jdbcTemplate.update(sqlBuilder.toString(), setter);
		if (0 == rows){
			LOGGER.error("update person info failure");
			return -1;
		}
		return person.getPid();	
	}
	
	public List<PersonCourseRelation> getCoursesForPersonSemester(final int pid, final String semester){
		final StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM `people_courses` where `pid`=? and `semester`=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, pid);
				ps.setString(2, semester);
			}
		};
		if (LOGGER.isDebugEnabled()){
			List<Object> params = new ArrayList<Object>();
			params.add(pid);
			params.add(semester);
			final String query = QueryUtil.getQuery(params, sqlBuilder.toString());
			LOGGER.debug("get course dashboard: " + query);
		}
		List<PersonCourseRelation> relations = jdbcTemplate.query(sqlBuilder.toString(), setter, new PersonCourseRelationMapper());
		return relations;
	}
	
	public List<Person> getFacultiesInCourse(int cid){
		return null;
	}
	
	public List<Person> getTAsInCourse(int cid){
		return null;
	}
}
