package nju.software.check.facade;

import nju.software.check.general.AHChecker;
import nju.software.check.general.SSCYRChecker;
import nju.software.check.pjjg.PJJGChecker;
import nju.software.check.ssjl.SSJLChecker;
import nju.software.util.JsonParserUtil;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.util.List;

/**
 * Created by away on 2018/4/18.
 */
public abstract class GeneralChecker {

    protected AHChecker ahChecker;
    protected SSCYRChecker sscyrChecker;
    protected SSJLChecker ssjlChecker;
    protected PJJGChecker pjjgChecker;
    protected JsonParserUtil jsonParserUtil;
    protected XmlParserUtil xmlParserUtil;
    protected WsModel wsModel;

    public GeneralChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        ahChecker = new AHChecker();
        sscyrChecker = new SSCYRChecker(jsonParserUtil);
        this.jsonParserUtil = jsonParserUtil;
        this.xmlParserUtil = xmlParserUtil;
        this.wsModel = wsModel;
    }

    public CheckInfoItemVO checkAh(String ah) {
        return ahChecker.check(ah);
    }

    public List<CheckInfoItemVO> checkSscyr() {
        return sscyrChecker.check(wsModel.getWssscyrModels());
    }

    public List<CheckInfoItemVO> checkSsjl() {
        return ssjlChecker.check();
    }

    public List<CheckInfoItemVO> checkPjjg() {
        return pjjgChecker.check();
    }
}
