package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.util.List;

/**
 * Created by zhuding on 2018/4/4.
 */
public class GXClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel) {
        this.ssjl = wsModel.getWsssjlSegment();
        this.cpjg = wsModel.getWscpjgSegment();
        this.cpgc = wsModel.getWscpfxgcSegment();
        List<String> esList = DocType.getTypeList(BaseClassifier.GX_PREFIX);
        Class<? extends GXClassifier> clz = getClass();
        return getType(esList, clz, this);
    }

    @Override
    public String getParseRuleName() {
        return ParseMap.getInstance().getParseClassName("管辖");
    }

    /**
     * 民事裁定书(不服管辖裁定上诉案件用)
     * @return
     */
    private boolean isBFGXCDSS(){
        boolean wccd = cpjg.contains("驳回上诉") && cpjg.contains("维持原裁定");
        String pattern1 = "由"+CHINESE+"管辖";
        String pattern2 = "移送"+CHINESE+"人民法院处理";
        boolean cxcd = matchCpjg(pattern1) || matchCpjg(pattern2);
        return wccd || cxcd;
    }

    /**
     * 民事裁定书(管辖权异议用)
     * @return
     */
    private boolean isGXQYY(){
        String pattern1 = "管辖权"+CHINESE+"异议";
        String pattern2 = "移送"+CHINESE+"处理";
        String pattern3 = "驳回"+CHINESE+pattern1;
        return matchSsjl(pattern1) && matchCpjg(pattern2) && matchCpjg(pattern3);
    }

    /**
     * 民事裁定书(上级法院移交下级法院审理用)
     * @return
     */
    private boolean isSJYJXJ(){
        String pattern1 = "报请"+CHINESE+"批准";
        String pattern2 = "由"+CHINESE+"审理";
        return matchCpgc(pattern1) && matchCpjg(pattern2);
    }

    /**
     * 民事裁定书(受移送人民法院报请指定管辖案件用)
     * @return
     */
    private boolean isSBQZDGX(){
        String pattern1 = "报请"+CHINESE+"指定管辖";
        String pattern2 = "由"+CHINESE+"审理";
        return matchSsjl(pattern1) && matchCpjg(pattern2);
    }

    /**
     * 民事裁定书(小额诉讼程序管辖权异议用)
     * @return
     */
    private boolean isXSSSGXYY(){
        String pattern1 = "管辖权"+CHINESE+"异议";
        String pattern2 = "异议成立.*移送"+CHINESE+"处理";
        String pattern3 = "驳回"+CHINESE+"异议";
        return matchSsjl(pattern1) && (matchCpjg(pattern2) || matchCpjg(pattern3));
    }

    /**
     * 民事裁定书(依报请提级管辖用)
     * @return
     */
    private boolean isBQTJGX(){
        return (cpgc.contains("提级管辖") || ssjl.contains("提级管辖")) && cpjg.contains("由本院审理");
    }

    /**
     * 民事裁定书(依职权提级管辖用)
     * todo 法律
     * @return
     */
    private boolean isZQTJGX(){
        return (cpgc.contains("提级管辖") || ssjl.contains("提级管辖")) && cpjg.contains("由本院审理");
    }

    /**
     * 民事裁定书(依职权移送管辖用)
     * @return
     */
    private boolean isZQYSGX(){
        String pattern2 = "移送"+CHINESE+"处理";
        return matchCpjg(pattern2);
    }

    /**
     * 民事裁定书(因管辖权争议报请指定管辖案件用)
     * @return
     */
    private boolean isBQZDGX(){
        String pattern1 = "管辖权"+CHINESE+"争议";
        String pattern2 = "指定管辖";
        return matchSsjl(pattern1) && matchSsjl(pattern2) && cpjg.contains("移送");
    }

//
    /**
     * 民事裁定书(有管辖权人民法院报请指定管辖案件用)
     * @return
     */
    private boolean isYGXQBQZD(){
        String pattern2 = "由"+CHINESE+"审理";
        return matchCpjg(pattern2);
    }
}
