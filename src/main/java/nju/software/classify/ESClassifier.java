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
    public DocType getType(WsModel wsModel) {
        this.ssjl = wsModel.getWsssjlSegment();
        this.cpjg = wsModel.getWscpjgSegment();
        this.cpgc = wsModel.getWscpfxgcSegment();
        List<String> esList = DocType.getES();
        Class<? extends ESClassifier> clz = getClass();
        return getType(esList, clz, this);
    }

    @Override
    public String getParseRuleName() {
        return ParseMap.getInstance().getParseClassName("第二审程序");
    }


    private DocType getType(List<String> list, Class c, Object o){
        for (String es : list) {
            String methodName = getMethodName(es);
            try {
                Method method = c.getDeclaredMethod(methodName);
                Boolean isMatch = ((boolean) method.invoke(o));
                if (isMatch) {
                    DocType type = DocType.getType(es);
                    if (type != null) {
                        return type;
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 民事裁定书(不参加二审诉讼按撤回上诉处理用)
     * @return
     */
    private boolean isBCJESSS(){
        String pattern1 = "拒不出庭";
        String pattern2 = "按" + CHINESE + "撤回上诉";
        return matchCpjg(pattern2) && matchCpgc(pattern1);
    }

    /**
     * 民事裁定书(二审驳回起诉用)
     * 驳回×××(写明一审原告的姓名或名称)的起诉
     * @return
     */
    private boolean isESBHQS(){
        String pattern1 = "驳回" +CHINESE+ "起诉";
        return matchCpjg(pattern1);
    }

    /**
     * 民事裁定书(二审不准许撤回上诉用)
     */
    private boolean isESBZXCS(){
        String pattern = "不准许"+CHINESE+"撤回上诉";
        return matchCpjg(pattern) && (ssjl.contains("提出撤回上诉") || cpgc.contains("提出撤回上诉"));
    }

    /**
     * 民事裁定书(二审发回重审用)
     * @return
     */
    private boolean isESFHCS(){
        String pattern = "发回"+CHINESE+"重审";
        return matchCpjg(pattern);
    }

    /**
     * 民事裁定书(二审维持不予受理裁定用)
     * @return
     */
    private boolean isWCBHQSCD(){
        return cpgc.contains("不予受理")&&cpjg.contains("驳回上诉")&&cpjg.contains("维持原裁定");
    }

    /**
     * 民事裁定书(二审维持驳回起诉裁定用)
     * @return
     */
    private boolean isWCBYSLCD(){
        return cpgc.contains("驳回上诉")&&cpjg.contains("驳回上诉")&&cpjg.contains("维持原裁定");
    }

    /**
     * 民事裁定书(二审指令立案受理用)
     * @return
     */
    private boolean isZLLASL(){
        String pattern = "(指令|指定)"+CHINESE+"立案受理";
        return matchCpjg(pattern);
    }

    /**
     * 民事裁定书(二审指令审理用)
     * @return
     */
    private boolean isZLSL(){
        String pattern = "(指令|指定)"+CHINESE+"审理";
        return matchCpjg(pattern);
    }

    /**
     * 民事裁定书(二审准许撤回上诉用)
     * @return
     */
    private boolean isZXCHSS(){
        String pattern = "准许"+CHINESE+"撤回上诉";
        return matchCpjg(pattern);
    }

    /**
     * 民事裁定书(二审准许或不准许撤回起诉用)
     * @return
     */
    private boolean isZXHBZX(){
        String pattern = "准许"+CHINESE+"撤回起诉";
        return matchCpjg(pattern);
    }

    /**
     * 民事裁定书(未交二审案件受理费按撤回上诉处理用)
     * @return
     */
    private boolean isWSESSLF(){
        String pattern1 = "不予缴纳";
        String pattern2 = "按" + CHINESE + "撤回上诉";
        return matchCpjg(pattern2) && matchCpgc(pattern1);
    }

    private String getMethodName(String ESName) {
        int index = ESName.indexOf("_");
        return "is" + ESName.substring(index+1);
    }

    private boolean match(String content, String pattern){
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        return matcher.find();
    }

    private boolean matchSsjl(String pattern) {
        return match(ssjl, pattern);
    }

    private boolean matchCpjg(String pattern){
        return match(cpjg, pattern);
    }

    private boolean matchCpgc(String pattern){
        return match(cpgc, pattern);
    }
}
