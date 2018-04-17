package nju.software.check.general;

import nju.software.util.REUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;

import static nju.software.util.REUtil.CHINESE;
import static nju.software.util.REUtil.SINGLE_CHINESE;

/**
 * 检查案号
 * Created by away on 2018/4/7.
 */
public class AHChecker {

    private static final String AH_FORMAT = "“（”+ 收案年度 +“）”+ 法院代字 + 类型代字 + 案件编号 + “号”。";

    private static final String SAND = "(\\(\\d{4}\\))"; // 收案年度
    private static final String FYDD = "(" + SINGLE_CHINESE + "(\\d{0}|\\d{2}|\\d{4})" + ")"; // 法院代字
    private static final String LXDD = "(" + CHINESE + ")";
    private static final String AJBH = "(\\d+)";
    private static final String AH_PATTERN_NEW = SAND + FYDD + LXDD + AJBH + "号";

    public CheckInfoItemVO check(String ah) {
        boolean match = REUtil.match(ah, AH_PATTERN_NEW);
        if (!match) {
            return new CheckInfoItemVO(
                    ErrorType.YSCW, "案号结构错误", "案号格式应为: " + AH_FORMAT, ErrorLevelEnum.LV_3);
        } else {
            return null;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(REUtil.match("(2017)吉24",SAND+FYDD));
//
//    }
}
