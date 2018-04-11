package nju.software.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuding on 2018/4/11.
 */
public class TemplateLawVO {

    private String name;

    private List<String> items;

    public TemplateLawVO() {
        this.items = new ArrayList<>();
    }

    public TemplateLawVO(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getItems() {
        return items;
    }

    public void addItem(String item){
        this.items.add(item);
    }

    public boolean containsItem(String item){
        return this.items.contains(item);
    }

    public boolean merge(TemplateLawVO templateLawVO){
        if(this.name.equals(templateLawVO.getName())){
            for (String s : templateLawVO.getItems()) {
                if(!this.items.contains(s)){
                    this.addItem(s);
                }
            }
            return true;
        }
        return false;
    }

    public static List<TemplateLawVO> mergeList(List<TemplateLawVO> templateLawVOS){
        List<TemplateLawVO> result = new ArrayList<>();
        if(templateLawVOS.size() == 0){
            return result;
        }
        result.add(templateLawVOS.get(0));
        for (int i = 1; i < templateLawVOS.size(); i++) {
            boolean merged = false;
            for (TemplateLawVO templateLawVO : result) {
                if(templateLawVO.merge(templateLawVOS.get(i))){
                    merged = true;
                    break;
                }
            }
            if(!merged){
                result.add(templateLawVOS.get(i));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "TemplateLawVO{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
