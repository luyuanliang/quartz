package org.web.quartz.utils;

public class Utils {

	public static String getExceptionInfo(Exception e) {
		StringBuffer buffer = new StringBuffer();
		if (e != null) {
			buffer.append(e.toString()).append("\n\t");
			StackTraceElement[] messages = e.getStackTrace();
			int length = messages.length;
			for (int i = 0; i < length; i++) {
				buffer.append("at ").append(messages[i].toString()).append("\n\t");
			}
		}
		return buffer.toString();
	}
}
