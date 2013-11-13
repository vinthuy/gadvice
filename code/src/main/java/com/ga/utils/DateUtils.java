package com.ga.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @description 日期工具类
 * @author  <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @see     
 * @since   girladvice1.0
 */
public class DateUtils {
	
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String MUNITES_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static final String CHINESE_DATETIME_FORMAT = "yyyy年MM月dd日HH时mm分ss秒";
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String SUNDAY = "Sunday";
	
	public static final String MONDAY = "Monday";
	
	public static final String TUESDAY = "Tuesday";
	
	public static final String WEDNESDAY = "Wednesday";
	
	public static final String THURSDAY = "Thursday";
	
	public static final String FRIDAY = "Friday";
	
	public static final String SATURDAY = "Saturday";
	
	private static Calendar calendar;
	
	static {
		calendar = Calendar.getInstance();
	}

	/**
	 * @description 以默认格式将字符串转换成Date对象
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param dateString
	 * @return 
	 * @since girladvice1.0
	 */
	public static Date stringToDate(String dateString){
		return stringToDate(dateString, DEFAULT_DATETIME_FORMAT);
	}
	
	/**
	 * @description 以指定的格式将字符串转换成Date对象
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param dateString
	 * @param pattern
	 * @return 
	 * @since girladvice1.0
	 */
	public static Date stringToDate(String dateString, String pattern) {
		SimpleDateFormat dateFormate = new SimpleDateFormat();
		dateFormate.applyPattern(StringUtils.isNotNullOrBlank(pattern) ? pattern
				: DEFAULT_DATETIME_FORMAT);
		return dateFormate.parse(dateString, new ParsePosition(0));
	}
	
	/**
	 * @description 以默认格式将Date对象转换成字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param date
	 * @return 
	 * @since girladvice1.0
	 */
	public static String dateToString(Date date){
		return dateToString(date, DEFAULT_DATETIME_FORMAT);
	}
	
