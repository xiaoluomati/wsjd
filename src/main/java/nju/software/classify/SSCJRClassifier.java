package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

/**
 * Created by zhuding on 2018/4/17.
 */
public class SSCJRClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(SSCJR_PREFIX, ah, wsModel);
    }
}
