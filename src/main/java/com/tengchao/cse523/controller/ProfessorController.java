package com.tengchao.cse523.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfessorController {

	/**
	 * should contain courseInfo (TA and basic course info) and course records
	 * for all students
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/professor/{semester}/course", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, String>> getCourseRecords(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid) {
		return null;
	}

	/**
	 * create new course nickid, cname, seats, available seats should be
	 * included in the payload
	 * 
	 * @param pid
	 * @param payload
	 * @return
	 */
	@RequestMapping(value = "/professor/{semester}/createCourse", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> createCourse(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestBody String payload) {
		return null;
	}
}
