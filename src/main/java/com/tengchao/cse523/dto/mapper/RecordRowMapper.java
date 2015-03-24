package com.tengchao.cse523.dto.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tengchao.cse523.dto.Record;

public class RecordRowMapper implements RowMapper<Record>{

	@Override
	public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
		Record record = new Record();
		record.setPid(rs.getInt("pid"));
		record.setName(rs.getString("name"));
		record.setData(rs.getString("data"));
		record.setExpectation(rs.getString("expectation"));
		record.setScore(rs.getDouble("score"));
		record.setGrade(rs.getString("grade"));
		return null;
	}

}
