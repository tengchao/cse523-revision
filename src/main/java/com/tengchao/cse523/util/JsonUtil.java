package com.tengchao.cse523.util;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tengchao.cse523.exception.JsonException;

public class JsonUtil {
	
	public static Map toMap(String jsonStr){
		Map jsonMap = null;
		ObjectMapper objMapper = new ObjectMapper();
		try {
			jsonMap = objMapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			throw new JsonException("cannot convert to map from json string: "
					+ jsonStr);
		}
		return jsonMap;
	}

	public static Object toObject(String jsonStr, Class<?> classType) {
		Object jsonObj = null;
		ObjectMapper objMapper = new ObjectMapper();
		try {
			jsonObj = objMapper.readValue(jsonStr, classType);
		} catch (Exception e) {
			throw new JsonException("cannot convert to map from json string: "
					+ jsonStr);
		}
		return jsonObj;
	}
}
