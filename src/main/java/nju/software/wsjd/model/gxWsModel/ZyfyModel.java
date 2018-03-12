package nju.software.wsjd.model.gxWsModel;

/**
 * Created by zhuding
 * 争议法院 管辖用
 */
public class ZyfyModel {

    private String fymc;//法院名称

    private String larq;//立案日期

    private String ah;//案号

    private String ay;//案由

    private String aydh;//案由代码

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getLarq() {
        return larq;
    }

    public void setLarq(String larq) {
        this.larq = larq;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getAydh() {
        return aydh;
    }

    public void setAydh(String aydh) {
        this.aydh = aydh;
    }

    @Override
    public String toString() {
        return "ZyfyModel{" +
                "fymc='" + fymc + '\'' +
                ", larq='" + larq + '\'' +
                ", ah='" + ah + '\'' +
                ", ay='" + ay + '\'' +
                ", aydh='" + aydh + '\'' +
                '}';
    }
}
