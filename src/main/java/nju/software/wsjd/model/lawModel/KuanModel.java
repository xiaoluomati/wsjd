package nju.software.wsjd.model.lawModel;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by 69401 on 2018/3/18.
 */
@Document
public class KuanModel {

    @Field("ÄÚÈÝ")
    private String content;

    @Field("Ïî")
    private List<String> xiang;

    @Field("¹Ø¼ü´Ê")
    private String keywords;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getXiang() {
        return xiang;
    }

    public void setXiang(List<String> xiang) {
        this.xiang = xiang;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
