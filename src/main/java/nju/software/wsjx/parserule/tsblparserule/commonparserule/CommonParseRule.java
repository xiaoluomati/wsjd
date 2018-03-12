package nju.software.wsjx.parserule.tsblparserule.commonparserule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonParseRule {

	public static Map<String, String> parseCommon(List<String> paragraphs){
		Map<String, String> map=new HashMap<String, String>();
		boolean fy=false;
		for(String string:paragraphs){
			String split[]=string.split("[:：]");
			if(split[0].contains("时间")&&!map.containsKey("ktsj")){
			    //解析开庭时间
				map.put("ktsj", split[1]);
			}
			else if(split[0].contains("地点")&&!map.containsKey("ktdd")){
				//解析开庭地点
				map.put("ktdd", split[1]);
			}
			else if(split[0].contains("法院")&&split[0].contains("fy")){
				//解析法院名
				map.put("fy", split[0]);
				fy=true;
			}
			else if(fy){
				//解析文书名称
				map.put("wsmc", split[0]);
				fy=false;
			}
		}
		return map;
	}
}
