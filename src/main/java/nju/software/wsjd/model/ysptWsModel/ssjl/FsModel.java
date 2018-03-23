package nju.software.wsjd.model.ysptWsModel.ssjl;

/**
 * 反诉 第一审使用
 * Created by zhuding on 2018/3/23.
 */
public class FsModel {
    private String fsrq;//反诉日期

    private String yg;//原告

    private String bg;//被告

    public String getFsrq() {
        return fsrq;
    }

    public void setFsrq(String fsrq) {
        this.fsrq = fsrq;
    }

    public String getYg() {
        return yg;
    }

    public void setYg(String yg) {
        this.yg = yg;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    @Override
    public String toString() {
        return "FsModel{" +
                "fsrq='" + fsrq + '\'' +
                ", yg='" + yg + '\'' +
                ", bg='" + bg + '\'' +
                '}';
    }
}
