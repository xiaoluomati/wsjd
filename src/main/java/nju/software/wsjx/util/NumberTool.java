package nju.software.wsjx.util;

import java.util.HashMap;

public class NumberTool {
	 //数字位  
    public static String[] chnNumChar = {"零","一","二","三","四","五","六","七","八","九"};  
    public static char[] chnNumChinese = {'零','一','二','三','四','五','六','七','八','九'};  
    //节权位  
    public static String[] chnUnitSection = {"","万","亿","万亿"};    
    //权位  
    public static String[] chnUnitChar = {"","十","百","千"};  
    public static HashMap intList = new HashMap();  
    static{  
        for(int i=0;i<chnNumChar.length;i++){  
            intList.put(chnNumChinese[i], i);  
        }  
          
        intList.put('十',10);  
        intList.put('百',100);  
        intList.put('千', 1000);  
    }  
}
