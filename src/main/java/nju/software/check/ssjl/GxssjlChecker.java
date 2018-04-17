package nju.software.check.ssjl;

import nju.software.util.JsonParserUtil;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.util.List;

/**
 * Created by away on 2018/4/17.
 */
public class GxssjlChecker extends SSJLChecker {

    public GxssjlChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        super(jsonParserUtil, xmlParserUtil, wsModel);
    }

    @Override
    public List<CheckInfoItemVO> check() {
        baseCheck();
        checkName("…œÀﬂ»À", "getSsr");
        return checkInfoItemVOS;
    }
}
