package org.web.quartz.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类DateUtils.java的实现描述： 类实现描述
 * 
 * @author Administrator 2015年9月11日 下午4:26:53
 */
public class DateUtils {

	public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMAT_ONE = "MM/dd/yyyy HH:mm:ss";
	
	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";


	/**
	 * @param date
	 * @return 返回选择日期的初始时间点
	 */
	public static Date getBeginOfSelectedDate(Date date) {
		Calendar beginDateCalendar = Calendar.getInstance();

		beginDateCalendar.setTimeInMillis(date.getTime());
		beginDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
		beginDateCalendar.set(Calendar.MINUTE, 0);
		beginDateCalendar.set(Calendar.SECOND, 0);
		beginDateCalendar.set(Calendar.MILLISECOND, 0);

		return new Date(beginDateCalendar.getTimeInMillis());
	}

	/**
	 * @param date
	 * @return 返回选择日期的结束时间点
	 */
	public static Date getEndOfSelectedDate(Date date) {
		Calendar endDateCalendar = Calendar.getInstance();

		endDateCalendar.setTimeInMillis(date.getTime());
		endDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
		endDateCalendar.set(Calendar.MINUTE, 59);
		endDateCalendar.set(Calendar.SECOND, 59);
		endDateCalendar.set(Calendar.MILLISECOND, 0);

		return new Date(endDateCalendar.getTimeInMillis());
	}

	public static String getBeginStrOfSelectedDate(Date date) {
		Calendar beginDateCalendar = Calendar.getInstance();

		beginDateCalendar.setTimeInMillis(date.getTime());
		beginDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
		beginDateCalendar.set(Calendar.MINUTE, 0);
		beginDateCalendar.set(Calendar.SECOND, 0);
		beginDateCalendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
		return df.format(new Date(beginDateCalendar.getTimeInMillis()));
	}
	
	
	public static String getWeekBeginStrOfSelectedDate(Date date) {
		Calendar beginDateCalendar = Calendar.getInstance();
		beginDateCalendar.setTimeInMillis(date.getTime());
		beginDateCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		beginDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
		beginDateCalendar.set(Calendar.MINUTE, 0);
		beginDateCalendar.set(Calendar.SECOND, 0);
		beginDateCalendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
		return df.format(new Date(beginDateCalendar.getTimeInMillis()));
	}

	
	public static Date getWeekEndOfSelectedDate(Date date) {
		Calendar endDateCalendar = Calendar.getInstance();

		endDateCalendar.setTimeInMillis(date.getTime());
		endDateCalendar.set(Calendar.HOUR_OF_DAY, Calendar.SATURDAY);
		endDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
		endDateCalendar.set(Calendar.MINUTE, 59);
		endDateCalendar.set(Calendar.SECOND, 59);
		endDateCalendar.set(Calendar.MILLISECOND, 0);

		return new Date(endDateCalendar.getTimeInMillis());
	}
	
	public static String getEndStrOfSelectedDate(Date date) {
		Calendar endDateCalendar = Calendar.getInstance();

		endDateCalendar.setTimeInMillis(date.getTime());
		endDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
		endDateCalendar.set(Calendar.MINUTE, 59);
		endDateCalendar.set(Calendar.SECOND, 59);
		endDateCalendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
		return df.format(new Date(endDateCalendar.getTimeInMillis()));
	}

	public static final String getDateStr(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * @param date
	 * @param format
	 * @return 把一种时间格式转成另一种时间格式.
	 */
	public static final String getDateStr(String date, String beforeFormat, String format) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(beforeFormat);
		return getDateStr(df.parse(date), format);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(DateUtils.getBeginStrOfSelectedDate(new Date()));
		System.out.println(DateUtils.getBeginOfSelectedDate(new Date()));
		System.out.println(DateUtils.getEndStrOfSelectedDate(new Date()));
		System.out.println(DateUtils.getEndOfSelectedDate(new Date()));
		System.out.println(DateUtils.getDateStr(new Date(), FORMAT_YYYY_MM_DD));
		System.out.println(DateUtils.getDateStr(new Date(), FORMAT_YYYY_MM_DD_HH_MM_SS));
		System.out.println(DateUtils.getDateStr("2015-09-11", FORMAT_YYYY_MM_DD, FORMAT_YYYY_MM_DD_HH_MM_SS));
		System.out.println(DateUtils.getDateStr("2015-09-11 17:12:15", FORMAT_YYYY_MM_DD_HH_MM_SS, FORMAT_YYYY_MM_DD));

	}
}
