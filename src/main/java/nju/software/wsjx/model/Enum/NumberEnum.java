package nju.software.wsjx.model.Enum;

import java.util.ArrayList;
import java.util.List;

public enum NumberEnum  {
	A1("1"),
	A2("2"),
	A3("3"),
    A4("4"),
    A5("5"),
    A6("6"),
    A7("7"),
    A8("8"),
    A9("9"),
    A0("0"),
    C1("一"),
    C2("二"),
    C3("三"),
    C4("四"),
    C5("五"),
    C6("六"),
    C7("七"),
    C8("八"),
    C9("九"),
    C10("十");

    
	NumberEnum(){
		
	}
	private NumberEnum(String content){
		this.content = content;
	}
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static List<String> getNumberList() {
		List<String> numberList = new ArrayList<String>();
		for (NumberEnum numberEnum : NumberEnum.values()) {
			numberList.add(numberEnum.getContent());
		}
		return numberList;
	}
	
	public static boolean HasNumber(String content){
		List<String> list=getNumberList();
		for(int i=0;i<list.size();i++){
	    
			if(content.indexOf(list.get(i))<4&&content.indexOf(list.get(i))>-1){
				return true;
			}
		}
		return false;
	}

}

