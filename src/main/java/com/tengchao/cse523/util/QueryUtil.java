package com.tengchao.cse523.util;

import java.util.List;

public class QueryUtil {

	public static String getQuery(List<Object> params, String query){
		for (Object param : params){
			if (null == param){
				query = query.replaceFirst("\\?", "");
			}
			else {
				query = query.replaceFirst("\\?", param.toString());
			}
		}
		return query;
	}
}
