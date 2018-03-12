package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * 文书类别枚举
 * 
 * @author lr12
 *
 */
public enum WslbEnum {
	
	TSBL("庭审笔录"), 
	PJS("判决书"), 
	CDS("裁定书"), 
	TJS("调解书"), 
	QT("其他");

	private String content;

	private WslbEnum(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	//获取全部的文书类别
	public static List<String> getWslbList() {
		List<String> wslbs = new ArrayList<String>();
		for (WslbEnum wslbEnum : WslbEnum.values()) {
			wslbs.add(wslbEnum.getContent());
		}
		return wslbs;
	}
	
}
