package com.tengchao.cse523.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tengchao.cse523.service.ProfessorService;

@RestController
public class ProfessorController {
	
	private ProfessorService professorService;
	
	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	/*************************************************************************
	 * course dashboard page
	 *************************************************************************/

	/**
	 * create new course: nickid, cname, seats, available seats should be
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
			@RequestBody String requestPayload) {
		return null;
	}
	
	/**
	 * create new section: section should be included in the payload
	 * 
	 * @param pid
	 * @param cid
	 * @param requestPayload
	 * @return
	 */
	@RequestMapping(value = "/professor/{semester}/createSection", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> createSection(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestBody String requestPayload) {
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
	 * @return
	 */
	@RequestMapping(value = "/professor/{semester}/course", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getCourseDetails(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid) {
		return null;
	}
	
	/**
	 * update course details
	 * 
	 * @param pid
	 * @param cid
	 * @param requestPayload
	 * @return
	 */
	@RequestMapping(value = "/professor/{semester}/course", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateCourseDetails(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestBody String requestPayload) {
		return null;
	}
	
	/**
	 * modify TA capabilities and responsibilities: payload include commands and
	 * whole TA info
	 * 
	 * @param pid
	 * @param cid
	 * @param requestPayload
	 * @return
	 */
	@RequestMapping(value = "/professor/{semester}/setTAsResponsibilities", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> actionTAsInCourse(
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
	@RequestMapping(value = "/professor/{semester}/getTAsResponsibilities", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getTAsInCourse(
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestBody String requestPayload){
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
	@RequestMapping(value = "/professor/{semester}/section", method = RequestMethod.POST)
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
