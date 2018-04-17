package nju.software.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/3/28.
 */
public class CheckInfoVO {

    private Map<String, Integer> errorTypeMap;
    private Map<Integer, Integer> errorNumByLevel;

    private List<CheckInfoItemVO> WS;
    private List<CheckInfoItemVO> SSCYR;
    private List<CheckInfoItemVO> SSJL;
    private List<CheckInfoItemVO> AJJBQK;
    private List<CheckInfoItemVO> CPFXGC;
    private List<CheckInfoItemVO> CPJG;
    private List<CheckInfoItemVO> WW;
    private List<CheckInfoItemVO> FL;

    public CheckInfoVO() {
        this.errorTypeMap = new HashMap<>();
        this.errorTypeMap.put(ErrorType.JGQS.getName(), 0);
        this.errorTypeMap.put(ErrorType.SSWBTY.getName(), 0);
        this.errorTypeMap.put(ErrorType.YSCW.getName(), 0);
        this.errorTypeMap.put(ErrorType.YSQS.getName(), 0);

        this.errorNumByLevel = new HashMap<>();
        errorNumByLevel.put(1, 0);
        errorNumByLevel.put(2, 0);
        errorNumByLevel.put(3, 0);
}

    public Map<String, Integer> getErrorTypeMap() {
        return errorTypeMap;
    }

    private void addToMap(List<CheckInfoItemVO> checkInfoItemVOS){
        for (CheckInfoItemVO checkInfoItemVO : checkInfoItemVOS) {
            String errorTypeName = checkInfoItemVO.getErrorTypeName();
            errorTypeMap.put(errorTypeName, 1 + errorTypeMap.get(errorTypeName));

            int num = checkInfoItemVO.getErrorLevel().getNum();
            errorNumByLevel.put(num, 1 + errorNumByLevel.get(num));
        }
    }

    public List<CheckInfoItemVO> getWS() {
        return WS;
    }

    public void setWS(List<CheckInfoItemVO> WS) {
        this.WS = WS;
        this.addToMap(getWS());
    }

    public List<CheckInfoItemVO> getSSCYR() {
        return SSCYR;
    }

    public void setSSCYR(List<CheckInfoItemVO> SSCYR) {
        this.SSCYR = SSCYR;
        this.addToMap(getSSCYR());
    }

    public List<CheckInfoItemVO> getSSJL() {
        return SSJL;
    }

    public void setSSJL(List<CheckInfoItemVO> SSJL) {
        this.SSJL = SSJL;
        this.addToMap(getSSJL());
    }

    public List<CheckInfoItemVO> getAJJBQK() {
        return AJJBQK;
    }

    public void setAJJBQK(List<CheckInfoItemVO> AJJBQK) {
        this.AJJBQK = AJJBQK;
        this.addToMap(getAJJBQK());
    }

    public List<CheckInfoItemVO> getCPFXGC() {
        return CPFXGC;
    }

    public void setCPFXGC(List<CheckInfoItemVO> CPFXGC) {
        this.CPFXGC = CPFXGC;
        this.addToMap(getCPFXGC());
    }

    public List<CheckInfoItemVO> getCPJG() {
        return CPJG;
    }

    public void setCPJG(List<CheckInfoItemVO> CPJG) {
        this.CPJG = CPJG;
        this.addToMap(getCPJG());
    }

    public List<CheckInfoItemVO> getWW() {
        return WW;
    }

    public void setWW(List<CheckInfoItemVO> WW) {
        this.WW = WW;
        this.addToMap(getWW());
    }

    public List<CheckInfoItemVO> getFL() {
        return FL;
    }

    public void setFL(List<CheckInfoItemVO> FL) {
        this.FL = FL;
        this.addToMap(getFL());
    }

    public Map<Integer, Integer> getErrorNumByLevel() {
        return errorNumByLevel;
    }

    @Override
    public String toString() {
        return "CheckInfoVO{" +
                "errorTypeMap=" + errorTypeMap +
                ", WS=" + WS +
                ", SSCYR=" + SSCYR +
                ", SSJL=" + SSJL +
                ", AJJBQK=" + AJJBQK +
                ", CPFXGC=" + CPFXGC +
                ", CPJG=" + CPJG +
                ", WW=" + WW +
                ", FL=" + FL +
                '}';
    }
}
