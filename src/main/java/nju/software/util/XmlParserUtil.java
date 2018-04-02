package nju.software.util;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuding on 2018/3/27.
 */
public class XmlParserUtil {

    private String filename;

    private Element root;

    public XmlParserUtil(String filename) {
        this.filename = filename;
        init();
    }

    private void init(){
        try {
            SAXBuilder builder = new SAXBuilder();//实例JDOM解析器
            Document document = builder.build(new File(filename));//读取xml文件
            root = document.getRootElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAh(){
        Element child = root.getChild("WS").getChild("AH");
        return child==null?null:child.getAttribute("value").getValue();
    }

    public List<String> getDsr(){
        List<String> strings = new ArrayList<>();
        for (Object o : root.getChild("SSCYRQJ").getChildren()) {
            Element sscyr = (Element) o;
            Element ssdw = sscyr.getChild("SSDW");
            Element sssf = sscyr.getChild("SSSF");
            if (ssdw != null && sssf != null){
                strings.add(ssdw.getAttribute("value").getValue());
                String s = sssf.getAttribute("value").getValue();
                if(s.contains("（") && s.contains("）")){
                    strings.add(s.substring(s.indexOf("（") + 1, s.indexOf("）")));
                }else{
                    strings.add(s);
                }
            }

        }
        return strings;
    }

    public Map<String, String> getCpfxgcQt(){
        Map<String, String> map = new HashMap<>();
        for (Object cpfxgc : root.getChild("CPFXGC").getChildren()) {
            Element element = (Element)cpfxgc;
            if(!element.getName().equals("FLFTMC")){
                map.put(element.getAttribute("nameCN").getValue(), element.getAttribute("value").getValue());
            }
        }
        return map;
    }

    public Map<String, String> getSsjl(){
        return getMapResult("SSJL");
    }

    public Map<String, String> getPjjg(){
        return getMapResult("CPJG");
    }

    public Map<String, String> getAjjbqk(){
        return getMapResult("AJJBQK");
    }

    private Map<String, String> getMapResult(String name){
        HashMap<String, String> map = new HashMap<>();
        List ssjl = root.getChild(name).getChildren();
        for (int i = 0; i < ssjl.size(); i++) {
            Element element = (Element)ssjl.get(i);
            if(element.getAttribute("nameCN") != null && element.getAttribute("value")!=null)
                map.put(element.getAttribute("nameCN").getValue(), element.getAttribute("value").getValue());
            if(element.getChildren() != null && !element.getChildren().isEmpty()){
                for (int j = 0; j < element.getChildren().size(); j++) {
                    Element e = (Element)element.getChildren().get(j);
                    if(e.getAttribute("nameCN") != null && e.getAttribute("value")!=null)
                        map.put(e.getAttribute("nameCN").getValue(), e.getAttribute("value").getValue());
                }
            }
        }
        return map;
    }

//    public static void main(String[] args) {
//        XmlParserUtil xmlParserUtil = new XmlParserUtil("xml/关明华与王正峰所有权确认纠纷二审民事裁定书.xml");
//    }
}
