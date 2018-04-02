package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

/**
 * Created by away on 2018/3/31.
 */
public abstract class BaseClassifier {

    protected String ssjl;

    protected String cpjg;

    protected String cpgc;

    final String CHINESE = "[\\u0391-\\uFFE5]+";

    public abstract DocType getType(WsModel wsModel);

    public abstract String getParseRuleName();
}
