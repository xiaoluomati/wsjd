package nju.software.classify;

import java.util.HashMap;

/**
 * Created by zhuding on 2018/3/20.
 */
public class ParseMap {

    private ParseMap(){
        init();
    }

    private void init() {
        jxHashMap = new HashMap<>();
        jxHashMap.put("管辖","ParseMsgxSegment");
        jxHashMap.put("第一审普通程序", "ParseYsptSegment");
        jxHashMap.put("诉讼参加人","ParseSscjrSegment");
        jxHashMap.put("第二审程序","ParseMsesSegment");
        jxHashMap.put("证据","ParseZjSegment");
        flHashMap = new HashMap<>();
        flHashMap.put("民初","YSClassifier");
        flHashMap.put("民终","ESClassifier");
        flHashMap.put("民辖","");
    }

    private HashMap<String, String>  jxHashMap;

    private HashMap<String, String> flHashMap;

    private static ParseMap parseMap = new ParseMap();

    public static ParseMap getInstance(){
        return parseMap;
    }

    public String getParseClassName(String name){
        return jxHashMap.get(name);
    }

    public String getClassifierName(String name) {return flHashMap.get(name);}

    public static Iterable<String> parseClassNameKeys(){
        return parseMap.jxHashMap.keySet();
    }

    public static Iterable<String> classifierNameKeys(){return parseMap.flHashMap.keySet();}

}
