package nju.software.check.facade;

import nju.software.check.pjjg.PJJGChecker;
import nju.software.check.ssjl.YscdssjlChecker;
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
public class YSCDChecker extends GeneralChecker {

    public YSCDChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        super(jsonParserUtil, xmlParserUtil, wsModel);
        ssjlChecker = new YscdssjlChecker(jsonParserUtil, xmlParserUtil, wsModel);
        pjjgChecker = new PJJGChecker(jsonParserUtil, xmlParserUtil);
    }

    @Override
    public void checkAjjbqkTypo(Map<String, List<SectionTypoCheckVO>> map, TypoChecker typoChecker) {
        WsajjbqkModel wsajjbqkModel = wsModel.getWsajjbqkModel();
        map.put("yscd_ygscd", typoChecker.check(wsajjbqkModel.getYgscd()));
        StringBuilder cmss = new StringBuilder();
        List<String> cmssd = wsajjbqkModel.getCmssd();
        if (cmssd != null && !cmssd.isEmpty()) {
            for (String s : wsajjbqkModel.getCmssd()) {
                cmss.append(s);
            }
            map.put("yscd_cmssd", typoChecker.check(cmss.toString()));
        }
    }

    @Override
    public String[] getAjjbqkPart() {
        return new String[]{"yscd_ygscd", "yscd_cmssd"};
    }
}
