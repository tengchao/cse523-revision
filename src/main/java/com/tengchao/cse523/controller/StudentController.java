package com.tengchao.cse523.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tengchao.cse523.service.StudentService;


@RestController
public class StudentController {
	
	private StudentService studentService;
	
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * request single course info. 
	 * should contain courseInfo (professor, TA and basic course info) and course records
	 * 
	 * @param semester
	 * @param pid
	 * @param cid
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/student/{semester}/course", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getCourse(
			@PathVariable(value = "semester") String semester,
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestParam(value = "section", required = true) int section) throws JsonProcessingException{
		Map<String, Object> response = studentService.getCourseDetails(semester, pid, cid, section);
		HttpStatus responseCode = HttpStatus.OK;
		return new ResponseEntity<Map<String,Object>>(response, responseCode);
	}
	
	/**
	 * set expectation
	 * @param payload
	 * @return
	 */
	@RequestMapping(value = "/student/setExpectation", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> setExpectation(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestParam(value = "expectation", required = true) String expectation,
			@RequestParam(value = "section", required = true) int section){
		return null;
	}
	
}
