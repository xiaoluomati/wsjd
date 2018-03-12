/**
 * created by 2010-7-2
 */
package nju.software.wsjx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日期工具类
 * 
 * @author zym
 * 
 */
/**
 * @author wangy
 * 
 */
public class DateUtil {
	public final static String shortFormat = "yyyyMMdd";
	public final static String longFormat = "yyyyMMddHHmmss";
	public final static String webFormat = "yyyy-MM-dd";
	public final static String timeFormat = "HHmmss";
	public final static String monthFormat = "yyyyMM";
	public final static String chineseDtFormat = "yyyy年MM月dd日";
	public final static String chineseFullFormat = "yyyy年MM月dd日 HH时mm分";
	public final static String chineseLongFormat = "yyyy年MM月dd日 HH:mm:ss";
	public final static String chineseshortFormat = "yyyy年MM月";
	/**
	 * 最高院使用日期格式YYYY-MM-DD
	 */
	public final static String newFormat = "yyyy-MM-dd";
	public final static String zbFormat = "yyyy/MM/dd";
	public final static String tsfxFormat = "yyyy.MM.dd";
	public final static String noSecondFormat = "yyyy-MM-dd HH:mm";

	public final static String datetimeFormat = "yyyy-MM-dd HH:mm:ss";

	public final static String sqlFormat = "yyyy/MM/dd HH:mm:ss";

	public final static long ONE_DAY_MILL_SECONDS = 86400000;

