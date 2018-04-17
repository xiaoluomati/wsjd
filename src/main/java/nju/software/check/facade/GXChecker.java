package nju.software.check.facade;

import nju.software.check.pjjg.PJJGChecker;
import nju.software.check.ssjl.GxssjlChecker;
import nju.software.util.JsonParserUtil;
import nju.software.util.XmlParserUtil;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

/**
 * Created by away on 2018/4/18.
 */
public class GXChecker extends GeneralChecker {

    public GXChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        super(jsonParserUtil, xmlParserUtil, wsModel);
        ssjlChecker = new GxssjlChecker(jsonParserUtil, xmlParserUtil, wsModel);
        pjjgChecker = new PJJGChecker(jsonParserUtil, xmlParserUtil);
    }

}
