package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by away on 2018/4/2.
 */
public class ESClassifier extends BaseClassifier {
    @Override
    public DocType getType(WsModel wsModel,String ah) {
        return getType(DocType.ES_PREFIX, ah, wsModel);
    }

    /**
     * 民事裁定书(不参加二审诉讼按撤回上诉处理用)
     * @return
     */
    protected boolean isBCJESSS(){
        String pattern1 = "拒不出庭";
        String pattern2 = "按" + CHINESE + "撤回上诉";
        return matchCpjg(pattern2) && matchCpgc(pattern1);
    }

    /**
     * 民事裁定书(二审驳回起诉用)
     * 驳回×××(写明一审原告的姓名或名称)的起诉
     * @return
     */
    protected boolean isESBHQS(){
        String pattern1 = "驳回" +CHINESE+ "起诉";
        return matchCpjg(pattern1);
    }

    /**
     * 民事裁定书(二审不准许撤回上诉用)
     */
    protected boolean isESBZXCS(){
        String pattern = "不准许"+CHINESE+"撤回上诉";
        return matchCpjg(pattern) && (ssjl.contains("提出撤回上诉") || cpgc.contains("提出撤回上诉"));
    }

    /**
     * 民事裁定书(二审发回重审用)
     * @return
     */
    protected boolean isESFHCS(){
        String pattern = "发回"+CHINESE+"重审";
        return matchCpjg(pattern);
    }

    /**
     * 民事裁定书(二审维持不予受理裁定用)
     * @return
     */
    protected boolean isWCBHQSCD(){
        return cpgc.contains("不予受理")&&cpjg.contains("驳回上诉")&&cpjg.contains("维持原裁定");
    }

    /**
     * 民事裁定书(二审维持驳回起诉裁定用)
     * @return
     */
    protected boolean isWCBYSLCD(){
        return cpgc.contains("驳回上诉")&&cpjg.contains("驳回上诉")&&cpjg.contains("维持原裁定");
    }

    /**
     * 民事裁定书(二审指令立案受理用)
     * @return
     */
    protected boolean isZLLASL(){
        String pattern = "(指令|指定)"+CHINESE+"立案受理";
        return matchCpjg(pattern);
    }

    /**
     * 民事裁定书(二审指令审理用)
     * @return
     */
    protected boolean isZLSL(){
        String pattern = "(指令|指定)"+CHINESE+"审理";
        return matchCpjg(pattern);
    }

    /**
     * 民事裁定书(二审准许撤回上诉用)
     * @return
     */
    protected boolean isZXCHSS(){
        String pattern = "准许"+CHINESE+"撤回上诉";
        return matchCpjg(pattern);
    }

    /**
     * 民事裁定书(二审准许或不准许撤回起诉用)
     * @return
     */
    protected boolean isZXHBZX(){
        String pattern = "准许"+CHINESE+"撤回起诉";
        return matchCpjg(pattern);
    }

    /**
     * 民事裁定书(未交二审案件受理费按撤回上诉处理用)
     * @return
     */
    protected boolean isWSESSLF(){
        String pattern1 = "不予缴纳";
        String pattern2 = "按" + CHINESE + "撤回上诉";
        return matchCpjg(pattern2) && matchCpgc(pattern1);
    }



    }
