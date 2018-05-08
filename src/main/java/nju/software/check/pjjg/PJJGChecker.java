package nju.software.check.pjjg;

import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.service.model.WsCpjgssfjeModel;
import nju.software.wsjx.service.model.WscpjgssfcdModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/4/12.
 */
public class PJJGChecker {

    private JsonParserUtil jsonParserUtil;
    private XmlParserUtil xmlParserUtil;
    private WsModel wsModel;

    public PJJGChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        this.jsonParserUtil = jsonParserUtil;
        this.xmlParserUtil = xmlParserUtil;
        this.wsModel = wsModel;
    }

    public List<CheckInfoItemVO> check() {
        List<CheckInfoItemVO> checkInfoItemVOS = new ArrayList<>();
        List<String> pjjgRequirements = jsonParserUtil.getPjjgRequirements();
        String tip = "判决结果应当包括: " + Arrays.toString(pjjgRequirements.toArray());
        System.out.println("pjjgRequirements = " + Arrays.toString(pjjgRequirements.toArray()));
        Map<String, String> pjjg = xmlParserUtil.getPjjg();
        System.out.println("pjjg = " + pjjg.keySet());
        for (String requirement : pjjgRequirements) {
            if(!Synonym.isContains(pjjg.keySet(), requirement)){
                if (requirement.equals("交代上诉权")) {
                    tip = "交代上诉权项格式如下:  如不服本裁定，可以在裁定书送达之日起十日内，向本院递交上诉状，并按照对方当事人或者代表人的人数提出副本，上诉于××××人民法院";
                }
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "缺少 " + requirement, tip, ErrorLevelEnum.LV_2));
            } else {
                System.out.println(requirement + " in " + Arrays.toString(pjjg.keySet().toArray()));
            }
        }

        WscpjgModel wscpjgModel = wsModel.getWscpjgModel();
        if (wscpjgModel != null) {
            WsCpjgssfModel ssfModel = wscpjgModel.getSsfModel();
            if (ssfModel != null) {
                List<WsCpjgssfjeModel> ssfjeModels = ssfModel.getSsfjeModels();
                BigDecimal je = new BigDecimal("0");
                for (WsCpjgssfjeModel ssfjeModel : ssfjeModels) {
                    String value = ssfjeModel.getValue();
                    if (value == null) continue;
                    String price = value.substring(0, value.indexOf("元"));
                    je = je.add(new BigDecimal(price));
                }
                BigDecimal cd = new BigDecimal("0");
                List<WscpjgssfcdModel> ssfcdModels = ssfModel.getSsfcdModels();
                for (WscpjgssfcdModel ssfcdModel : ssfcdModels) {
                    String value = ssfcdModel.getCdje();
                    if (value == null) continue;
                    String price = value.substring(0, value.indexOf("元"));
                    cd = cd.add(new BigDecimal(price));
                }

                if (!je.equals(cd)) {
                    checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.SSWBTY, "案件受理费计算错误", "案件受理费承担部分金额总和应该与前文的金额总和相同", ErrorLevelEnum.LV_2));
                }
            }
        }
        return checkInfoItemVOS;
    }

//    public static void main(String[] args) {
//        System.out.println(new BigDecimal(1));
//        System.out.println(new BigDecimal("1.23"));
//    }
}
