package nju.software.check.ssjl;

import nju.software.util.JsonParserUtil;
import nju.software.util.Synonym;
import nju.software.util.XmlParserUtil;
import nju.software.vo.CheckInfoItemVO;
import nju.software.vo.ErrorLevelEnum;
import nju.software.vo.ErrorType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by away on 2018/4/12.
 */

public abstract class SSJLChecker {

    protected WsModel wsModel;
    protected List<CheckInfoItemVO> checkInfoItemVOS;
    protected JsonParserUtil jsonParserUtil;
    protected XmlParserUtil xmlParserUtil;
    protected WsssjlModel wsssjlModel;

    public SSJLChecker(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        this.jsonParserUtil = jsonParserUtil;
        this.xmlParserUtil = xmlParserUtil;
        this.wsModel = wsModel;
        this.checkInfoItemVOS = new ArrayList<>();
        this.wsssjlModel = wsModel.getWsssjlModel();
    }


    // 检查是否缺少某一部分
    protected void baseCheck() {
        this.checkInfoItemVOS = new ArrayList<>();
        List<String> ssjlRequirements = jsonParserUtil.getSsjlRequirements();
        String tip = "诉讼记录应当包括: " + Arrays.toString(ssjlRequirements.toArray());
        System.out.println("ssjlRequirements = " + Arrays.toString(ssjlRequirements.toArray()));
        Map<String, String> ssjl = xmlParserUtil.getSsjl();
        System.out.println("ssjl = " + ssjl.keySet());
        for (String requirement : ssjlRequirements) {
            if(!Synonym.isContains(ssjl.keySet(), requirement)){
                checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.YSQS, "缺少 " + requirement, tip, ErrorLevelEnum.LV_2));
            }
        }
//        checkInfoItemVOS.addAll(checkYS("诉讼记录", ssjlRequirements, ssjl.keySet()));

        checkName("原告", "getYg");
        checkName("被告", "getBg");
    }


    // 检查诉讼记录中的原告(被告, 上诉人等)的名称是否错误
    protected void checkName(String name, String methodName) {
        List<String> ssjlRequirements = jsonParserUtil.getSsjlRequirements();
        WsssjlModel wsssjlModel = wsModel.getWsssjlModel();
        List<WssscyrModel> sscyrList = wsModel.getWssscyrModels();

        if (Synonym.isContains(ssjlRequirements, name)) {
            for (WssscyrModel wssscyrModel : sscyrList) {
                if (wssscyrModel.getSssf() == null) continue;
                if(wssscyrModel.getSssf().equals(name)) {
                    String trueName = wssscyrModel.getSscyr();
                    try {
                        Method method = wsssjlModel.getClass().getDeclaredMethod(methodName);
                        String checkName = (String) method.invoke(wsssjlModel);
//                        System.out.println("checkName: " + checkName);
                        if (!trueName.equals(checkName)) {
                            checkInfoItemVOS.add(new CheckInfoItemVO(ErrorType.SSWBTY, name + "名称错误", "名称应为: " + trueName, ErrorLevelEnum.LV_2));
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public abstract List<CheckInfoItemVO> check();
}
