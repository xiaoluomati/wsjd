package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/17.
 */
public class BQClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.BQ_PREFIX, ah, wsModel);
    }


    /**
     * 民事裁定书(仲裁中财产保全用)
     * @return
     */
    private boolean isZCZCCBQ(){
        String p1 = "向"+CHINESE+"仲裁委员会申请财产保全";
        String p2 = "(查封|扣押|冻结)"+CHINESE+"的";
        return matchSsjl(p1) && matchCpjg(p2);
    }

    /**
     * 民事裁定书(依职权诉讼保全用)
     * @return
     */
    private boolean isYZQSSBQ(){
        String p1 = "依职权";
        String p2 = "诉讼保全";
        return matchCpgc(p1) && matchCpgc(p2) || matchCpjg(p1) && matchCpjg(p2);
    }

    /**
     * 民事裁定书(保全或者先予执行裁定复议用)
     * @return
     */
    private boolean isCDFY(){
        String p1 = "复议";
        String p2 = "财产保全|行为保全|先予执行";
        String p3 = "驳回"+CHINESE+"复议请求";
        String p4 = "撤销.*(保全|先予执行)";
        return matchSsjl(p1) && matchSsjl(p2) && (matchCpjg(p3) || matchCpjg(p4));
    }


     /**
     * 民事裁定书(先予执行用)
     * @return
     */
    private boolean isXYZX(){
        String p1 = "申请先予执行";
        return matchSsjl(p1);
    }

    /**
     * 民事裁定书(变更保全用)
     * @return
     */
    private boolean isBGBQ(){
        String p1 = "等值担保财产";
        String p2 = "变更保全标的物";
        String p3 = "解除.*(查封|扣押|冻结)";
        String p4 = "期限";
        return matchCpjg(p3) && matchCpjg(p4) && matchSsjl(p1) && matchSsjl(p2);
    }

    /**
     * 民事裁定书(执行前保全用)
     * @return
     */
    private boolean isZXQBQ(){
        String p1 = "已经生效.*申请保全";
        return matchSsjl(p1);
    }
    /**
     * 民事裁定书(解除保全用)
     * @return
     */
    private boolean isJCBQ(){
        String p1 = "解除上述保全";
        String p2 = "解除对.*的.*";
        return matchSsjl(p1) && matchCpjg(p2);
    }
    /**
     * 民事裁定书(诉前行为保全用)
     * @return
     */
    private boolean isSQXWBQ(){
        String p1 = "申请诉前行为保全";
        return matchSsjl(p1);
    }
    /**
     * 民事裁定书(诉前财产保全用)
     * @return
     */
    private boolean isSQCCBQ(){
        String p1 = "申请诉前财产保全";
        String p2 = "(查封|扣押|冻结)";
        return matchSsjl(p1) && matchCpjg(p2);
    }
    /**
     * 民事裁定书(诉讼行为保全用)
     * @return
     */
    private boolean isSSXWBQ(){
        String p1 = "申请行为保全";
        return matchSsjl(p1);
    }
    /**
     * 民事裁定书(诉讼财产保全用)
     * @return
     */
    private boolean isSSCCBQ(){
        String p1 = "申请财产保全";
        String p2 = "(查封|扣押|冻结)";
        return matchSsjl(p1) && matchCpjg(p2);
    }
    /**
     * 民事裁定书(驳回保全或者先予执行申请用)
     * @return
     */
    private boolean isBHSQ(){
        String p1 = "申请(财产保全|行为保全|先予执行)";
        String p2 = "驳回"+CHINESE+"申请";
        return matchSsjl(p1) && matchCpjg(p2);
    }
}
