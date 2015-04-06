package com.tengchao.cse523.util;

import java.util.List;

public class QueryUtil {

	public static String getQuery(List<Object> params, String query) {
		for (Object param : params) {
			if (null == param) {
				query = query.replaceFirst("\\?", "");
			} else {
				query = query.replaceFirst("\\?", param.toString());
			}
		}
		return query;
	}

	public static String constructInsertQuery(String table, String[] columns) {
		String[] temp = arrayToStr(columns);
		StringBuilder sb = new StringBuilder("insert into ");
		sb.append("`").append(table).append("` ");
		sb.append("(").append(temp[0]).append(") ");
		sb.append("values ");
		sb.append("(").append(temp[1]).append(");");
		return sb.toString();
	}

	private static String[] arrayToStr(String[] array) {
		String[] result = new String[2];
		if (null == array || array.length == 0) {
			return result;
		}
		StringBuilder keys = new StringBuilder();
		StringBuilder values = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			keys.append("`").append(array[i]).append("`, ");
			values.append("?, ");
		}
		if (keys.length() > 0) {
			keys.setLength(keys.length() - 2);
			values.setLength(values.length() - 2);

			result[0] = keys.toString();
			result[1] = keys.toString();
		}
		return result;
	}
}
