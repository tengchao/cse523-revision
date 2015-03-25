package com.tengchao.cse523.dto.mapper.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tengchao.cse523.dto.Person;

public class PersonRowMapper implements RowMapper<Person>{

	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setPid(rs.getInt("pid"));
		person.setFirstName(rs.getString("firstname"));
		person.setLastName(rs.getString("lastname"));
		person.setRole(rs.getString("role"));
		person.setEmail(rs.getString("email"));
		person.setLastLoginTime(rs.getTimestamp("last_login_time"));
		person.setPassword(rs.getString("password"));
		return person;
	}

}
