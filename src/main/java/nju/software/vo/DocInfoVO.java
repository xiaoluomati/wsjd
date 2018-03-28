package nju.software.vo;


/**
 * Created by zhuding on 2018/3/28.
 */
public class DocInfoVO {

    private String xmlFileName;

    private String jsonName;

    public DocInfoVO(String xmlFileName, String jsonName) {
        this.xmlFileName = xmlFileName;
        this.jsonName = jsonName;
    }

    public String getXmlFileName() {
        return xmlFileName;
    }

    public void setXmlFileName(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }


    public String getJsonName() {
        return jsonName;
    }

    public void setJsonName(String jsonName) {
        this.jsonName = jsonName;
    }
}
