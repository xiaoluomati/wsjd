package nju.software.wsjx.service.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Properties;




import nju.software.wsjx.util.DateUtil;


/**
 * 文书查询的相关配置文件，从WsConfig.properties中获得
 * 
 * @author yjj
 * 
 */

public class WsConfig {



	/** 法院代码 */
	private static String[] fydm;

	/** 起始时间 */
	private static String startDate;

	/** 终止时间 */
	private static String endDate;

	/** 案件性质 */
	private static String[] ajxz;

	/** 文书内容 */
	private static String content;

	static {
		String filePath = "WsConfig.properties";
		Properties prop = new Properties();
		try {
			String path = WsConfig.class.getClassLoader().getResource("")
					.toURI().getPath();
			FileInputStream in = new FileInputStream(new File(path + filePath));
			prop.load(in);
			String charset = prop.getProperty("charset", "utf-8");
			String fydms = new String(prop.getProperty("wsconfig.fydm", "")
					.getBytes("ISO-8859-1"), charset);
			String splitSign = new String(prop.getProperty(
					"wsconfig.splitSign", "").getBytes("ISO-8859-1"), charset);
			if (fydms.equals("")) {
				fydm = new String[] { "120000 200" };
			} else if (splitSign.equals("")) {
				fydm = new String[1];
				fydm[0] = fydms;
			} else {
				fydm = fydms.split(splitSign);
			}

			endDate = new String(prop.getProperty("wsconfig.endDate", "")
					.getBytes("ISO-8859-1"), charset);
			if (endDate.equals("")) {
				endDate = DateUtil.format(new Date(), DateUtil.webFormat);
			}

			startDate = new String(prop.getProperty("wsconfig.startDate", "")
					.getBytes("ISO-8859-1"), charset);
			if (startDate.equals("")) {
				startDate = DateUtil.getLastYearByDate(DateUtil.parse(endDate,
						DateUtil.webFormat));
			}

			String ajxzs = new String(prop.getProperty("wsconfig.ajxz", "")
					.getBytes("ISO-8859-1"), charset);
			if (ajxzs.equals("")) {
				ajxz = null;
			} else if (splitSign.equals("")) {
				ajxz = new String[1];
				ajxz[0] = ajxzs;
			} else {
				ajxz = ajxzs.split(splitSign);
			}

			content = new String(prop.getProperty("wsconfig.content", "")
					.getBytes("ISO-8859-1"), charset);

		} catch (URISyntaxException e) {
			
		} catch (FileNotFoundException e) {
			
		} catch (UnsupportedEncodingException e) {
			
		} catch (Exception e) {
			
		}
	}

	public static String getContent() {
		return content;
	}

	public static void setContent(String content) {
		WsConfig.content = content;
	}

	public static String[] getFydm() {
		return fydm;
	}

	public static void setFydm(String[] fydm) {
		WsConfig.fydm = fydm;
	}

	public static String getStartDate() {
		return startDate;
	}

	public static void setStartDate(String startDate) {
		WsConfig.startDate = startDate;
	}

	public static String getEndDate() {
		return endDate;
	}

	public static void setEndDate(String endDate) {
		WsConfig.endDate = endDate;
	}

	public static String[] getAjxz() {
		return ajxz;
	}

	public static void setAjxz(String[] ajxz) {
		WsConfig.ajxz = ajxz;
	}

}
