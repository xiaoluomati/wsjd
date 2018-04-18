package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/18.
 */
public class DSRClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.DSR_PREFIX, ah, wsModel);
    }

    /**
     * 民事裁定书(中止再审程序用)
     * @return
     */
    private boolean isZZZSCX(){
        String p1 = "组成合议庭.*审理本案";
        String p2 = "正在审理中";
        String p3 = "中止诉讼";
        String p4 = "先行审理";
        return matchSsjl(p1) && matchSsjl(p2) && matchCpjg(p3) && matchCpjg(p4);
    }
    /**
     * 民事裁定书(对第三人撤销之诉不予受理用)
     * @return
     */
    private boolean isBYSL(){
        String p1 = "第三人撤销之诉";
        String p2 = "对.*第三人撤销之诉.*不予受理";
        return matchSsjl(p1) && matchCpjg(p2);
    }
    /**
     * 民事裁定书(第三人撤销之诉并入再审程序用)
     * @return
     */
    private boolean isBRZSCX(){
        String p1 = "尚未审结";
        String p2 = "由.*裁定再审";
        String p3 = "并入再审程序";
        String p4 = "并入.*审理";
        return matchSsjl(p1) && matchCpgc(p2) && matchCpgc(p3) && matchCpjg(p4);
    }

}
