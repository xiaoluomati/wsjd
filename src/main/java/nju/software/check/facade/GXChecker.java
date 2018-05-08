package nju.software.check.facade;

import nju.software.check.pjjg.PJJGChecker;
import nju.software.check.ssjl.GxssjlChecker;
import nju.software.check.typo.TypoChecker;
import nju.software.util.JsonParserUtil;
import nju.software.util.XmlParserUtil;
import nju.software.vo.SectionTypoCheckVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;

import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/4/18.
 */
public class GXChecker extends GeneralChecker {

    public GXChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        super(jsonParserUtil, xmlParserUtil, wsModel);
        ssjlChecker = new GxssjlChecker(jsonParserUtil, xmlParserUtil, wsModel);
        pjjgChecker = new PJJGChecker(jsonParserUtil, xmlParserUtil, wsModel);
    }

    @Override
    public void checkAjjbqkTypo(Map<String, List<SectionTypoCheckVO>> map, TypoChecker typoChecker) {
        WsajjbqkModel wsajjbqkModel = wsModel.getWsajjbqkModel();
        map.put("gx_ygscd", typoChecker.check(wsajjbqkModel.getYgscd()));
        map.put("gx_bgbcd", typoChecker.check(wsajjbqkModel.getBgbcd()));
        map.put("gx_ysfylyd", typoChecker.check(wsajjbqkModel.getYsfylyd()));
        map.put("gx_bqfylyd", typoChecker.check(wsajjbqkModel.getBqfylyd()));
    }

    @Override
    public String[] getAjjbqkPart() {
        return new String[]{"gx_ygscd","gx_bgbcd","gx_ysfylyd","gx_bqfylyd"};
    }

}
