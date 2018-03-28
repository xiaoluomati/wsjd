package nju.software.wsjd.model.lawModel;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by 69401 on 2018/3/18.
 */
@Document
public class KuanModel {

    @Field("内容")
    private String content;

    @Field("项")
    private List<String> xiang;

    @Field("关键字")
    private String keywords;

    public KuanModel() {
    }

    public KuanModel(KuanModel kuan) {
        this.content = kuan.getContent();
        this.xiang = kuan.getXiang();
        this.keywords = kuan.getKeywords();
    }

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

//    @Override
//    public String toString() {
//        String strxiang = "";
////        Set<String> keys = this.xiang.keySet();
////        if ()
//        for(String key :this.xiang){
//            strxiang += key+"，";
//        }
//        return "Model.Kuan{" +
//                "content='" + content + '\'' +
//                ", Xiang=" + strxiang +
//                ", Keywords='" + keywords + '\'' +
//                '}';
//    }

}
