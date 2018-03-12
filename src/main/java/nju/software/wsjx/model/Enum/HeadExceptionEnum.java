package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum HeadExceptionEnum {	
	JGDM("机构代码"),
	ZZJGDM("组织机构代码"),
	GMSFHM("公民身份号码"),
	GMSFZH("公民身份证号"),
	GMSFH("公民身份号"),
	TYSHXYDM("统一社会信用代码"),
	SFHM("身份号码"),
	SFZH("身份证号"),
	XZ("现住"),
	DLQX("代理权限");
	HeadExceptionEnum() {
	}
	private HeadExceptionEnum(String content) {
		this.content = content;
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static List<String> getHeadList() {
		List<String> headList = new ArrayList<String>();
		for (HeadExceptionEnum headExceptionEnum : HeadExceptionEnum.values()) {
			headList.add(headExceptionEnum.getContent());
		}
		return headList;
	}
	public static boolean HasHead(String head){
		List<String> list=getHeadList();
		for(int i=0;i<list.size();i++){
		//	if(head.indexOf(list.get(i))>-1&&head.indexOf(list.get(i))<10){
/*				System.out.println(head);*/
			if(head.startsWith(list.get(i))){
				return true;
			}
		}
		return false;
	}
	
	public static String getHead(String head){
		List<String> list=getHeadList();
		for(int i=0;i<list.size();i++){
	    
			if(head.indexOf(list.get(i))>-1){
/*				System.out.println(head);*/
				return list.get(i);
			}
		}
		return null;
	}
}
