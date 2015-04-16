package com.tengchao.cse523.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;
import com.tengchao.cse523.dto.Course;
import com.tengchao.cse523.dto.mapper.CourseMapper;
import com.tengchao.cse523.util.QueryUtil;

public class ProfessorDao {

	private static final Logger LOGGER = LogManager
			.getLogger(ProfessorDao.class);
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int createCourse(final Map<String, Object> courseMap,
			final String[] requiredParams) throws SQLException {
		final String sqlString = QueryUtil.constructInsertQuery("courses",
				requiredParams);
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			for (int i = 0; i < requiredParams.length; i++) {
				String key = requiredParams[i];
				Object value = courseMap.get(key);
				params.add(value.toString());
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
						Statement.RETURN_GENERATED_KEYS);
				for (int i = 0; i < requiredParams.length; i++) {
					String key = requiredParams[i];
					Object value = courseMap.get(key);
					pst.setObject(i + 1, value);
				}
				return pst;
			}
		}, keyHolder);
		if (rows != 1) {
			throw new SQLException("fail to insert new course: " + rows);
		}
		return keyHolder.getKey().intValue();
	}

	public Course getCourseBasic(final int cid, final int pid) {
		final StringBuilder sqlBuilder = new StringBuilder(
				"SELECT * FROM `courses` where `cid`=? and `professorId`=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, cid);
				ps.setInt(2, pid);
			}
		};
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			params.add(cid);
			params.add(pid);
			final String query = QueryUtil.getQuery(params,
					sqlBuilder.toString());
			LOGGER.debug(query);
		}
		Course course = jdbcTemplate.query(sqlBuilder.toString(), setter,
				new CourseMapper());
		return course;
	}

	public int deleteCourseInCourseTable(final int pid, final int cid) {
		final String sqlString = new String(
				"delete * from `courses` where `cid`=? and `professorId`=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, cid);
				ps.setInt(2, pid);
			}
		};
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			params.add(cid);
			params.add(pid);
			final String query = QueryUtil.getQuery(params, sqlString);
			LOGGER.debug("delete course in table courses: " + query);
		}
		int rows = jdbcTemplate.update(sqlString, setter);
		return rows;
	}

	public int deleteCourseInRelationTable(final int cid) {
		final String sqlString = new String(
				"delete * from `people_courses` where `cid`=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, cid);
			}
		};
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			params.add(cid);
			final String query = QueryUtil.getQuery(params, sqlString);
			LOGGER.debug("delete course in table people_courses: " + query);
		}
		int rows = jdbcTemplate.update(sqlString, setter);
		return rows;
	}

	public List<Integer> sectionListForCourse(final int pid, final int cid) {
		final String sqlString = new String(
				"select `section` from `people_courses` where `pid`=? and `cid`=?;");
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, pid);
				ps.setInt(2, cid);
			}
		};
		if (LOGGER.isDebugEnabled()){
			List<Object> params = new ArrayList<Object>();
			params.add(pid);
			params.add(cid);
			final String query = QueryUtil.getQuery(params, sqlString);
			LOGGER.debug("get course section list: " + query);
		}
		List<Integer> sections = null;
		sections = jdbcTemplate.query(sqlString, setter, new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("section");
			}
		});
		return sections;
	}

	public int createSection(final Map<String, Object> infoMap,
			final String[] requiredParams) throws SQLException {
		final String sqlString = QueryUtil.constructInsertQuery(
				"people_courses", requiredParams);
		PreparedStatementSetter setter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				for (int i = 0; i < requiredParams.length; i++) {
					String key = requiredParams[i];
					Object value = infoMap.get(key);
					ps.setObject(i + 1, value);
				}
			}
		};
		if (LOGGER.isDebugEnabled()) {
			List<Object> params = new ArrayList<Object>();
			for (int i = 0; i < requiredParams.length; i++) {
				String key = requiredParams[i];
				Object value = infoMap.get(key);
				params.add(value.toString());
			}
			final String query = QueryUtil.getQuery(params, sqlString);
			LOGGER.debug("create section: " + query);
		}
		int rows = jdbcTemplate.update(sqlString, setter);
		if (rows != 1){
			throw new SQLException("fail to insert new course: " + rows);
		}
		return rows;
	}

}
