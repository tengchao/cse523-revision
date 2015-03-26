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
import com.tengchao.cse523.exception.BadRequestException;
import com.tengchao.cse523.service.StudentService;

@RestController
public class StudentController {

	private static final Logger LOGGER = LogManager
			.getLogger(StudentController.class);

	private StudentService studentService;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * request single course info. should contain courseInfo (professor, TA and
	 * basic course info) and course records
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
			@RequestParam(value = "section", required = true) int section)
			throws JsonProcessingException {
		Map<String, Object> response = studentService.getCourseDetails(
				semester, pid, cid, section);
		HttpStatus responseCode = HttpStatus.OK;
		return new ResponseEntity<Map<String, Object>>(response, responseCode);
	}

	/**
	 * set expectation
	 * 
	 * @param payload
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value = "/student/{semester}/setExpectation", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> setExpectation(
			@PathVariable(value = "semester") String semester,
			@RequestParam(value = "pid", required = true) int pid,
			@RequestParam(value = "cid", required = true) int cid,
			@RequestParam(value = "section", required = true) int section,
			@RequestBody String requestPayload) throws JsonParseException,
			JsonMappingException, IOException {
		Map<String, Integer> response = new HashMap<String, Integer>();
		HttpStatus responseCode = HttpStatus.OK;
		if (StringUtils.isNullOrEmpty(requestPayload)) {
			LOGGER.error("request payload is null or empty");
			throw new BadRequestException("request payload is null or empty");
		}
		ObjectMapper objMapper = new ObjectMapper();
		Map<String, String> temp = null;
		temp = objMapper.readValue(requestPayload, Map.class);
		if (!temp.containsKey("expectation")) {
			LOGGER.error("parameter expectation is missing");
			throw new BadRequestException(
					"parameter is missing in the request payload: "
							+ requestPayload);
		}
		String expectation = temp.get("expectation");
		int personId = studentService.setExpectation(semester, pid, cid,
				section, expectation);
		if (personId < 0) {
			LOGGER.error("cannot set expectation with pid=" + pid + ", cid="
					+ cid + ", section=" + section + ", expectation="
					+ expectation);
			throw new BadRequestException("bad request");
		}
		response.put("pid", personId);
		return new ResponseEntity<Map<String, Integer>>(response, responseCode);
	}

}
