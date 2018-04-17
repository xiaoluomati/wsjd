package nju.software.classify;

import java.util.HashMap;

/**
 * Created by zhuding on 2018/3/20.
 */
public class ParseMap {

    public static final String NOT_DETERMINED = "不能确定";

    public static final String DEFAULT_PARSECLASS = "第一审普通程序";

    private ParseMap(){
        init();
    }

    private void init() {
        map = new HashMap<>();
        map.put("管辖",new String[]{"ParseMsgxSegment", "GXClassifier"});
        map.put("第一审普通程序", new String[]{"ParseYsptSegment","YSClassifier"});
        map.put("第二审程序", new String[]{"ParseMsesSegment", "ESClassifier"});
        map.put("诉讼参加人", new String[]{"ParseSscjrSegment","SSCJRClassifier"});
        map.put("证据", new String[]{"ParseZjSegment","ZJClassifier"});
        map.put("保全和先予执行", new String[]{"",""});
        map.put("公益诉讼", new String[]{"",""});
        map.put("公示催告程序", new String[]{"",""});
        map.put("执行异议之诉", new String[]{"",""});
        map.put("督促程序", new String[]{"",""});
        map.put("第三人撤销之诉", new String[]{"",""});
        map.put("简易程序", new String[]{"",""});
        map.put("简易程序中的小额诉讼", new String[]{"",""});
        map.put("诉讼费用", new String[]{"",""});
    }

    private HashMap<String, String[]> map;

    private static ParseMap parseMap = new ParseMap();

    public static ParseMap getInstance(){
        return parseMap;
    }

    public static Iterable<String> classnameKeys(){return parseMap.map.keySet();}

    public String getParseName(String classname){
        return map.get(classname)[0];
    }

    public String getClassifyName(String classname){
        return map.get(classname)[1];
    }

}
