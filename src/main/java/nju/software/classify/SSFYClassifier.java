package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

/**
 * Created by zhuding on 2018/4/18.
 */
public class SSFYClassifier extends BaseClassifier{

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.SSFY_PREFIX, ah, wsModel);
    }


    /**
     * 民事裁定书(未补交案件受理费按撤回起诉处理用)
     * @return
     */
    private boolean isWBJACH(){
        String p1 = "送达补交.*通知";
        String p2 = "未(按期|足额)*补交";
        String p3 = "按.*撤回起诉";
        return (matchSsjl(p1) || matchCpgc(p1)) && (matchSsjl(p2) || matchCpgc(p2)) && matchCpjg(p3);
    }
    /**
     * 民事裁定书(未预交案件受理费按撤回起诉处理用)
     * @return
     */
    private boolean isWYJACH(){
        String p1 = "送达交纳.*通知";
        String p2 = "(未在.*预交)|(申请(减|缓|免).*未获批准)";
        String p3 = "按.*撤回起诉";
        return (matchSsjl(p1) || matchCpgc(p1)) && (matchSsjl(p2) || matchCpgc(p2)) && matchCpjg(p3);
    }
}
