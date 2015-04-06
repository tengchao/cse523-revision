package com.tengchao.cse523.util;

import java.util.Map;

public class GeneralUtil {

	public static boolean containsAllKeys(Map map, String[] keys){
		for (String key : keys){
			if (!map.containsKey(key)){
				return false;
			}
		}
		return true;
	}

}
