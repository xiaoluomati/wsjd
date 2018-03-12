package nju.software.wsjx.parserule.wsssjlparserule;

public class GeneralSsjlCommonRule {
	public String getDate(String str) {
		String date = null;
		// 长度大于"xxxx年x月x日"的长度则存在文书日期
		if (str.length() > 8 && str.contains("年") && str.contains("月")
				&& str.contains("日")) {
			date = str;
			if (date.indexOf("年") >= 4
					&& date.indexOf("月") > date.indexOf("年")
					&& (date.indexOf("日") == date.indexOf("月") + 2
							|| date.indexOf("日") == date.indexOf("月") + 3 || date
							.indexOf("日") == date.indexOf("月") + 4)) {
				String year = date.substring(date.indexOf("年") - 4,
						date.indexOf("年"));
				String month = date.substring(date.indexOf("年") + 1,
						date.indexOf("月"));
				String day = date.substring(date.indexOf("月") + 1,
						date.indexOf("日"));
				date = year + "年" + month + "月" + day + "日";
			} else {
				date = null;
			}
		}
		return date;
	}
}
