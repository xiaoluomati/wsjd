package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

import nju.software.wsjx.util.StringUtil;



public enum WwEnum {
	SPZ("审判长"),
	RMPSY("人民陪审员"),
	DLSPY("代理审判员"),
	RMSPY("人民审判员"),
	SPY("审判员"),
	DLSJY("代理书记员"),
	DSJY("代书记员"),
	DKSJY("（代）书记员"),
	SJYD("书记员（代）"),
	JXSJY("见习书记员"),
	SJYJ("书记员（兼）"),
	SJY("书记员"),
	SLY("速录员"),
	FGZL("法官助理")
	;
	
	WwEnum(){
		
	}
	private WwEnum(String content){
		this.content = content;
	}
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public static List<String> getWwList() {
		List<String> wwList = new ArrayList<String>();
		for (WwEnum wwEnum : WwEnum.values()) {
			wwList.add(wwEnum.getContent());
		}
		return wwList;
	}
	
	public static boolean HasWw(String ww){
		List<String> list=getWwList();
		for(int i=0;i<list.size();i++){
	    
			if(ww.indexOf(list.get(i))==0){
				return true;
			}
		}
		return false;
	}
	
	public static String getWw(String ww){
		List<String> list=getWwList();
		for(int i=0;i<list.size();i++){
			
				if(ww.indexOf(list.get(i))!=-1){
					//System.out.println(ww);
					return list.get(i);
				}
			
			
		}
		return null;
	}
	public static List<String> getSsdwlist(String ww){
		List<String> list=getWwList();
		String temp = ww;
		List<String> result = new ArrayList<String>();
		for(String s:list){
			if(StringUtil.indexOf(temp,s)>-1){
				result.add(s);
				temp = temp.substring(s.length());
			}
		}
		return result;
	}
	
	public static  String  getSsdwreg(String ww){
		List<String> list=getWwList();
		String temp = ww;
		String result="";
		for(String s:list){
			if(StringUtil.indexOf(temp,s)>-1){
//				result.add(s);
				result = result+s+"|";
				temp = temp.substring(s.length());
			}
		}
		if(!StringUtil.isBlank(result)){
			result = result.substring(0, result.length()-1);
		}
		return result;
	}
	
	public static void main(String arg[]){
		String text="代理审判员";
	}
	
	public static String addBlank(String content){
		for(WwEnum ww:WwEnum.values()){
			if(StringUtil.contains(content, ww.getContent())){
				content = content.replace(ww.getContent(), " "+ww.getContent());
			}
		}
		return content;
	}
	
}

