package nju.software.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Created by away on 2018/3/27.
 */
public class Test {

    public static void main(String[] args) {
        String json = "{\"民事裁定书(保全或者先予执行裁定复议用)\":{\"诉讼记录\":[\"案由\",\"生效裁定\",\"申请人\",\"申请日期\"],\"判决结果\":[],\"案号\":\"\",\"当事人\":[\"复议申请人\",\"被申请人\"],\"案件基本情况\":[\"原告诉称段\"],\"裁判分析过程\":{\"法律法条引用\":[\"《中华人民共和国民事诉讼法》第一百零八条\",\"《最高人民法院关于适用〈中华人民共和国民事诉讼法〉的解释》第一百七十一条\"]}}}";
        Gson gson = new Gson();
        Map<String, Map> val = gson.fromJson(json, new TypeToken<Map<String, Map>>() {}.getType());
        System.out.println(val.values());
    }
}
