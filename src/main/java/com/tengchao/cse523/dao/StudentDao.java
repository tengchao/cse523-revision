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
import com.tengchao.cse523.dto.Record;
import com.tengchao.cse523.dto.mapper.row.RecordRowMapper;
import com.tengchao.cse523.util.QueryUtil;

public class StudentDao{

	private JdbcTemplate jdbcTemplate;
	private static final Logger LOGGER = LogManager.getLogger(StudentDao.class);
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Record getRecord(final int pid, final int cid, final int section, final String role) throws JsonProcessingException{
		final StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM `people_courses` where `pid`=? and `cid`=? and `section`=? and `role`=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, pid);
				ps.setInt(2, cid);
				ps.setInt(3, section);
				ps.setString(4, role);
			}
		};
		if (LOGGER.isDebugEnabled()){
			List<Object> params = new ArrayList<Object>();
			params.add(pid);
			params.add(cid);
			params.add(section);
			params.add(role);
			final String query = QueryUtil.getQuery(params, sqlBuilder.toString());
			LOGGER.debug("get student record: " + query);
		}
		List<Record> records = jdbcTemplate.query(sqlBuilder.toString(), setter, new RecordRowMapper());
		if (records != null && records.size() > 0){
			Record record = records.get(0);
			if (LOGGER.isDebugEnabled()){
				ObjectMapper mapper = new ObjectMapper();
				String jsonStr = mapper.writeValueAsString(record);
				LOGGER.debug("find record: " + jsonStr);
			}
			return record;
		}
		return null;
	}
	
}
