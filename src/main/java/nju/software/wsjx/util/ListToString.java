package nju.software.wsjx.util;

import java.util.ArrayList;
import java.util.List;

public class ListToString {
	public static String List2String(List<String> list) {
		String s = "";
		if (list == null)
			return s;
		for (String x : list) {
			s += x.toString() + "\n";
		}
		return s;
	}

	//×Ö·û´®×ªlist
	public static List<String> StringToList(String nr) {
		List<String> result = new ArrayList<String>();
		if (nr == null || nr.equals(""))
			return result;
		for (String str : nr.split("\n")) {
			result.add(str);
		}
		return result;
	}
}
