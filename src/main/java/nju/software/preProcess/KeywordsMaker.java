package nju.software.preProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhuding on 2018/4/10.
 */
public class KeywordsMaker {

    private HashMap<String, HashMap<String, Integer>> map;

    public KeywordsMaker() {
        this.map = new HashMap<>();
    }

    public void addData(String name, HashMap<String, Integer> keywordNum){
        map.put(name, keywordNum);
    }


    public  HashMap<String, HashMap<String, Integer>> getKeywords(){
        List<String> commonWords = new ArrayList<>();
        HashMap<String, HashMap<String, Integer>> result = new HashMap<>();
        for (String s : map.keySet()) {
            HashMap<String, Integer> keymap = this.map.get(s);
            for (String s1 : keymap.keySet()) {
                if(!commonWords.contains(s1) && isCommon(s, s1)){
                    commonWords.add(s1);
                }
            }
        }
        for (String s : map.keySet()) {
            HashMap<String, Integer> truemap = this.map.get(s);
            for (String commonWord : commonWords) {
                if(truemap.containsKey(commonWord)){
                    truemap.remove(commonWord);
                }
            }
            result.put(s, truemap);
        }
        return result;
    }

    private boolean isCommon(String name, String key){
        for (String s : map.keySet()) {
            if(!name.equals(s)){
                HashMap<String, Integer> keymap = this.map.get(s);
                if(keymap.containsKey(key)){
                    return true;
                }
            }
        }
        return false;
    }



}
