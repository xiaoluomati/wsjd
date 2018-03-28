package nju.software.vo;


/**
 * Created by zhuding on 2018/3/28.
 */
public class DocInfoVO {

    private String xmlFileName;

    private String jsonString;

    public DocInfoVO(String xmlFileName, String jsonString) {
        this.xmlFileName = xmlFileName;
        this.jsonString = jsonString;
    }

    public String getXmlFileName() {
        return xmlFileName;
    }

    public void setXmlFileName(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }


    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
