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
import com.tengchao.cse523.dto.mapper.RecordMapper;
import com.tengchao.cse523.util.QueryUtil;

public class StudentDao {

	private JdbcTemplate jdbcTemplate;
	private static final Logger LOGGER = LogManager.getLogger(StudentDao.class);

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Record getRecord(final int pid, final int cid, final int section,
			final String role) throws JsonProcessingException {
		final StringBuilder sqlBuilder = new StringBuilder(
				"SELECT * FROM `people_courses` where `pid`=? and `cid`=? and `section`=? and `role`=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, pid);
				ps.setInt(2, cid);
				ps.setInt(3, section);
				ps.setString(4, role);
			}
		};
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			params.add(pid);
			params.add(cid);
			params.add(section);
			params.add(role);
			final String query = QueryUtil.getQuery(params,
					sqlBuilder.toString());
			LOGGER.debug("get student record: " + query);
		}
		Record record = jdbcTemplate.query(sqlBuilder.toString(), setter,
				new RecordMapper());
		if (LOGGER.isDebugEnabled()) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(record);
			LOGGER.debug("find record: " + jsonStr);
		}
		return record;
	}

	public int setException(final int pid, final int cid, final int section,
			final String semester, final String exception, final String role) {
		final StringBuilder sqlBuilder = new StringBuilder(
				"UPDATE `people_courses` SET `expectation`=? "
						+ "WHERE `pid`=? AND `cid`=? AND `section`=? AND `semester`=? AND `role`=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, exception);
				ps.setInt(2, pid);
				ps.setInt(3, cid);
				ps.setInt(4, section);
				ps.setString(5, semester);
				ps.setString(6, role);
			}
		};
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			params.add(exception);
			params.add(pid);
			params.add(cid);
			params.add(section);
			params.add(semester);
			params.add(role);
			final String query = QueryUtil.getQuery(params,
					sqlBuilder.toString());
			LOGGER.debug("set expectation: " + query);
		}
		int row = jdbcTemplate.update(sqlBuilder.toString(), setter);
		if (row == 0) {
			LOGGER.error("set exception fail");
			return -1;
		}
		return pid;
	}

}