	/**
	 * 获得一个日期的去年的同一时间
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getLastYear(Date date) {
		if (date == null) {
			return null;
		}

		int year = getYear(date);
		int month = getMonth(date);
		int day = getDayOfMonth(date);

		StringBuffer sb = new StringBuffer();
		sb.append(year - 1).append("-").append(month).append("-").append(day);
		return parse(sb.toString(), webFormat);
	}

	public static String getLastYearByDate(Date date) {
		if (date == null) {
			return null;
		}

		int year = getYear(date);
		int month = getMonth(date);
		int day = getDayOfMonth(date);

		StringBuffer sb = new StringBuffer();
		sb.append(year - 1).append("-").append(month).append("-").append(day);
		return sb.toString();
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds to a date returning a new object. The original date object is
	 * unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param calendarField
	 *            the calendar field to add to
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}

	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	/**
	 * 标准化输出
	 * 
	 * @param date
	 *            日期对象
	 * @param format
	 *            日期输出格式
	 * @return 按照指定格式输出的字符串
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}

		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 获得一个时间的年份数
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获得一个时间的月份数
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得一个时间在一个月中的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 标准化输出
	 * 
	 * @param s_date
	 * @param format
	 * @return
	 */
	public static Date parse(String s_date, String format) {
		if (s_date == null)
			return null;
		try {
			return new SimpleDateFormat(format).parse(s_date);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得两个日期间隔秒数（日期1-日期2）
	 * 
	 * @param one
	 *            日期1
	 * @param two
	 *            日期2
	 * 
	 * @return 间隔秒数
	 */
	public static long getDiffSeconds(Date one, Date two) {
		Calendar sysDate = new GregorianCalendar();

		sysDate.setTime(one);

		Calendar failDate = new GregorianCalendar();

		failDate.setTime(two);
		return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
	}

	/**
	 * 取得两个日期间隔分钟数（日期1-日期2）
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static long getDiffMinutes(Date one, Date two) {
		Calendar sysDate = new GregorianCalendar();

		sysDate.setTime(one);

		Calendar failDate = new GregorianCalendar();

		failDate.setTime(two);
		return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
				/ (60 * 1000);
	}

	/**
	 * 取得两个日期的间隔天数
	 * 
	 * @param one
	 * @param two
	 * 
	 * @return 间隔天数
	 */
	public static long getDiffDays(Date one, Date two) {
		Calendar sysDate = new GregorianCalendar();

		sysDate.setTime(one);

		Calendar failDate = new GregorianCalendar();

		failDate.setTime(two);
		return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
				/ (24 * 60 * 60 * 1000);
	}

	/**
	 * 判断是否是正确的日期格式
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static boolean isValidDateFormat(String strDate, String format) {
		// 检查长度
		if (strDate.length() != format.length()) {
			return false;
		}

		// 检查格式是否正确
		DateFormat df = new SimpleDateFormat(format);
		df.setLenient(false);
		try {
			df.parse(strDate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * 获得当天的开始时间
	 * 
	 * @return
	 */
	public static Date today() {
		return parse(format(new Date(), webFormat), webFormat);
	}

	/**
	 * 比较两个日期的先后关系
	 * 
	 * @param one
	 *            日期1
	 * @param two
	 *            日期2
	 * @return 0 ： 表示相等 1 ： one大于two -1 ： two大于one
	 */
	public static int compareDate(Date one, Date two) {
		// 合法性判断
		if (one == null && two != null)
			return -1;
		else if (one != null && two == null)
			return 1;
		else if (one == null && two == null)
			return 0;
		else {
			if (one.getTime() > two.getTime())
				return 1;
			if (one.getTime() == two.getTime())
				return 0;
			return -1;
		}
	}

	/**
	 * @param date
	 * @return
	 */
	public static String formatToFullChinese(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sdate = sdf.format(date);
		String newsdate[] = new String[8];
		// 全部转化为汉字
		for (int i = 0; i < sdate.length(); i++) {
			int k = Integer.parseInt(Character.toString(sdate.charAt(i)));
			switch (k) {
			case 0:
				newsdate[i] = "〇";
				break;
			case 1:
				newsdate[i] = "一";
				break;
			case 2:
				newsdate[i] = "二";
				break;
			case 3:
				newsdate[i] = "三";
				break;
			case 4:
				newsdate[i] = "四";
				break;
			case 5:
				newsdate[i] = "五";
				break;
			case 6:
				newsdate[i] = "六";
				break;
			case 7:
				newsdate[i] = "七";
				break;
			case 8:
				newsdate[i] = "八";
				break;
			case 9:
				newsdate[i] = "九";
				break;
			}
		}
		// 加入年月日
		List<String> s1 = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			if (i < 4) {
				s1.add(newsdate[i]);
			} else if (i == 4) {
				s1.add("年");
				s1.add(newsdate[i]);
			} else if (i == 5) {
				s1.add(newsdate[i]);
			} else if (i == 6) {
				s1.add("月");
				s1.add(newsdate[i]);
			} else if (i == 7) {
				s1.add(newsdate[i]);
				s1.add("日");
			}

		}

		String newstr = "";
		for (String s : s1) {
			newstr += s;
		}
		/*
		 * 截取月份、日期
		 */
		int i = newstr.indexOf("年");
		int j = newstr.indexOf("月");
		String month = newstr.substring(i + 1, j);
		String day = newstr.substring(j + 1, newstr.length() - 1);
		/*
		 * 处理月份
		 */
		String str1 = month.substring(0, 1);
		String str2 = month.substring(1);
		String newmonth = "";
		if ("〇".equals(str1)) {
			newmonth = str2;
		} else if ("一".equals(str1) && "〇".equals(str2)) {
			newmonth = "十";
		} else if ("一".equals(str1) && !"〇".equals(str2)) {
			newmonth = "十" + str2;
		}

		/*
		 * 处理日期
		 */
		String st1 = day.substring(0, 1);
		String st2 = day.substring(1);
		String newday = "";
		if ("〇".equals(st1)) {
			newday = st2;
		} else if ("一".equals(st1) && "〇".equals(st2)) {
			newday = "十";
		} else if ("一".equals(st1) && !"〇".equals(st2)) {
			newday = "十" + st2;
		} else if ("二".equals(st1) && "〇".equals(st2)) {
			newday = st1 + "十";
		} else if ("二".equals(st1) && !"〇".equals(st2)) {
			newday = st1 + "十" + st2;
		} else if ("三".equals(st1) && "〇".equals(st2)) {
			newday = st1 + "十";
		} else if ("三".equals(st1) && !"〇".equals(st2)) {
			newday = st1 + "十" + st2;
		}
		String newstring = newstr.substring(0, i) + "年" + newmonth + "月"
				+ newday + "日";
		return newstring;
	}

	// 中文日期转为通用日期
	// 返回一个字符串
	public static String convertToCNDate(String cndate) {
		int yearPos = cndate.indexOf("年");
		int monthPos = cndate.indexOf("月");
		String cnYear = cndate.substring(0, yearPos);
		String cnMonth = cndate.substring(yearPos + 1, monthPos);
		String cnDay = cndate.substring(monthPos + 1, cndate.length() - 1);
		String year = convertCNToNum(cnYear);
		String month = convertCNDateNum(cnMonth);
		String day = convertCNDateNum(cnDay);
		StringBuilder stringbuilder = new StringBuilder();
		if (year != null)
			stringbuilder.append(year + "年");
		if (month != null)
			stringbuilder.append(month + "月");
		if (day != null)
			stringbuilder.append(day + "日");
		return year + "年" + month + "月" + day + "日";

	}

	public static void main(String[] args) {
		String a = "2015年十二月二十一日";
		System.out.println(convertToCNDate(a));
	}

	// 返回一个Date
	public static Date convertToCNDate(String cndate, Boolean isDate) {
		int yearPos = cndate.indexOf("年");
		int monthPos = cndate.indexOf("月");
		String cnYear = cndate.substring(0, yearPos);
		String cnMonth = cndate.substring(yearPos + 1, monthPos);
		String cnDay = cndate.substring(monthPos + 1, cndate.length() - 1);
		String year = convertCNToNum(cnYear);
		String month = convertCNDateNum(cnMonth);
		String day = convertCNDateNum(cnDay);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		return c.getTime();

	}

	private static String convertCNDateNum(String cnNum) {
		if (cnNum.length() == 1) {
			if (cnNum.equals("十"))
				return "10";
			else
				return convertCNToNum(cnNum);
		} else if (cnNum.length() == 2) {
			if (cnNum.startsWith("十")) {
				return "1" + convertCNToNum(cnNum.substring(1, 2));
			}
			if (cnNum.endsWith("十")) {
				return convertCNToNum(cnNum.substring(0, 1)) + "0";
			} else {
				return convertCNToNum(cnNum);
			}
		} else if (cnNum.length() == 3) {
			return convertCNToNum(cnNum.substring(0, 1) + cnNum.substring(2, 3));
		}
		return null;

	}

	private static String convertCNToNum(String cnNumStr) {
		String allCNNum = "o○〇０OΟO零-一二三四五六七八九";
		String allNum = "000000001123456789";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < cnNumStr.length(); i++) {
			char c = cnNumStr.charAt(i);
			int index = allCNNum.indexOf(c);
			if (index != -1) {
				buf.append(allNum.charAt(index));
			} else {
				buf.append(c);
			}
		}
		return buf.toString();

	}

