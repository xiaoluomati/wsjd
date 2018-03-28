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
    private String lawname;

    @Field("法律内容")
    private LinkedHashMap<String,TiaoModel> content;

    @Field("时效性")
    private String timelimit;

    @Field("效力级别")
    private String level;

    @Field("发布日期")
    private String publishtime;

    @Field("实施日期")
    private String starttime;

    public String getLawname() {
        return lawname;
    }

    public void setLawname(String lawname) {
        this.lawname = lawname;
    }

    public LinkedHashMap<String, TiaoModel> getContent() {
        return content;
    }

    public void setContent(LinkedHashMap<String, TiaoModel> content) {
        this.content = content;
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
}
