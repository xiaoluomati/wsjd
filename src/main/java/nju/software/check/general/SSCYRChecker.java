package nju.software.check.general;

import nju.software.util.JsonParserUtil;
import nju.software.util.StringUtil;
import nju.software.util.Synonym;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by away on 2018/4/11.
 */
public class SSCYRChecker {

    private JsonParserUtil jsonParserUtil;

    public SSCYRChecker(JsonParserUtil jsonParserUtil) {
        this.jsonParserUtil = jsonParserUtil;
    }

    public List<CheckInfoItemVO> check(List<WssscyrModel> sscyrList) {
        this.jsonParserUtil = jsonParserUtil;

        List<CheckInfoItemVO> SSCYRErrorList = new ArrayList<>();
        // 检查每个诉讼参与人信息是否完整
        for (WssscyrModel wssscyrModel : sscyrList) {
            CheckInfoItemVO checkInfoItemVO = checkInfo(wssscyrModel);
            if (null != checkInfoItemVO) {
                SSCYRErrorList.add(checkInfoItemVO);
            }
        }

        // 检查诉讼参与人是否出现不恰当的诉讼地位, 如上诉人写成了申请人
        List<CheckInfoItemVO> checkSsdw = checkSsdw(sscyrList);

        SSCYRErrorList.addAll(checkSsdw);
        return SSCYRErrorList;
    }

    private List<CheckInfoItemVO> checkSsdw(List<WssscyrModel> wssscyrModels) {
        List<CheckInfoItemVO> checkInfoItemVOList = new ArrayList<>();

        List<String> dsrRequirements = jsonParserUtil.getDsrRequirements();
        String dsrRequirementsArray = Arrays.toString(dsrRequirements.toArray());
        //当事人诉讼地位比对
        List<String> dsrList = new ArrayList<>();
        for (WssscyrModel wssscyrModel : wssscyrModels) {
            String ssdw = wssscyrModel.getSsdw();
            if (null != ssdw) {
                dsrList.add(ssdw);
            }
        }

        for (String s : dsrList) {
//            System.out.println("s = " + s);
            if (!Synonym.isContains(dsrRequirements, s)) {
                String errMsg = "诉讼参与人地位不应为 " + s;
                String tip = "诉讼参与人地位应该为: " + dsrRequirementsArray + "中的一种";
                checkInfoItemVOList.add(new CheckInfoItemVO(ErrorType.YSCW, errMsg, tip, ErrorLevelEnum.LV_2));
            }
        }

        return checkInfoItemVOList;
    }

    /**
     * 检查诉讼参与人信息是否完整, 包括
     *
     * (自然人)
     * 姓名、性别、出生年月日、民族、住所。
     * 当事人职业或者工作单位和职务不明确的，可以不表述
     *
     * (法人)
     * 写明名称和住所，并另起一行写明法定代表人的姓名和职务
     */
    private CheckInfoItemVO checkInfo(WssscyrModel wssscyrModel) {
        StringBuilder errorMsg = new StringBuilder();
        List<String> missPartList = new ArrayList<>();
        String name = wssscyrModel.getSscyr();
        boolean nameValid = checkName(wssscyrModel.getSscyr(), missPartList);
        if (nameValid) {
            errorMsg.append(name);
        }
        String tip = "";
        if (isNaturalDSR(wssscyrModel)) {
            tip = "当事人是自然人的，应当写明其姓名、性别、出生年月日、民族、职业或者工作单位和职务、住所。姓名、性别等身份事项以居民身份证、户籍证明为准。 当事人职业或者工作单位和职务不明确的，可以不表述";
            checkGender(wssscyrModel.getXb(), missPartList);
            checkBirthday(wssscyrModel.getCsrq(), missPartList);
            checkEthnicity(wssscyrModel.getMz(), missPartList);
            checkAddress(wssscyrModel.getDsrdz(), missPartList);
        } else if (isLegalDSR(wssscyrModel)) {
            tip = "当事人是法人的，写明名称和住所，并另起一行写明法定代表人的姓名和职务";
            checkAddress(wssscyrModel.getDsrdz(), missPartList);
            checkFDDBR(wssscyrModel.getFddbr(), missPartList);
        } else if (isFDDBR(wssscyrModel)) {
            tip = "当事人是法人的，写明名称和住所，并另起一行写明法定代表人的姓名和职务";
            checkZW(wssscyrModel.getDsrzw(), missPartList);
        }

        if (missPartList.isEmpty()) {
            return null;
        } else {
            errorMsg.append(" 缺少 ");
            for (String s : missPartList) {
                errorMsg.append(" \"").append(s).append("\" ");
            }
            return new CheckInfoItemVO(ErrorType.YSQS, errorMsg.toString(), tip, ErrorLevelEnum.LV_2);
        }
    }

    // 姓名正确返回 true, 错误 false
    private boolean checkName(String name, List<String> missPartList) {
        // TODO 给出诉讼参与人姓名推测?
        return isMissPart(name, "姓名", missPartList);
    }

    private void checkGender(String gender, List<String> missPartList) {
        isMissPart(gender, "性别", missPartList);
    }

    private void checkBirthday(String birthday, List<String> missPartList) {
        isMissPart(birthday, "出生日期", missPartList);
    }

    private void checkEthnicity(String ethnicity, List<String> missPartList) {
        isMissPart(ethnicity, "民族", missPartList);
    }

    private void checkAddress(String address, List<String> missPartList) {
        isMissPart(address, "住所", missPartList);
    }

    private void checkFDDBR(String fddbr, List<String> missPartList) {
        isMissPart(fddbr, "法定代表人", missPartList);
    }

    private void checkZW(String zw, List<String> missPartList) {
        isMissPart(zw, "法定代表人职务", missPartList);
    }

    private boolean isMissPart(String part, String partName,  List<String> missPartList) {
        if (null == part || StringUtil.trim(part).equals("")) {
            missPartList.add(partName);
            return false;
        }
        return true;
    }

    // 是否是当事人且是自然人
    private boolean isNaturalDSR(WssscyrModel wssscyrModel) {
        String dsrlb = wssscyrModel.getDsrlb();
        String dsrlx = wssscyrModel.getDsrlx();
        return (null != dsrlb) && (null != dsrlx) && (dsrlx.equals("自然人"));
    }

    // 是否是当事人且是法人
    private boolean isLegalDSR(WssscyrModel wssscyrModel) {
        String dsrlb = wssscyrModel.getDsrlb();
        String dsrlx = wssscyrModel.getDsrlx();
        return (null != dsrlb) && (null != dsrlx) && (dsrlx.equals("法人"));
    }

    // 是否是法定代表人
    private boolean isFDDBR(WssscyrModel wssscyrModel) {
        String sssf = wssscyrModel.getSssf();
        return (null != sssf) && (sssf.equals("法定代表人"));
    }
}
