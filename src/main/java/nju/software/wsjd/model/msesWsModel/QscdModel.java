package nju.software.wsjd.model.msesWsModel;

/**
 * 前审裁定的处理 民事二审
 * Created by zhuding on 2018/3/27.
 */
public class QscdModel {

    private String fymc;//法院名称

    private String ah;//案号

    private String pjfs;//判决方式

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getPjfs() {
        return pjfs;
    }

    public void setPjfs(String pjfs) {
        this.pjfs = pjfs;
    }

    @Override
    public String toString() {
        return "QscdModel{" +
                "fymc='" + fymc + '\'' +
                ", ah='" + ah + '\'' +
                ", pjfs='" + pjfs + '\'' +
                '}';
    }
}
