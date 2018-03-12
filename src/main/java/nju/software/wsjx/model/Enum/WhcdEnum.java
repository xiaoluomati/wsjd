package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * 诉讼参与人文化程度枚举类
 * @author LXY
 *
 */
public enum WhcdEnum {
	WM("文盲"),
	XXWH("小学文化"),
	CZWH("初中文化"),
	ZZWH("中专文化"),
	GZWH("高中文化"),
	DZWH("大专文化"),
	BKWH("本科文化"),
	DXBKWH("大学本科文化"),
	DXWH("大学文化");
	WhcdEnum(){
		
	}
	private WhcdEnum(String content){
		this.content = content;
	}
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public static List<String> getWhcdList(){
		List<String> whcd = new ArrayList<String>();
		for(WhcdEnum whcdEnum : WhcdEnum.values()){
			whcd.add(whcdEnum.getContent());
		}
		return whcd;
	}
	
	public static boolean HasWhcd(String whcd){
		List<String> list = getWhcdList();
		for(int i=0;i<list.size();i++){
			if(whcd.indexOf(list.get(i))==0){
				return true;
			}
		}
		return false;
	}
	
	public static String getWhcd(String whcd){
		List<String> list = getWhcdList();
		for(int i=0;i<list.size();i++){
			if(whcd.indexOf(list.get(i))!=-1){
				return list.get(i);
			}
		}
		return null;
	}
}
