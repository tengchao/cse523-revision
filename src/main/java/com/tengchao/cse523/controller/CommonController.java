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
import com.tengchao.cse523.service.BaseService;

@RestController
public class CommonController {
	
	private BaseService baseService;
	
	private final static Logger LOGGER = LogManager.getLogger(CommonController.class);
	
	public void setBaseService(BaseService baseService){
		this.baseService = baseService;
	}

	/**
	 * get person info
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/personInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Person> getPersonInfo(
			@RequestParam(value = "pid", required = true) int pid){
		
		HttpStatus responseCode = HttpStatus.OK;
		Person person = null;
		try {
			person = baseService.getPersonInfo(pid);
			if (null == person){
				responseCode = HttpStatus.NOT_FOUND;
				LOGGER.error("not find the person with pid:  " + pid);
			}
		} catch (JsonProcessingException e) {
			responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error("JSON exception when getting person info with pid:" + pid);
		}
		return new ResponseEntity<Person>(person, responseCode);
	}
	
	/**
	 * update person info
	 * 
	 * @param pid
	 * @param requestPayload
	 * @return {pid : <id>}
	 */
	@RequestMapping(value = "/personInfo", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> updatePersonInfo(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestBody String requestPayload){
		Map<String, Integer> response = new HashMap<String, Integer>();
		HttpStatus responseCode = HttpStatus.OK;
		if (StringUtils.isNullOrEmpty(requestPayload)){
			LOGGER.error("request payload is null or empty");
			responseCode = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String, Integer>>(response, responseCode);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		Person newPerson = null;
		try {
			newPerson = mapper.readValue(requestPayload, Person.class);
			int personId = baseService.updatePersonInfo(newPerson);
			
			if (personId < 0){
				responseCode = HttpStatus.BAD_REQUEST;
				LOGGER.error("cannot find the appropriate person to update with payload: " + requestPayload);
				return new ResponseEntity<Map<String,Integer>>(response, responseCode);
			}
			
			response.put("pid", personId);
			
		} catch (JsonParseException e) {
			responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error("JsonParseException when updating person info with payload: " + requestPayload);
		} catch (JsonMappingException e) {
			responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error("JsonMappingException when updating person info with payload: " + requestPayload);
		} catch (IOException e) {
			responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error("IOException when updating person info with payload: " + requestPayload);
		}
		
		return new ResponseEntity<Map<String, Integer>>(response, responseCode);
	}
	
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
			@RequestParam(value = "pid", required = true) int pid){
		CourseDashboard dashboard = null;
		HttpStatus responseCode = HttpStatus.ACCEPTED;
		dashboard = baseService.getCourseDashboard(pid, semester);
		if (0 == dashboard.getCourses().size()){
			responseCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<CourseDashboard>(dashboard, responseCode);
	}
	
}
