package nju.software.vo;

import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/3/28.
 */
public class LawItemVO {

    // 法律名称, 如 xxx 法
    private String name;

    // 法律条款名称及其内容, 如 map.put("第二百零四条第一款", ......);
    private List<Map> lawList;

    public LawItemVO(String name, List<Map> lawList) {
        this.name = name;
        this.lawList = lawList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map> getLawList() {
        return lawList;
    }

    public void setLawList(List<Map> lawList) {
        this.lawList = lawList;
    }
}
