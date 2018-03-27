package nju.software.wsjd.classify;

import java.util.HashMap;

/**
 * Created by zhuding on 2018/3/20.
 */
public class ParseMap {

    private ParseMap(){
        init();
    }

    private void init() {
        hashMap = new HashMap<>();
        hashMap.put("管辖","ParseMsgxSegment");
        hashMap.put("第一审普通程序", "ParseYsptSegment");
        hashMap.put("诉讼参加人","ParseSscjrSegment");
        hashMap.put("第二审程序","ParseMsesSegment");
    }

    private HashMap<String, String>  hashMap;

    private static ParseMap parseMap = new ParseMap();

    public static ParseMap getInstance(){
        return parseMap;
    }

    public String get(String name){
        return hashMap.get(name);
    }

    public Iterable<String> namelist(){
        return hashMap.keySet();
    }

}
