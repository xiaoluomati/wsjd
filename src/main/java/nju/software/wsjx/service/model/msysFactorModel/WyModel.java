package nju.software.wsjx.service.model.msysFactorModel;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.util.List;

public class WyModel {

    /**
     * 参考文书抽取的model，自行添加需要抽取的要素属性
     */

    /**
     * 共用项目
     */
    private String ah;                  //一 案号
    private List<JSONObject> bg;              //三 被告
//    private String csny;                //四 出生年月
//    private String mz;                  //五 民族
//    private String zz;                  //六 住址
    private String qfqssj;              //七 欠费起始时间
    private String wyfwf;               //八 物业服务费
    private String jdssf;               //九 机电设施费
    private String rzsj;                //十入住时间
    private String fwmj;                //十一 房屋面积
    private String sqtjsj;              //十二 诉前调解时间
    private String ssf;                 //十三 诉讼费

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public List<JSONObject> getBg() {
        return bg;
    }

    public void setBg(List<JSONObject> bg) {
        this.bg = bg;
    }

//    public String getXb() {
//        return xb;
//    }
//
//    public void setXb(String xb) {
//        this.xb = xb;
//    }
//
//    public String getCsny() {
//        return csny;
//    }
//
//    public void setCsny(String csny) {
//        this.csny = csny;
//    }
//
//    public String getMz() {
//        return mz;
//    }
//
//    public void setMz(String mz) {
//        this.mz = mz;
//    }
//
//    public String getZz() {
//        return zz;
//    }
//
//    public void setZz(String mz) {
//        this.zz = zz;
//    }

    public String getQfqssj() {
        return qfqssj;
    }

    public void setQfqssj(String qfqjsj) {
        this.qfqssj = qfqjsj;
    }

    public String getWyfwf() {
        return wyfwf;
    }

    public void setWyfwf(String wyfwf) {
        this.wyfwf = wyfwf;
    }

    public String getJdssf() {
        return jdssf;
    }

    public void setJdssf(String jdssf) {
        this.jdssf = jdssf;
    }

    public String getrzsj() {
        return rzsj;
    }

    public void setRzsj(String rzsj) {
        this.rzsj = rzsj;
    }

    public String getFwmj() {
        return fwmj;
    }

    public void setFwmj(String fwmj) {
        this.fwmj = fwmj;
    }

    public String getSqtjsj() {
        return sqtjsj;
    }

    public void setSqtjsj(String sqtjsj) {
        this.sqtjsj = sqtjsj;
    }

    public String getSsf() {
        return ssf;
    }

    public void setSsf(String ssf) {
        this.ssf = ssf;
    }

}
