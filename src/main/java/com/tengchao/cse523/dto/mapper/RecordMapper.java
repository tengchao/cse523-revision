package com.tengchao.cse523.dto.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tengchao.cse523.dto.Record;

public class RecordMapper implements ResultSetExtractor<Record> {

	@Override
	public Record extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			Record record = new Record();
			record.setPid(rs.getInt("pid"));
			record.setName(rs.getString("name"));
			record.setData(rs.getString("data"));
			record.setExpectation(rs.getString("expectation"));
			record.setScore(rs.getDouble("score"));
			record.setGrade(rs.getString("grade"));
			return record;
		}
		return null;
	}

}
