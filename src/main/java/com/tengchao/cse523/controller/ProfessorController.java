package com.tengchao.cse523.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.StringUtils;
import com.tengchao.cse523.dto.Course;
import com.tengchao.cse523.exception.BadRequestException;
import com.tengchao.cse523.service.ProfessorService;
import com.tengchao.cse523.util.GeneralUtil;
import com.tengchao.cse523.util.JsonUtil;

@RestController
public class ProfessorController {

	private static final Logger LOGGER = LogManager
			.getLogger(ProfessorController.class);
	private ProfessorService professorService;

	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}

	/*************************************************************************
	 * course dashboard page
	 *************************************************************************/

	/**
	 * create new course
	 * 
	 * @param pid
	 * @param payload
	 *            {nickId, cname, gradeRange, percentageFlag,
	 *            categoryPercentage}
	 * @return {cid}
	 * @throws SQLException
	 */
	@RequestMapping(value = "/professor/createCourse", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> createCourse(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestBody String requestPayload) throws SQLException {
		if (StringUtils.isNullOrEmpty(requestPayload)) {
			LOGGER.error("request payload is null or empty");
			throw new BadRequestException("request payload is null or empty");
		}
		Map<String, Object> jsonMap = JsonUtil.toMap(requestPayload);
		Map<String, Integer> response = new HashMap<String, Integer>();
		String[] requiredParams = new String[] { "nickId", "courseName",
				"semester", "professorId", "professorName", "gradeRange",
				"percentageFlag", "categories" };
		String missingKey = GeneralUtil
				.containsAllKeys(jsonMap, requiredParams);
		if (missingKey != null) {
			LOGGER.error("Parameter " + missingKey
					+ " is missing in the request payload: " + requestPayload);
			throw new BadRequestException(
					"some parameter is missing in the request payload: "
							+ requestPayload);
		}
		HttpStatus responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
		int cid = professorService.createCourse(jsonMap, requiredParams);
		response.put("cid", cid);
		responseCode = HttpStatus.OK;
		return new ResponseEntity<Map<String, Integer>>(response, responseCode);
	}

	/**
	 * delete course
	 * 
	 * @param pid
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/professor/deleteCourse", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> deleteCourse(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid)
			throws SQLException {
//		HttpStatus responseCode = HttpStatus.OK;
//		Map<String, Integer> response = new HashMap<String, Integer>();
//		professorService.deleteCourse(cid, pid);
//		response.put("cid", cid);
//		return new ResponseEntity<Map<String, Integer>>(response, responseCode);
		return null;
	}

	/**
	 * create new section: section should be included in the payload
	 * 
	 * @param pid
	 * @param cid
	 * @param requestPayload
	 *            {section}
	 * @return {cid, section}
	 */
	@RequestMapping(value = "/professor/createSection", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> createSection(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestBody String requestPayload) {
		return null;
	}

	/**
	 * delete section
	 * 
	 * @param pid
	 * @param cid
	 * @param section
	 * @return
	 */
	@RequestMapping(value = "/professor/deleteSection", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deleteSection(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestParam(value = "section", required = true) int section) {
		return null;
	}

	/*************************************************************************
	 * course details page
	 *************************************************************************/

	/**
	 * get course info like gradeRange, category percentages
	 * 
	 * @param pid
	 * @param cid
	 * @return {cid, nickId, cname, sections, gradeRange, percentageFlag,
	 *         categoryPercentage}
	 */
	@RequestMapping(value = "/professor/viewCourse", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Course> getCourseDetails(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid) {
		Course course = null;
		HttpStatus responseCode = HttpStatus.OK;
		course = professorService.getCourse(cid, pid);
		return new ResponseEntity<Course>(course, responseCode);
	}

	/**
	 * update course details
	 * 
	 * @param pid
	 * @param cid
	 * @param requestPayload
	 * @return
	 */
	@RequestMapping(value = "/professor/updateCourse", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateCourseDetails(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestBody String requestPayload) {
		return null;
	}

	/**
	 * modify TA capabilities and responsibilities
	 * 
	 * @param pid
	 * @param cid
	 * @param requestPayload
	 * @return
	 */
	@RequestMapping(value = "/professor/updateTAsResponsibilities", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateTAsInCourse(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestBody String requestPayload) {
		return null;
	}

	/**
	 * get TA info for this course
	 * 
	 * @param pid
	 * @param cid
	 * @param requestPayload
	 * @return
	 */
	@RequestMapping(value = "/professor/viewTAsResponsibilities", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getTAsInCourse(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestBody String requestPayload) {
		return null;
	}

	/**
	 * add TA in the course
	 * 
	 * @param pid
	 * @param cid
	 * @param requestPayload
	 * @return
	 */
	@RequestMapping(value = "/professor/addTAForCourse", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addTAInCourse(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestBody String requestPayload) {
		return null;
	}

	/**
	 * delete TA in course
	 * 
	 * @param pid
	 * @param cid
	 * @param requestPayload
	 * @return
	 */
	@RequestMapping(value = "/professor/deleteTAForCourse", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deleteTAInCourse(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestBody String requestPayload) {
		return null;
	}

	/*************************************************************************
	 * section page
	 *************************************************************************/

	/**
	 * should contain courseInfo (TA and basic course info) and course records
	 * for all students
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/professor/viewSection", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, String>> getSectionRecords(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestParam(value = "section", required = true) int section) {
		return null;
	}

	/*************************************************************************
	 * update record page
	 *************************************************************************/

	/*************************************************************************
	 * preference page
	 *************************************************************************/

}
