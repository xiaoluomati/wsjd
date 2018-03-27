package nju.software.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by zhuding on 2018/3/27.
 */
public class JsonParserUtil {

    private String jsonString;

    private JsonObject jsonElement;

    public JsonParserUtil(String jsonString){
        this.setJsonString(jsonString);
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
        init();
    }

    private void init(){
        JsonParser parser=new JsonParser();
        JsonObject object=(JsonObject) parser.parse(jsonString);
        this.jsonElement = object.entrySet().iterator().next().getValue().getAsJsonObject();
    }

    public List<String> getSsjlRequirements(){
        return getListKeyPoints("诉讼记录");
    }

    public List<String> getPjjgRequirements(){
        return getListKeyPoints("判决结果");
    }

    public List<String> getDsrRequirements(){
        return getListKeyPoints("当事人");
    }

    public List<String> getAjjbqkRequirements(){
        return getListKeyPoints("案件基本情况");
    }

    public String getAhRequirements(){
        return jsonElement.get("案号").getAsString();
    }

    public Map<String, List<String>> getCpfxgcRequirements(){
        Map<String, List<String>> map = new HashMap<>();
        JsonObject object = jsonElement.get("裁判分析过程").getAsJsonObject();
        Iterator<Map.Entry<String, JsonElement>> iterator = object.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, JsonElement> next = iterator.next();
            List<String> strings = new ArrayList<>();
            for (JsonElement element : next.getValue().getAsJsonArray()) {
                strings.add(element.getAsString());
            }
            map.put(next.getKey(), strings);
        }
        return map;
    }

    private List<String> getListKeyPoints(String name){
        List<String> strings = new ArrayList<>();
        JsonArray array = jsonElement.get(name).getAsJsonArray();
        for (JsonElement element : array) {
            strings.add(element.getAsString());
        }
        return strings;
    }





//    public static void main(String[] args) {
//        String string = "{\n" +
//                "  \"民事裁定书(依职权提级管辖用)\": {\n" +
//                "    \"诉讼记录\": [\n" +
//                "      \"原告\",\n" +
//                "      \"被告\",\n" +
//                "      \"案由\",\n" +
//                "      \"立案日期\",\n" +
//                "      \"立案法院\"\n" +
//                "    ],\n" +
//                "    \"判决结果\": [],\n" +
//                "    \"案号\": \"民辖\",\n" +
//                "    \"当事人\": [\n" +
//                "      \"原告\",\n" +
//                "      \"被告\"\n" +
//                "    ],\n" +
//                "    \"案件基本情况\": [\n" +
//                "      \"原告诉称段\"\n" +
//                "    ],\n" +
//                "    \"裁判分析过程\": {\n" +
//                "      \"法律法条引用\": [\n" +
//                "        \"《中华人民共和国民事诉讼法》第三十八条第一款\"\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  }\n" +
//                "}";
//        JsonParserUtil jsonParserUtil = new JsonParserUtil(string);
//
//
//    }


}
