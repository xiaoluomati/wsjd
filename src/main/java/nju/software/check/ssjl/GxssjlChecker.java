package nju.software.check.ssjl;

import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;
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
        checkName("上诉人", "getSsr");
        checkBqfy(); // 报请法院
        checkSscd(); // 上诉裁定
        return checkInfoItemVOS;
    }

    /*
        报请法院
            法院名称
            报请时间
     */
    private void checkBqfy() {
        String tip = "格式应该为:  ××××年××月××日，××××人民法院(写明报请人民法院名称)报请本院指定管辖";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "报请法院")) {
            String name = wsssjlModel.getBqfymc();
            String date = wsssjlModel.getBqsj();
            String wrongPart = "";
            if (name == null && date == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart = "时间";
            } else if (name == null ) {
                wrongPart = "法院名称";
            }
            if (!wrongPart.isEmpty()) { // 缺少一部分
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "缺少报请" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }

    /*
        上诉裁定
            法院名称
            案号
     */
    private void checkSscd() {
        String tip = "不服××××人民法院(××××)……民初……号民事裁定，向本院提起上诉";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "上诉裁定")) {
            String name = wsssjlModel.getSscdfymc();
            String ah = wsssjlModel.getSscdah();
            String wrongPart = "";
            if (name == null && ah == null) {
                wrongPart = "";
            }
            if (name == null) {
                wrongPart = "法院名称";
            }
            if (ah == null) {
                wrongPart = "案号 ";
            }
            if (!wrongPart.isEmpty()) { // 缺少一部分
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "缺少上诉裁定" + wrongPart, tip, ErrorLevelEnum.LV_2));
            }
        }
    }

    // todo 争议裁定
}
