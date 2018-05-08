package nju.software.check.facade;

import nju.software.check.pjjg.PJJGChecker;
import nju.software.check.ssjl.YsssjlChecker;
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
public class YSChecker extends GeneralChecker {

    public YSChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        super(jsonParserUtil, xmlParserUtil, wsModel);
        ssjlChecker = new YsssjlChecker(jsonParserUtil, xmlParserUtil, wsModel);
        pjjgChecker = new PJJGChecker(jsonParserUtil, xmlParserUtil, wsModel);
    }

    @Override
    public void checkAjjbqkTypo(Map<String, List<SectionTypoCheckVO>> map, TypoChecker typoChecker) {
        WsajjbqkModel wsajjbqkModel = wsModel.getWsajjbqkModel();
        map.put("ys_ygscd", typoChecker.check(wsajjbqkModel.getYgscd()));

        StringBuilder cmss = new StringBuilder();
        List<String> cmssd = wsajjbqkModel.getCmssd();
        if (cmssd != null && !cmssd.isEmpty()) {
            for (String s : cmssd) {
                cmss.append(s);
            }
            map.put("ys_cmssd", typoChecker.check(cmss.toString()));
        }

        StringBuilder zj = new StringBuilder();
        List<String> zjd = wsajjbqkModel.getZjd();
        if (zjd  != null && !zjd.isEmpty()) {
            for (String s : zjd) {
                zj.append(s);
            }
            map.put("ys_zjd", typoChecker.check(zj.toString()));
        }

        map.put("ys_fsscd", typoChecker.check(wsajjbqkModel.getFsscd()));
        map.put("ys_fsbcd", typoChecker.check(wsajjbqkModel.getFsbcd()));
    }

    @Override
    public String[] getAjjbqkPart() {
        return new String[]{"yscd_ygscd", "yscd_cmssd"};
    }
}
