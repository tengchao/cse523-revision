package com.tengchao.cse523.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tengchao.cse523.dto.CourseDashboard;
import com.tengchao.cse523.dto.Person;
import com.tengchao.cse523.service.BaseService;

@RestController
public class CommonController {
	
	private BaseService baseService;
	
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
		Person person = baseService.getPersonInfo(pid);
		HttpStatus responseCode = HttpStatus.OK;
		if (null == person){
			responseCode = HttpStatus.BAD_REQUEST;
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
		return null;
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
		return null;
	}
	
}