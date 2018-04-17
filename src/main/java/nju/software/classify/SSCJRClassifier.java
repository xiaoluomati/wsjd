package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/17.
 */
public class SSCJRClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.SSCJR_PREFIX, ah, wsModel);
    }

    /**
     * 民事裁定书(变更当事人用)
     * @return
     */
    private boolean isBGDSRY(){
        String pattern1 = "申请变更当事人";
        String pattern2 = "准许.*参加诉讼.*退出诉讼";
        return matchSsjl(pattern1) && matchCpjg(pattern2);
    }


    /**
     * 民事裁定书(未参加登记的权利人适用生效判决或裁定用)
     * @return
     */
    private boolean isWCJDJ(){
        String pattern1 = "人数众多.*人数尚未确定";
        String pattern2 = "通知权利人.*登记";
        return matchCpgc(pattern1) || matchCpgc(pattern2);
    }
}
