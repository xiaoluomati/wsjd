package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum MsesJafsEnum {
	WC("维持"),
    JCXDSR("仅撤销第三人撤销之诉判决"),
    GP("改判"),
    ZXCHSS("准予撤回上诉"),
	TJ("调解"),
	CXYCPBHQS("撤销原判并驳回起诉"),
	FHCS("发挥重审"),
	YSQTFY("撤销原判并裁定移送其他法院管辖"),
	ACHSSCL("按撤回上诉处理"),
	ZYCHQSCXYSPJ("准予撤回起诉并撤销一审判决"),
	ZLSL("撤销原裁定并指令审理"),
	CXZLSL("撤销原裁定并指令受理");
	MsesJafsEnum(){
	}

	private MsesJafsEnum(String content) {
		this.content = content;
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	//获得所有结案方式
	public static List<String> getAll() {
		List<String> jafsList = new ArrayList<String>();
		for (MsesJafsEnum jafs : MsesJafsEnum.values()) {
			jafsList.add(jafs.getContent());
		}
		return jafsList;
	}
	
	//根据判决结果判断结案方式，包含结案方式就返回
	public static String getMsesjafs(String pjjg){
		List<String> jafsList = getAll();
		for(String jafs:jafsList){
			if(pjjg.indexOf(jafs)!=-1){
				return jafs;
			}
		}
		return null;
	}
}
