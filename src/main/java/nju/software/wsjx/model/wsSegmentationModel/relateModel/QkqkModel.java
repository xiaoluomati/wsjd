package nju.software.wsjx.model.wsSegmentationModel.relateModel;

import java.util.List;

/**
 * Created by zhx on 2016/12/15.
 */
public class QkqkModel {
    private String qklb;//前科类别
    private String cfTime;//处罚时间
    private List<String> cfReason;//处罚原因
    private String cfdw;//处罚单位
    private String cfxs;//处罚形式
    private List<String> cfxq;//处罚刑期
    private String xmsfTime;//刑满释放日期

    public String getQklb() {
        return qklb;
    }

    public void setQklb(String qklb) {
        this.qklb = qklb;
    }

    public String getCfTime() {
        return cfTime;
    }

    public void setCfTime(String cfTime) {
        this.cfTime = cfTime;
    }

    public List<String> getCfReason() {
        return cfReason;
    }

    public void setCfReason(List<String> cfReason) {
        this.cfReason = cfReason;
    }

    public String getCfdw() {
        return cfdw;
    }

    public void setCfdw(String cfdw) {
        this.cfdw = cfdw;
    }

    public String getCfxs() {
        return cfxs;
    }

    public void setCfxs(String cfxs) {
        this.cfxs = cfxs;
    }

    public List<String> getCfxq() {
        return cfxq;
    }

    public void setCfxq(List<String> cfxq) {
        this.cfxq = cfxq;
    }

    public String getXmsfTime() {
        return xmsfTime;
    }

    public void setXmsfTime(String xmsfTime) {
        this.xmsfTime = xmsfTime;
    }
}
