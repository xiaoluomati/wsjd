package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/17.
 */
public class ZJClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.ZJ_PREFIX, ah, wsModel);
    }

    /**
     * 民事裁定书(仲裁前证据保全用)
     * @return
     */
    private boolean isZCQZJBQ(){
        String p1 = "仲裁前保全证据";
        String p2 = "以.*提供担保";
        return matchSsjl(p1) && matchSsjl(p2);
    }

    /**
     * 民事裁定书(申请书证提出命令用)
     * @return
     */
    private boolean isSQSZ(){
        String p1 = "提出书面申请";
        String p2 = "请求.*责令.*提交";
        String p3 = "向.*提交";
        String p4 = "驳回.*申请";
        return matchSsjl(p1) && matchSsjl(p2) && (matchCpjg(p3) || matchCpjg(p4));
    }


    /**
     * 民事裁定书(申请返还鉴定费用用)
     * @return
     */
    private boolean isSQFHJDF(){
        String p1 = "返还.*鉴定费用";
        String p2 = "驳回.*申请";
        return matchSsjl(p1) && (matchCpjg(p1) || matchCpjg(p2));
    }

    /**
     * 民事裁定书(解除证据保全用)
     * @return
     */
    private boolean isJCZJBQ(){
        String p1 = "解除对.*保全措施";
        return matchCpjg(p1);
    }

    /**
     * 民事裁定书(诉前证据保全用)
     * @return
     */
    private boolean isSQZJBQ(){
        String p1 = "诉前保全证据";
        String p2 = "以.*提供担保";
        return matchSsjl(p1) && matchSsjl(p2);
    }


    /**
     * 民事裁定书(诉讼证据保全用)
     * @return
     */
    private boolean isSSZJBQ(){
        String p1 = "申请保全证据";
        String p2 = "以.*提供担保";
        return matchSsjl(p1) && matchSsjl(p2);
    }
}
