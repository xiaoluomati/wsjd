package nju.software.wsjd.model.lawModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedHashMap;

/**
 * Created by 69401 on 2018/3/18.
 */
@Document
public class LawModel {

    @Id
    private String _id;

    @Indexed
    @Field("法律名称")
    private String lawname;//法律名称

    @Field("法律内容")
    private LinkedHashMap<String,TiaoModel> content;//法律内容

    @Field("时效性")
    private String timelimit;//法律时效性

    @Field("效力级别")
    private String level;//法律效力级别；

    @Field("发布日期")
    private String publishtime;//发布日期

    @Field("实施日期")
    private String starttime;//实施日期

    public LawModel() {
    }

    public LawModel(String name) {
        this.lawname = name;
    }

    public String getName() {
        return lawname;
    }

    public void setName(String name) {
        this.lawname = name;
    }

    public LinkedHashMap<String,TiaoModel> getTiao() {
        return content;
    }

    public void setTiao(LinkedHashMap<String,TiaoModel> tiao) {
        this.content = tiao;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

//    @Override
//    public String toString() {
//        String strtiao = "";
//        Set<String> keys = this.content.keySet();
//        for(String key :keys){
//            strtiao += key+":"+this.content.get(key).toString()+"，";
//        }
//        return "Model.Law{" +
//                "name='" + lawname + '\'' +
//                ", tiao=" +strtiao+
//                ", timelimit='" + timelimit + '\'' +
//                ", level='" + level + '\'' +
//                ", publishtime='" + publishtime + '\'' +
//                ", starttime='" + starttime + '\'' +
//                '}';
//    }
}