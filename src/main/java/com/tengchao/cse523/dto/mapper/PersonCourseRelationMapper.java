package com.tengchao.cse523.dto.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tengchao.cse523.dto.PersonCourseRelation;

public class PersonCourseRelationMapper implements RowMapper<PersonCourseRelation>{

	@Override
	public PersonCourseRelation mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		PersonCourseRelation relation = new PersonCourseRelation();
		relation.setPid(rs.getInt("pid"));
		relation.setCid(rs.getInt("cid"));
		relation.setSection(rs.getInt("section"));
		relation.setRole(rs.getString("role"));
		relation.setNickId(rs.getString("nickId"));
		relation.setCname(rs.getString("cname"));
		return relation;
	}

}
