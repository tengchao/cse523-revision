package com.tengchao.cse523.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.tengchao.cse523.util.GeneralUtil;
import com.tengchao.cse523.util.QueryUtil;

public class ProfessorDao {

	private static final Logger LOGGER = LogManager
			.getLogger(ProfessorDao.class);
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int createCourse(final Map<String, String> courseMap,
			final String[] requiredParams) throws SQLException {
		final String sqlString = QueryUtil.constructInsertQuery("courses",
				requiredParams);
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			for (int i = 0; i < requiredParams.length; i++) {
				String key = requiredParams[i];
				String value = courseMap.get(key);
				params.add(value);
			}
			final String query = QueryUtil.getQuery(params, sqlString);
			LOGGER.debug("create course: " + query);
		}
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int rows = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pst = con.prepareStatement(sqlString,
						new String[] { "cid" });
				for (int i = 0; i < requiredParams.length; i++) {
					String key = requiredParams[i];
					String value = courseMap.get(key);
					pst.setString(i + 1, value);
				}
				return pst;
			}
		}, keyHolder);
		if (0 == rows){
			throw new SQLException("fail to insert new course");
		}
		return (int) keyHolder.getKeys().get("cid");
	}

}
