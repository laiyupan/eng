package com.eng.one.nine.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import org.apache.commons.validator.GenericValidator;


/**
 * 日期转换共通类
 * 
 */
public class DateUtil {
	public static final String empty = "";
	/** 基本的日期格式 */
	public static final String DATE_PATTERN = "yy/M/d";

	/** 基本的日期时间格式 */
	public static final String DATETIME_PATTERN = "yy/M/d H:m:s";
	
	/** 全日期格式 */
	public static final String DATETIME_FULLPATTERN = "yyyy/MM/dd HH:mm:ss";
	
	/** 全日期格式 - yyyy-MM-dd HH:mm:ss */
	public static final String DATETIME_PATTERN_ONE = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATETIME_PATTERN_TWO = "yyyyMMddHHmmss";
	
	/**
	 * 格式化Date对象为日期字符串<br>
	 * 默认格式为：yyyy-MM-dd
	 * @param date 要格式化的Date对象
	 * @return 格式化后的日期字符串
	 */
	public static String dateToString(Date date) {
		return dateFormat(date, "yyyy-MM-dd");
	}
	
	/**
	 * 格式化Date对象为日期时间字符串<br>
	 * 默认格式为：yyyy-MM-dd HH:mm:ss
	 * @param date 要格式化的Date对象
	 * @return 格式化后的日期时间字符串
	 */
	public static String dateTimeToString(Date date) {
		return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 格式化Date对象为时间字符串<br>
	 * 默认格式为：HH:mm:ss
	 * @param date 要格式化的Date对象
	 * @return 格式化后的时间字符串
	 */
	public static String timeToString(Date date) {
		return dateFormat(date, "HH:mm:ss");
	}
	
	/**
	 * 按照指定的格式格式化Date对象<br>
	 * @param date 要格式化的Date对象
	 * @param format 格式字符串
	 * @return 格式化后的字符串
	 */
	public static String dateFormat(Date date, String format) {
		if(date == null)
			return empty;
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	/**
	 * 日期字符串转成日期对象
	 * @param dateStr 日期字符串
	 * @param format 格式化字符串
	 * @return 转换后的日期对象
	 */
	public static Date stringToDate(String dateStr, String format) {
		Date date = null;
		if(!StringUtil.isAnyNullOrEmpty(dateStr)) {
			DateFormat df = new SimpleDateFormat(format);
			try {
				date = df.parse(dateStr);
			} catch (ParseException e) {
				date = null;
			}
		}
		return date;
	}
	
	/**
	 * 返回yyyy/MM/dd HH:mm:ss形式的系统日期字符串
	 * 
	 * @return 当前的服务器系统时间
	 */
	public static String getSystemDateTime() {
		long lDate = System.currentTimeMillis();
		Date date = new Date(lDate);
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN_TWO);
		return sdf.format(date);
	}
	
	/**
	 * 返回当前时间 
	 * @return
	 */
	public static Date getSystemDate() {
		long lDate = System.currentTimeMillis();
		Date date = new Date(lDate);
		return date;
	}
	
	/**
	 * 返回yyyy/MM/dd HH:mm:ss形式的系统日期字符串（今日的00:00:00的时间）
	 * @return
	 */
	public static String getToday00String() {
		String now = getSystemDateTime();
		String startTime = now.substring(0, 10) + " 00:00:00";
		return startTime;
	}
	
	/**
	 * 返回yyyy/MM/dd HH:mm:ss形式的系统日期（今日的00:00:00的时间）
	 * @return
	 */
	public static Date getToday00() {
		String now = getSystemDateTime();
		String startTime = now.substring(0, 10) + " 00:00:00";
		Date dStartTime = stringToDate(startTime, DATETIME_FULLPATTERN);
		return dStartTime;
	}
	
	/**
	 * 返回yyyy/MM/dd HH:mm:ss形式的系统日期字符串（今日的23:59:59的时间）
	 * @return
	 */
	public static String getToday23String() {
		String now = getSystemDateTime();
		String endTime = now.substring(0, 10) + " 23:59:59";
		return endTime;
	}
	
	/**
	 * 返回yyyy/MM/dd HH:mm:ss形式的系统日期（今日的23:59:59的时间）
	 * @return
	 */
	public static Date getToday23() {
		String now = getSystemDateTime();
		String endTime = now.substring(0, 10) + " 23:59:59";
		Date dEndTime = stringToDate(endTime, DATETIME_FULLPATTERN);
		return dEndTime;
	}
	
	/**
	 * 取得某年某月的最后一天
	 * @param year 年
	 * @param month 月（1－12）
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int getLastDayInMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(c.YEAR, year - 1900);
		c.set(c.MONTH, month - 1);

		return c.getActualMaximum(c.DAY_OF_MONTH);
	}
	
	/**
	 * 某月的最后一天取得
	 * @param date 日期
	 * @param pattern 日期格式
	 * @return
	 */
	public static int getLastDayInMonth(String date, String pattern) {
		Date d = stringToDate(date, pattern);
		return getLastDayInMonth(d);
	}
	
	/**
	 * 取得某月的最后一天
	 * @param date 日期
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int getLastDayInMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(c.DAY_OF_MONTH);
	}
	
	/**
	 * 调整月份数<br>
	 * 例子：<br>
	 * 1、2003/11, yyyy/MM, 3  --> 2004/02<br> 
	 * 2、2003/11, yyyy/MM, -1 --> 2003/10
	 * @param date 日期
	 * @param pattern 日期格式
	 * @param n 要调整的月份数量
	 * @return
	 */
	public static String changeMonth(String date, String pattern, int n) {
		Date d = stringToDate(date, pattern);
		d = changeMonth(d, n);
		return getDateTime(d, pattern);
	}
	
	/**
	 * 返回月份调整后的新日期对象<br>
	 * 例子：<br>
	 * 1、2003/04/30, 2  --> 2003/06/30<br>
	 * 2、2003/04/30, -2 --> 2003/03/02
	 * @param now 现在的日期对象
	 * @param n 要调整的月份数量
	 * @return Date
	 */
	public static Date changeMonth(Date now, int n) {
		if (now == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FULLPATTERN);
		String s = sdf.format(now);
		sdf = new SimpleDateFormat("M");
		int m = Integer.parseInt(sdf.format(now));
		m = m + n;
		s = s.substring(0, 5) + m + s.substring(7);
		sdf = new SimpleDateFormat(DATETIME_FULLPATTERN);
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 获取特定字符串格式的时间或日期
	 * @param d 日期对象
	 * @param p 日期格式
	 * @return
	 */
	public static String getDateTime(Date d, String p) {
		if (d == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		return sdf.format(d);
	}
	
	/**
	 * 指定日期后n天的日期
	 * @param date 日期
	 * @param pattern 日期格式
	 * @param n 天数
	 * @return
	 */
	public static String changeDay(String date, String pattern, int n) {
		Date d = stringToDate(date, pattern);
		long time = d.getTime();
		time += n * 24 * 60 * 60 * 1000;
		Date nd = new Date(time);
		return getDateTime(nd, pattern);
	}
	
	/**
	 * 两个时间前后的判断
	 * @param beforeDate 第一个日期
	 * @param afterDate 第二个日期
	 * @param p 日期形式
	 * @return true: 第一个日期 < 第二个日期
	 */
	public static boolean compareDate(String beforeDate, String afterDate,
			String p) {
		Date d1 = stringToDate(beforeDate, p);
		Date d2 = stringToDate(afterDate, p);
		boolean flag = false;
		if (d1 == null || d2 == null) {
			return false;
		}
		if(d1.before(d2) || d1.equals(d2)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 两个时间前后的判断
	 * @param beforeDate 第一个日期
	 * @param afterDate 第二个日期
	 * @param p 日期形式
	 * @return 
	 * =0: 第一个日期 = 第二个日期<br>
	 * >0 : 第一个日期 > 第二个日期<br>
	 * <0 : 第一个日期 < 第二个日期
	 */
	public static int compareDate2(String beforeDate, String afterDate,
			String p) {
		Date d1 = stringToDate(beforeDate, p);
		Date d2 = stringToDate(afterDate, p);
		if (d1 == null || d2 == null) {
			return 0;
		}
		return d1.compareTo(d2);
	}
	
	/**
	 * 返回yyyy/MM/dd格式的日期字符串
	 * @param strYear 年
	 * @param strMonth 月
	 * @param strDay 日
	 * @return 格式化后的数据，数据不正返回NULL
	 */
	public static String formatDate(String strYear, String strMonth,
			String strDay) {
		try {
			int iYear = Integer.parseInt(strYear);
			int iMonth = Integer.parseInt(strMonth);
			int iDay = Integer.parseInt(strDay);
			return formatDate(iYear, iMonth, iDay);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 返回yyyy/MM/dd格式的日期字符串
	 * @param iYear 年
	 * @param iMonth 月
	 * @param iDay 日
	 * @return 格式化后的数据，数据不正返回NULL
	 */
	public static String formatDate(int iYear, int iMonth, int iDay) {
		try {
			Calendar cld = Calendar.getInstance();
			cld.set(iYear, iMonth - 1, iDay);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			return sdf.format(cld.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 检查是否构成一个合法日期
	 * @param iYear 年
	 * @param iMonth 月
	 * @param iDay 日
	 * @return true：构成合法日期；false：日期非法
	 */
	public static boolean checkDate(int iYear, int iMonth, int iDay) {
		if (formatDate(iYear, iMonth, iDay) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 返回yyyy/MM/dd HH:mm:ss格式的日期字符串
	 * @param strYear 年
	 * @param strMonth 月
	 * @param strDay 日
	 * @param strHour 小时
	 * @param strMinute 分
	 * @param strSecond 秒
	 * @return 格式化后的数据，数据不正返回NULL
	 */
	public static String formatDateTime(String strYear, String strMonth,
			String strDay, String strHour, String strMinute, String strSecond) {
		try {
			int iYear = Integer.parseInt(strYear);
			int iMonth = Integer.parseInt(strMonth);
			int iDay = Integer.parseInt(strDay);
			int iHour = Integer.parseInt(strHour);
			int iMinute = Integer.parseInt(strMinute);
			int iSecond = Integer.parseInt(strSecond);
			return formatDateTime(iYear, iMonth, iDay, iHour, iMinute, iSecond);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 返回yyyy/MM/dd HH:mm:ss格式的日期字符串
	 * @param iYear 年
	 * @param iMonth 月
	 * @param iDay 日
	 * @param iHour 时
	 * @param iMinute 分
	 * @param iSecond 秒
	 * @return 返回格式化后的数据，数据不正返回NULL
	 */
	public static String formatDateTime(int iYear, int iMonth, int iDay,
			int iHour, int iMinute, int iSecond) {
		try {
			Calendar cld = Calendar.getInstance();
			cld.set(iYear, iMonth - 1, iDay, iHour, iMinute, iSecond);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return sdf.format(cld.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 检查是否构成一个合法日期
	 * @param strYear 年
	 * @param strMonth 月
	 * @param strDay 日
	 * @return true：构成合法日期；false：日期非法
	 */
	public static boolean checkDate(String strYear, String strMonth,
			String strDay) {
		if (formatDate(strYear, strMonth, strDay) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查是否构成一个合法日期
	 * @param strDate 日期
	 * @param strDatePattern 日期格式
	 * @return true：构成合法日期；false：日期非法
	 */
	public static boolean checkDate(String strDate, String strDatePattern) {
		return isDate(strDate, strDatePattern);
	}
	
	/**
	 * 检查是否构成一个合法时间
	 * @param iYear 年
	 * @param iMonth 月
	 * @param iDay 日
	 * @param iHour 时
	 * @param iMinute 分
	 * @param iSecond 秒
	 * @return true：构成合法时间；false：时间非法
	 */
	public static boolean checkDateTime(int iYear, int iMonth, int iDay,
			int iHour, int iMinute, int iSecond) {
		if (formatDateTime(iYear, iMonth, iDay, iHour, iMinute, iSecond) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查是否构成一个合法时间
	 * @param strYear 年
	 * @param strMonth 月
	 * @param strDay 日
	 * @param strHour 时
	 * @param strMinute 分
	 * @param strSecond 秒
	 * @return true：构成合法时间；false：时间非法
	 */
	public static boolean checkDateTime(String strYear, String strMonth,
			String strDay, String strHour, String strMinute, String strSecond) {
		if (formatDateTime(strYear, strMonth, strDay, strHour, strMinute,
				strSecond) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断一个日期是否合法
	 * @param date 要判断的日期
	 * @param dateForm 日期格式
	 * @return true：合法日期；false：不合法日期;
	 */
	public static boolean isDate(String date, String dateForm) {
		//return GenericValidator.isDate(date, dateForm, true);
		return false;
	}
	
	/**
	 * 计算某一天所在的那个星期的星期一是几号
	 * @param date 要计算的某一天
	 * @return 某一天所在的那个星期的星期一
	 */
	public static Calendar mondayOfWeek(Calendar date) {
		Calendar monday = date;
		int def = monday.get(Calendar.DAY_OF_WEEK) - 2;
		monday.add(Calendar.DAY_OF_MONTH, -def);
		return monday;
	}
	
	/**
	 * 通过身份证取得出生年月 
	 * @param idNumber
	 * @param str 操作符  
	 * @return
	 */
	public static String  getBirthday(String idNumber , String str) {
		String Resutl = "";
		String year = "";
		String mouth = "";
		String day = "";
		if ((null == idNumber) || (StringUtil.isAnyNullOrEmpty(idNumber))) {
			return "";
		}
		if (idNumber.length() == 18) {
			//如果是18的身份证:
			year = idNumber.substring(6,10);
			mouth = idNumber.substring(10,12);
			day = idNumber.substring(12,14);
			Resutl = year + str + mouth + str + day ;
		} else if (idNumber.length() == 15) {
			//如果是15位的身份证:320684830221061
			year = "19" + idNumber.substring(6,8);
			mouth = idNumber.substring(8,10);
			day = idNumber.substring(10,12);
			Resutl = year + str + mouth + str + day ;
		} else {
			//位数不够 
			return "";
		}
		return Resutl;
	}
	
	/**
	 * java.util.Date 转换成 java.sql.Timestamp
	 * @param date
	 * @return
	 */
	public static Timestamp parseUtilDateToSQLTimestamp(java.util.Date date) {
		if(date == null) {
			return null;
		}
		java.sql.Timestamp d = new java.sql.Timestamp(date.getTime());
		return d;
	}
	
	public static void main(String[] args) {
		System.out.println(getSystemDateTime());
	}
}
