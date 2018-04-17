
package nju.software.classify;

import nju.software.preProcess.PreClassifyAH;
import nju.software.preProcess.PreClassifyLaws;
import nju.software.vo.DocType;
import nju.software.vo.TemplateLawVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

import java.util.List;

/**
 * Created by zhuding on 2018/4/4.
 */
public class GXClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.GX_PREFIX, ah, wsModel);
    }

    /**
     * 民事裁定书(不服管辖裁定上诉案件用)
     * @return
     */
    protected boolean isBFGXCDSS(){
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
    protected boolean isGXQYY(){
        String pattern1 = "管辖权"+CHINESE+"异议";
        String pattern2 = "移送"+CHINESE+"处理";
        String pattern3 = "驳回"+CHINESE+pattern1;
        return matchSsjl(pattern1) && matchCpjg(pattern2) && matchCpjg(pattern3);
    }

    /**
     * 民事裁定书(上级法院移交下级法院审理用)
     * @return
     */
    protected boolean isSJYJXJ(){
        String pattern1 = "报请"+CHINESE+"批准";
        String pattern2 = "由"+CHINESE+"审理";
        return matchCpgc(pattern1) && matchCpjg(pattern2);
    }

    /**
     * 民事裁定书(受移送人民法院报请指定管辖案件用)
     * @return
     */
    protected boolean isSBQZDGX(){
        String pattern1 = "报请"+CHINESE+"指定管辖";
        String pattern2 = "由"+CHINESE+"审理";
        return matchSsjl(pattern1) && matchCpjg(pattern2) && ssjl.indexOf("一案") == ssjl.lastIndexOf("一案");
    }

    /**
     * 民事裁定书(小额诉讼程序管辖权异议用)
     * @return
     */
    protected boolean isXSSSGXYY(){
        String pattern1 = "管辖权"+CHINESE+"异议";
        String pattern2 = "异议成立.*移送"+CHINESE+"处理";
        String pattern3 = "驳回"+CHINESE+"异议";
        return matchSsjl(pattern1) && (matchCpjg(pattern2) || matchCpjg(pattern3));
    }

    /**
     * 民事裁定书(依报请提级管辖用)
     * @return
     */
    protected boolean isBQTJGX(){
        return (cpgc.contains("提级管辖") || ssjl.contains("提级管辖")) && cpjg.contains("由本院审理");
    }

    /**
     * 民事裁定书(依职权提级管辖用)
     * todo 法律
     * @return
     */
    protected boolean isZQTJGX(){
        return (cpgc.contains("提级管辖") || ssjl.contains("提级管辖")) && cpjg.contains("由本院审理");
    }

    /**
     * 民事裁定书(依职权移送管辖用)
     * @return
     */
    protected boolean isZQYSGX(){
        String pattern2 = "移送"+CHINESE+"处理";
        return matchCpjg(pattern2);
    }

    /**
     * 民事裁定书(因管辖权争议报请指定管辖案件用)
     * @return
     */
    protected boolean isBQZDGX(){
        String pattern1 = "管辖权"+CHINESE+"争议";
        String pattern2 = "指定管辖";
        return matchSsjl(pattern1) && matchSsjl(pattern2) && cpjg.contains("移送") &&  ssjl.indexOf("一案") != ssjl.lastIndexOf("一案");
    }

//
    /**
     * 民事裁定书(有管辖权人民法院报请指定管辖案件用)
     * @return
     */
    protected boolean isYGXQBQZD(){
        String pattern2 = "由"+CHINESE+"审理";
        return matchCpjg(pattern2);
    }
}
