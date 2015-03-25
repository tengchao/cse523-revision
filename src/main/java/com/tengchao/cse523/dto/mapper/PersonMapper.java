package com.tengchao.cse523.dto.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tengchao.cse523.dto.Person;

public class PersonMapper implements ResultSetExtractor<Person> {

	@Override
	public Person extractData(ResultSet rs) throws SQLException, DataAccessException {
		if (!rs.next()){
			return null;
		}
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
