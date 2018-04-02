package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

/**
 * Created by away on 2018/3/31.
 */
public interface BaseClassifier {

    final String CHINESE = "[\\u0391-\\uFFE5]+";

    DocType getType(WsModel wsModel);
}