	/**
	 * 得到默认起始时间
	 * 
	 * @return 上一年12月21日
	 */
	public static Date getDefaultYearBeginTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR) - 1, 11, 21);
		return calendar.getTime();
	}

	public static Date getDefaultBeginTime() {
		return DateUtil.addDays(new Date(), -7);
	}

	@SuppressWarnings("deprecation")
	public static Date getBeginTime() {
		Date begin = new Date();
		if (DateUtil.getMonth(begin) == 12
				&& DateUtil.getDayOfMonth(begin) >= 21)
			begin = DateUtil.parse(1900 + begin.getYear() + "-12-21",
					DateUtil.webFormat);
		else
			begin = DateUtil.parse((1900 + begin.getYear() - 1) + "-12-21",
					DateUtil.webFormat);
		return begin;
	}

	/**
	 * 返回下一天
	 * 
	 * @param date
	 *            当天，格式xxxx/xx/xx
	 * @return
	 */
	public static String nextDay(String date) {
		// 解析出的日期未必都是合法的，所以捕获异常
		try {
			int index1 = date.indexOf("/");
			int index2 = date.lastIndexOf("/");
			int year = Integer.parseInt(date.substring(0, index1));
			int month = Integer.parseInt(date.substring(index1 + 1, index2));
			int day = Integer.parseInt(date.substring(index2 + 1));
			int febDay;
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
				febDay = 29;
			else
				febDay = 28;
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
				if (day < 31)
					return year + "/" + month + "/" + (day + 1);
				else
					return year + "/" + (month + 1) + "/" + "1";

			case 12:
				if (day < 31)
					return year + "/" + month + "/" + (day + 1);
				else
					return (year + 1) + "/" + "1" + "/" + "1";
			case 2:
				if (day < febDay)
					return year + "/" + month + "/" + (day + 1);
				else
					return year + "/" + 3 + "/" + "1";
			case 4:
			case 6:
			case 9:
			case 11:
				if (day < 30)
					return year + "/" + month + "/" + (day + 1);
				else
					return (year + 1) + "/" + "1" + "/" + "1";
			}
			return "";

		} catch (Exception e) {
			return "";
		}
	}

	public static Date parseToDate(String dateStr) {
		int index1 = dateStr.indexOf("/");
		int index2 = dateStr.lastIndexOf("/");
		int year = Integer.parseInt(dateStr.substring(0, index1));
		int month = Integer.parseInt(dateStr.substring(index1 + 1, index2));
		int day = Integer.parseInt(dateStr.substring(index2 + 1));
		Date date = new Date(year, month, day);
		return date;
	}
}