	/**
	 * @description 以指定格式将Date对象转换成字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param date
	 * @param pattern
	 * @return 
	 * @since girladvice1.0
	 */
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat dateFormate = new SimpleDateFormat(
				StringUtils.isNotNullOrBlank(pattern) ? pattern : DEFAULT_DATETIME_FORMAT);
		return dateFormate.format(date);
	}
	
	/**
	 * @description 将时间数字转换成默认格式的日期字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param time 时间数字
	 * @return 
	 * @since girladvice1.0
	 */
	public static String timeNumberToString(long time) {
		return timeNumberToString(time, DEFAULT_DATETIME_FORMAT);
	}
	
	/**
	 * @description 将时间数字转换成指定格式的日期字符串
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param time 
	 * @param pattern 
	 * @return 
	 * @since girladvice1.0
	 */
	public static String timeNumberToString(long time, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				StringUtils.isNotNullOrBlank(pattern) ? pattern : DEFAULT_DATETIME_FORMAT);
		calendar.setTimeInMillis(time); 
		return formatter.format(calendar.getTime());				
	}
	
	/**
	 * @description 判断两个日期是否为同一天
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param now
	 * @param then
	 * @return 
	 * @since girladvice1.0
	 */
	public static boolean isSameDay(Date now, Date then) {
		return now != null && then != null && 
				dateToString(now, DEFAULT_DATE_FORMAT).equals(dateToString(then, DEFAULT_DATE_FORMAT));
	}
	
	/**
	 * @description 判断指定的日期是否就是为今天
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param date
	 * @return 
	 * @since girladvice1.0
	 */
	public static boolean isToday(Date date){
		return isSameDay(new Date(), date);
	}
	
	/**
	 * @description 获取两个时间间隔的毫秒数
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param date1 
	 * @param date2
	 * @return 
	 * @since girladvice1.0
	 */
	public static long getIntervalMilliseconds(Date date1, Date date2) {
		return Math.abs(date1.getTime() - date2.getTime());
	}
	
	/**
	 * @description 获取两个日期间隔的秒数
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param date1
	 * @param date2
	 * @return 
	 * @since girladvice1.0
	 */
	public static long getIntervalSeconds(Date date1, Date date2) {
		return Math.abs((date1.getTime() - date2.getTime()) / 1000L);
	}
	
	/**
	 * @description 获取两个日期间隔的分钟数
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param date1
	 * @param date2
	 * @return 
	 * @since girladvice1.0
	 */
	public static long getIntervalMinute(Date date1, Date date2) {
		return Math.abs((date1.getTime() - date2.getTime()) / 60000L);
	}
	
	/**
	 * @description 获取两个日期间隔的小时数
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param date1
	 * @param date2
	 * @return 
	 * @since girladvice1.0
	 */
	public static long getIntervalHour(Date date1, Date date2) {
		return Math.abs((date1.getTime() - date2.getTime()) / 3600000L);
	}
	
	/**
	 * @description 获取两个日期间隔的天数
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param date1
	 * @param date2
	 * @return 
	 * @since girladvice1.0
	 */
	public static int getIntervalDays(Date date1, Date date2) {
		return Math.abs(Integer.parseInt(String.valueOf(getIntervalMilliseconds(date1, date2) / 86400000L)));
	}
	
	/**
	 * @description 获取当前日期与截止日期之间间隔的周数
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param date1
	 * @param date2
	 * @return 
	 * @since girladvice1.0
	 */
	public static int getIntervalWeeks(Date date1, Date date2) {
		return getIntervalDays(date1, date2) / 7;
	}
	
	/**
	 * @description 获取在指定日期的这周内每一天的日期
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param now
	 * @return 
	 * @since girladvice1.0
	 */
	public static Date[] getDateOfThisWeek(Date now){
		Date []dayOfWeek = new Date[7];
		calendar.setTime(now);
		// 本周第一天(星期天)的字段编号
		int day = calendar.getFirstDayOfWeek();
		for (int i = 0; i < 7; i++) {
			calendar.set(Calendar.DAY_OF_WEEK, day++);
			dayOfWeek[i] = calendar.getTime();
		}
		return dayOfWeek;
	}
	
	/**
	 * @description 判断指定的日期是否为一个周末
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param now
	 * @return 
	 * @since girladvice1.0
	 */
	public static boolean isWeekend(Date now){
		calendar.setTime(now);
		// 当前日期在本周的字段编号
		int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
		return (currentDay == Calendar.SUNDAY) || (currentDay == Calendar.SATURDAY);
	}
	
	/**
	 * @description 获取在指定日期的这周内，周末(星期六和星期天)的日期
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param now
	 * @return 
	 * @since girladvice1.0
	 */
	public static Map<String, Date> getWeekend(Date now){
		Map<String,Date> map = new HashMap<String, Date>();
		calendar.setTime(now);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		map.put(SUNDAY, calendar.getTime());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		map.put(SATURDAY, calendar.getTime());
		return map;
	}
	
	/**
	 * @description 获取在指定日期的这周内,某一天的日期
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param now
	 * @param weekday 第几天
	 * @return 
	 * @since girladvice1.0
	 */
	public static Date getDateInWeek(Date now,int weekday){
		calendar.setTime(now);
		calendar.set(Calendar.DAY_OF_WEEK, weekday);
		return calendar.getTime();
	}
	
	/**
	 * @description 获取指定的日期是星期几
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param now
	 * @return 
	 * @since girladvice1.0
	 */
	public static String getDayOfWeekStr(Date now){
		calendar.setTime(now);
		String result = null;
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
			case 1:
				result = SUNDAY;
				break;
			case 2:
				result = MONDAY;
				break;
			case 3:
				result = TUESDAY;
				break;
			case 4:
				result = WEDNESDAY;
				break;
			case 5:
				result = THURSDAY;
				break;
			case 6:
				result = FRIDAY;
				break;
			case 7:
				result = SATURDAY;
				break;
		}
		return result;
	}
	
	/**
	 * @description 获取昨天的日期
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @return 
	 * @since girladvice1.0
	 */
	public static Date getYesterday(){
		return getDateOfDay(new Date(), -1);
	}
	
	/**
	 * @description 获取昨天的日期
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @return 
	 * @since girladvice1.0
	 */
	public static Date getTomorrow(){
		return getDateOfDay(new Date(), 1);
	}
	
	/**
	 * @description 获取距离指定日期多少天以前或以后的日期
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param now
	 * @param days
	 * @return 
	 * @since girladvice1.0
	 */
	public static Date getDateOfDay(Date now, int days) {
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	} 

	/**
	 * @description 获取当前时间分钟格式部分
	 * @author <a href="mailto:ruiyong@daw.so">胡瑞永</a> 
	 * @return 
	 * @since girladvice1.0
	 */
	public static Date getCurrentDateOfMM(){
		return DateUtils.stringToDate(DateUtils.dateToString(new Date(),DateUtils.MUNITES_DATETIME_FORMAT),DateUtils.MUNITES_DATETIME_FORMAT);
	}
	
}
