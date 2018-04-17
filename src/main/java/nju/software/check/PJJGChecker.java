package nju.software.check;

import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/4/12.
 */
@Component
public class PJJGChecker {

    public List<CheckInfoItemVO> check(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil) {
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
        return checkInfoItemVOS;
    }
}
