package nju.software.wsjx.util;

import java.util.ArrayList;
import java.util.List;

public class ExceptionUtil {
	public static List<String> getExceptionStackTrace(Exception e){
		List<String> exceptionStackTrace = new ArrayList<String>();
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		for(StackTraceElement stackTraceElement:stackTraceElements){
			exceptionStackTrace.add(stackTraceElement.toString());
		}
		return exceptionStackTrace;
	}
}
