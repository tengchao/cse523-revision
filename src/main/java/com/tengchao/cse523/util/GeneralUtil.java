package com.tengchao.cse523.util;

import java.util.List;
import java.util.Map;

public class GeneralUtil {

	public static String containsAllKeys(Map map, String[] keys) {
		for (String key : keys) {
			if (!map.containsKey(key)) {
				return key;
			}
		}
		return null;
	}
	
	public static String[] append(String[] array, List<String> toAppend){
		int len = 0;
		if (array != null){
			len = array.length;
		}
		int size = toAppend.size();
		String[] newStrings = new String[len+size];
		for (int i=0; i<len; i++){
			newStrings[i] = array[i];
		}
		for (int i=0; i<size; i++){
			newStrings[len+i] = toAppend.get(i);
		}
		return newStrings;
	}

}
