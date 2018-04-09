package nju.software.preProcess;

import java.util.HashMap;

/**
 * Created by zhuding on 2018/4/10.
 */
public class PreClassifyKeyword {

    private static final String[] GX_KEYWORDS = {};

    private static PreClassifyKeyword instance = new PreClassifyKeyword();

    private PreClassifyKeyword(){
        init();
    }

    private HashMap<String,String[]> map;

    private void init(){
        map.put("", GX_KEYWORDS);
    }

    public static String getType(String content){
        for (String s : instance.map.keySet()) {
            String[] strings = instance.map.get(s);
            //TODO
        }
        return null;
    }

}
