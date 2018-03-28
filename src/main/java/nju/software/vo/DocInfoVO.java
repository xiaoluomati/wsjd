package nju.software.vo;


/**
 * Created by zhuding on 2018/3/28.
 */
public class DocInfoVO {

    private String xmlFileName;

    private String jsonFileName;

    public DocInfoVO(String xmlFileName, String jsonFileName) {
        this.xmlFileName = xmlFileName;
        this.jsonFileName = jsonFileName;
    }

    public String getXmlFileName() {
        return xmlFileName;
    }

    public void setXmlFileName(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public String getJsonFileName() {
        return jsonFileName;
    }

    public void setJsonFileName(String jsonFileName) {
        this.jsonFileName = jsonFileName;
    }
}
