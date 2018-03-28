package nju.software.vo;

import java.util.List;

/**
 * Created by away on 2018/3/28.
 */
public class CheckInfoVO {

    private List<CheckInfoItemVO> WS;
    private List<CheckInfoItemVO> SSCYR;
    private List<CheckInfoItemVO> SSJL;
    private List<CheckInfoItemVO> AJJBQK;
    private List<CheckInfoItemVO> CPFXGC;
    private List<CheckInfoItemVO> CPJG;
    private List<CheckInfoItemVO> WW;
    private List<CheckInfoItemVO> FL;

    public CheckInfoVO() {
    }

    public List<CheckInfoItemVO> getWS() {
        return WS;
    }

    public void setWS(List<CheckInfoItemVO> WS) {
        this.WS = WS;
    }

    public List<CheckInfoItemVO> getSSCYR() {
        return SSCYR;
    }

    public void setSSCYR(List<CheckInfoItemVO> SSCYR) {
        this.SSCYR = SSCYR;
    }

    public List<CheckInfoItemVO> getSSJL() {
        return SSJL;
    }

    public void setSSJL(List<CheckInfoItemVO> SSJL) {
        this.SSJL = SSJL;
    }

    public List<CheckInfoItemVO> getAJJBQK() {
        return AJJBQK;
    }

    public void setAJJBQK(List<CheckInfoItemVO> AJJBQK) {
        this.AJJBQK = AJJBQK;
    }

    public List<CheckInfoItemVO> getCPFXGC() {
        return CPFXGC;
    }

    public void setCPFXGC(List<CheckInfoItemVO> CPFXGC) {
        this.CPFXGC = CPFXGC;
    }

    public List<CheckInfoItemVO> getCPJG() {
        return CPJG;
    }

    public void setCPJG(List<CheckInfoItemVO> CPJG) {
        this.CPJG = CPJG;
    }

    public List<CheckInfoItemVO> getWW() {
        return WW;
    }

    public void setWW(List<CheckInfoItemVO> WW) {
        this.WW = WW;
    }

    public List<CheckInfoItemVO> getFL() {
        return FL;
    }

    public void setFL(List<CheckInfoItemVO> FL) {
        this.FL = FL;
    }

    @Override
    public String toString() {
        return "CheckInfoVO{" +
                "WS=" + WS +
                ",\n SSCYR=" + SSCYR +
                ",\n SSJL=" + SSJL +
                ",\n AJJBQK=" + AJJBQK +
                ",\n CPFXGC=" + CPFXGC +
                ",\n CPJG=" + CPJG +
                ",\n WW=" + WW +
                ",\n FL=" + FL +
                '}';
    }
}
