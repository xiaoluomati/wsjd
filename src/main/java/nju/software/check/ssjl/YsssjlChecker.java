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
 *  一审裁定
 * Created by away on 2018/4/18.
 */

public class YsssjlChecker extends SSJLChecker {

    public YsssjlChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        super(jsonParserUtil, xmlParserUtil, wsModel);
    }

    @Override
    public List<CheckInfoItemVO> check() {
        baseCheck();
        checkQsz(); // 起诉状
        checkFsz(); // 反诉状
        checkCs(); // 撤诉
        checkFs(); // 反诉
        checkJjct(); // 拒绝出庭
        return checkInfoItemVOS;
    }

    /*
    起诉状
        日期
        起诉人
     */
    private void checkQsz() {
        String tip = "格式应该为:  ××××年××月××日，本院收到×××的起诉状。";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "起诉状")) {
            String name = wsssjlModel.getQsz().getName();
            String date = wsssjlModel.getQsz().getDate();
            String wrongPart = "";
            if (name == null && date == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart = "时间";
            } else if (name == null ) {
                wrongPart = "起诉人";
            }
            if (!wrongPart.isEmpty()) { // 缺少一部分
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "缺少起诉状" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }

    /*
    反诉状
        日期
        反诉人
     */
    private void checkFsz() {
        String tip = "格式应该为:  ××××年××月××日，本院收到×××的反诉状。";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "反诉状")) {
            String name = wsssjlModel.getFsz().getName();
            String date = wsssjlModel.getFsz().getDate();
            String wrongPart = "";
            if (name == null && date == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart = "时间";
            } else if (name == null ) {
                wrongPart = "反诉人";
            }
            if (!wrongPart.isEmpty()) { // 缺少一部分
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "缺少反诉状" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }

    /*
    撤诉
        日期
        撤诉人
     */
    private void checkCs() {
        String tip = "格式应该为:  原告×××于××××年××月××日向本院提出撤诉申请。";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "撤诉")) {
            String name = wsssjlModel.getCs().getName();
            String date = wsssjlModel.getCs().getDate();
            String wrongPart = "";
            if (name == null && date == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart = "撤诉时间";
            } else if (name == null ) {
                wrongPart = "撤诉人";
            }
            if (!wrongPart.isEmpty()) { // 缺少一部分
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "缺少" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }



    /*
    反诉
        日期
        原告
        被告
     */
    private void checkFs() {
        String tip = "格式应该为:   ××××年××月××日，被告×××对原告×××提出反诉。";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "反诉")) {
            String bg = wsssjlModel.getFs().getBg();
            String yg = wsssjlModel.getFs().getYg();
            String date = wsssjlModel.getFs().getFsrq();

            String wrongPart = "";
            if (bg == null && date == null && yg == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart += " 反诉时间 ";
            } else if (bg == null ) {
                wrongPart = " 被告 ";
            } else if (yg == null ) {
                wrongPart = " 原告 ";
            }
            if (!wrongPart.isEmpty()) { // 缺少一部分
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "缺少" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }

    /*
    拒绝出庭
        日期
        当事人
     */
    private void checkJjct() {
        String tip = "格式应该为:  ××××年××月××日，×××经传票传唤，无正当理由拒不到庭/未经法庭许可中途退庭。";
        if (Synonym.isContains(jsonParserUtil.getSsjlRequirements(), "拒绝出庭")) {
            String name = wsssjlModel.getJjct().getName();
            String date = wsssjlModel.getCs().getDate();
            String wrongPart = "";
            if (name == null && date == null) {
                wrongPart = "";
            }
            else if (date == null) {
                wrongPart = "拒绝出庭时间";
            } else if (name == null ) {
                wrongPart = "拒绝出庭当事人";
            }
            if (!wrongPart.isEmpty()) { // 缺少一部分
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "缺少" + wrongPart, tip, ErrorLevelEnum.LV_2));
            } else {
                // check date and name
            }
        }
    }

}
