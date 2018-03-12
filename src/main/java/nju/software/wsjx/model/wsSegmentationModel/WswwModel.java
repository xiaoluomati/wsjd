package nju.software.wsjx.model.wsSegmentationModel;

import java.util.HashMap;
import java.util.List;



/**
 * 文书文尾model
 * @author lr12
 *
 */
public class WswwModel {
	private HashMap<String, String> spzzcyMap;//审判组织成员
	private String wsrq=null;//文书日期
	private String year;//结案年度
	private String month;//结案月份
	private String day;//结案日
	private String yearAndMonth;//结案年月；
	
	
	
	public HashMap<String, String> getSpzzcyMap() {
		return spzzcyMap;
	}
	public void setSpzzcyMap(HashMap<String, String> spzzcyMap) {
		this.spzzcyMap = spzzcyMap;
	}
	public String getWsrq() {
		return wsrq;
	}
	public void setWsrq(String wsrq) {
		this.wsrq = wsrq;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getYearAndMonth() {
		return yearAndMonth;
	}
	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}
	@Override
	public String toString() {
		return "WswwModel [spzzcyMap=" + spzzcyMap + ", wsrq=" + wsrq
				+ ", year=" + year + ", month=" + month + ", day=" + day
				+ ", yearAndMonth=" + yearAndMonth + "]";
	}
	
	

}

