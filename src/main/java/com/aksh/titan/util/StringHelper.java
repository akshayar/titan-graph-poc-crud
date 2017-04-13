package com.aksh.titan.util;

import org.apache.commons.lang.StringUtils;

public class StringHelper {

	public static Object trimIfString(String obj) {
		Object result = obj;
		if (obj != null) {
			result = ((String) obj).trim();
		} else {
			result = "";
		}
		return result;
	}
	
	public static Object trimIfString(String obj,int maxWidth) {
		Object result = obj;
		if (obj != null) {
			result = StringUtils.abbreviate(obj, maxWidth);
		} else {
			result = "";
		}
		return result;
	}
	
	public static String getDate(String string) {
		return string;
	}
}
