package com.tengchao.cse523.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tengchao.cse523.dto.CourseDashboard;
import com.tengchao.cse523.dto.Person;
import com.tengchao.cse523.dto.mapper.PersonRowMapper;

public class BaseDao {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Person getPersonInfo(final int pid){
		final StringBuilder sqlBuilder = new StringBuilder("select * from people where pid = ?");
		
		PreparedStatementSetter psmtSetter = new PreparedStatementSetter() {		
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, pid);
			}
		};
		List<Person> people = jdbcTemplate.query(sqlBuilder.toString(), psmtSetter, new PersonRowMapper());
		if (people.size() > 0){
			return people.get(0);
		}
		return null;
	}
	
	public int updatePersonInfo(Person person){
		return 0;	
	}
	
	public CourseDashboard getDashboard(int pid, String semester){
		return null;
	}
	
	public List<Person> getFacultiesInCourse(int cid){
		return null;
	}
	
	public List<Person> getTAsInCourse(int cid){
		return null;
	}
}
