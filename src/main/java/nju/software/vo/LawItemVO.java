package nju.software.vo;

import java.util.Map;

/**
 * Created by away on 2018/3/28.
 */
public class LawItemVO {

    // 法律名称, 如 xxx 法
    private String name;

    // 法律条款名称及其内容, 如 map.put("第二百零四条第一款", ......);
    // 先支持到款, 列出款式项的内容也列出
    private Map<String, String> lawMap;

    public LawItemVO(String name, Map<String, String> lawMap) {
        this.name = name;
        this.lawMap = lawMap;
    }

    public LawItemVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getLawMap() {
        return lawMap;
    }

    public void setLawMap(Map<String, String> lawMap) {
        this.lawMap = lawMap;
    }
}
