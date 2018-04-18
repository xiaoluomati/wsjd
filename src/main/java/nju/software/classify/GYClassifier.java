package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/18.
 */
public class GYClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.GY_PREFIX, ah,  wsModel);
    }


    /**
     * 民事裁定书(公益诉讼不准许撤回起诉用)
     * @return
     */
    private boolean isBZXCH(){
        String p1 = "公益诉讼";
        String p2 = "申请撤回起诉";
        String p3 = "不准许"+CHINESE+"撤回起诉";
        return matchSsjl(p1) && matchSsjl(p2) && matchCpjg(p3);
    }
    /**
     * 民事裁定书(对同一侵权行为另行提起公益诉讼不予受理用)
     * @return
     */
    private boolean isLXTQBYSL(){
        String p1 = "已经发生法律效力";
        String p2 = "另行提起";
        String p3 = "不予受理";
        return matchCpgc(p1) && matchCpgc(p2) && matchCpjg(p3);

    }
    /**
     * 民事裁定书(环境污染或者生态破坏公益诉讼准许撤回起诉用)
     * @return
     */
    private boolean isHJWRZXCH(){
        String p1 = "公益诉讼";
        String p2 = "申请撤回起诉";
        String p3 = "准许"+CHINESE+"撤回起诉";
        String p4 = "不准许";
        return matchSsjl(p1) && matchSsjl(p2) && matchCpjg(p3) && !matchCpjg(p4);
    }
}
