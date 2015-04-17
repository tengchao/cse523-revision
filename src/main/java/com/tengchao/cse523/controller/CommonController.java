package com.tengchao.cse523.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.StringUtils;
import com.tengchao.cse523.dto.CourseDashboard;
import com.tengchao.cse523.dto.Person;
import com.tengchao.cse523.exception.BadRequestException;
import com.tengchao.cse523.exception.DataNotFoundException;
import com.tengchao.cse523.service.BaseService;
import com.tengchao.cse523.util.JsonUtil;

@RestController
public class CommonController {

	private BaseService baseService;

	private final static Logger LOGGER = LogManager
			.getLogger(CommonController.class);

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	/*************************************************************************
	 * person info page
	 *************************************************************************/

	/**
	 * get person info
	 * 
	 * @param pid
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/personInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Person> getPersonInfo(
			@RequestParam(value = "pid", required = true) int pid)
			throws JsonProcessingException {

		HttpStatus responseCode = HttpStatus.OK;
		Person person = null;
		person = baseService.getPersonInfo(pid);
		return new ResponseEntity<Person>(person, responseCode);
	}

	/**
	 * update person info
	 * 
	 * @param pid
	 * @param requestPayload
	 * @return {pid : <id>}
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value = "/personInfo", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> updatePersonInfo(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestBody String requestPayload) {
		Map<String, Integer> response = new HashMap<String, Integer>();
		HttpStatus responseCode = HttpStatus.OK;
		if (StringUtils.isNullOrEmpty(requestPayload)) {
			LOGGER.error("request payload is null or empty");
			throw new BadRequestException("request payload is null or empty");
		}

		Person newPerson = null;
		newPerson = (Person) JsonUtil.toObject(requestPayload, Person.class);
		int personId = baseService.updatePersonInfo(newPerson);

		if (personId < 0) {
			LOGGER.error("cannot find the appropriate person to update with payload: "
					+ requestPayload);
			throw new BadRequestException("bad request with payload: "
					+ requestPayload);
		}

		response.put("pid", personId);

		return new ResponseEntity<Map<String, Integer>>(response, responseCode);
	}

	/*************************************************************************
	 * course dashboard page
	 *************************************************************************/

	/**
	 * request the dash board
	 * 
	 * @param semester
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/courses/{semester}/dashboard", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CourseDashboard> getSemesterCourses(
			@PathVariable("semester") String semester,
			@RequestParam(value = "pid", required = true) int pid) {
		CourseDashboard dashboard = null;
		HttpStatus responseCode = HttpStatus.ACCEPTED;
		dashboard = baseService.getCourseDashboard(pid, semester);
		if (0 == dashboard.getCourses().size()) {
			throw new DataNotFoundException(
					"cannot find any courses in semester: " + semester);
		}
		return new ResponseEntity<CourseDashboard>(dashboard, responseCode);
	}
	
}
