package nju.software.wsjd.model.lawModel;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedHashMap;

/**
 * Created by 69401 on 2018/3/18.
 */
@Document
public class TiaoModel {

    @Field("¿î")
    private LinkedHashMap<String,KuanModel> kuan;

    public LinkedHashMap<String, KuanModel> getKuan() {
        return kuan;
    }

    public void setKuan(LinkedHashMap<String, KuanModel> kuan) {
        this.kuan = kuan;
    }
}
