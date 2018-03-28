package nju.software.wsjd.classify;

import nju.software.service.ErrorCheckService;
import nju.software.service.impl.ErrorCheckServiceImpl;
import nju.software.vo.CheckInfoVO;
import nju.software.vo.DocInfoVO;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuding on 2018/3/20.
 */
public class TestClass {

    private static String jsonString(){
        return "{\n" +
                "  \"民事裁定书(二审驳回起诉用)\": {\n" +
                "    \"诉讼记录\": [\n" +
                "      \"上诉人\",\n" +
                "      \"争议裁定\",\n" +
                "      \"案由\"\n" +
                "    ],\n" +
                "    \"判决结果\": [\n" +
                "      \"判决\",\n" +
                "      \"上诉人\"\n" +
                "    ],\n" +
                "    \"案号\": \"民终\",\n" +
                "    \"当事人\": [\n" +
                "      \"上诉人\",\n" +
                "      \"被上诉人\",\n" +
                "      \"原审原告\",\n" +
                "      \"被告\",\n" +
                "      \"第三人\"\n" +
                "    ],\n" +
                "    \"案件基本情况\": [\n" +
                "      \"上诉人诉称段\",\n" +
                "      \"被上诉人辩称段\",\n" +
                "      \"前一审原告诉称段\",\n" +
                "      \"前一审查明事实段\",\n" +
                "      \"前一审审理段\",\n" +
                "      \"前一审判决段\",\n" +
                "      \"本审审理段\"\n" +
                "    ],\n" +
                "    \"裁判分析过程\": {\n" +
                "      \"法律法条引用\": [\n" +
                "        \"《最高人民法院关于适用〈中华人民共和国民事诉讼法〉的解释》第三百三十条\"\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    public static void main(String[] args) {
        ErrorCheckService errorCheckService = new ErrorCheckServiceImpl();
        DocInfoVO docInfoVO = new DocInfoVO("xml/丁立德与李炳祥、李炳恕民间借贷纠纷二审民事裁定书.xml",jsonString());
        System.out.println(errorCheckService.checkError(docInfoVO));
    }
}
