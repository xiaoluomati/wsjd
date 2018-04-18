package nju.software.classify;

import nju.software.preProcess.PreClassifyAH;
import nju.software.preProcess.PreClassifyLaws;
import nju.software.vo.DocType;
import nju.software.vo.TemplateLawVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by away on 2018/3/31.
 */
public abstract class BaseClassifier {

    protected String ssjl;

    protected String cpjg;

    protected String cpgc;

    static final String CHINESE = "[\\u4e00-\\u9fa5]+";

    public abstract DocType getType(WsModel wsModel,String ah);

    private DocType getPossibleDoctypeByAH(List<String> nameList, String ah){
        for (String s : nameList) {
            String uniqueAHD = PreClassifyAH.getUniqueAHD(s);
            if(uniqueAHD != null && !uniqueAHD.equals("") && ah.contains(uniqueAHD)){
                return DocType.getType(s);
            }
        }
        return null;
    }

    private DocType getPossibleDoctypeByLaws(List<String> nameList,List<WscpfxgcFtModel> preWscpfxgcFtModels){
        for (String s : nameList) {
            List<TemplateLawVO> uniqueLawsDetail = PreClassifyLaws.getUniqueLawsDetail(s);
            for (WscpfxgcFtModel preWscpfxgcFtModel : preWscpfxgcFtModels) {
                if(PreClassifyLaws.matchLaws(uniqueLawsDetail, preWscpfxgcFtModel)){
                    return DocType.getType(s);
                }
            }
        }
        return null;
    }

    protected String getMethodName(String name) {
        int index = name.lastIndexOf("_");
        return "is" + name.substring(index+1);
    }

    protected DocType getType(String prefix, String ah, WsModel wsModel){
        List<String> list = DocType.getTypeList(prefix);
        DocType possibleDoctypeByAH = getPossibleDoctypeByAH(list, ah);
        if(possibleDoctypeByAH != null){
            return possibleDoctypeByAH;
        }
        DocType possibleDoctypeByLaws = getPossibleDoctypeByLaws(list, wsModel.getPreWscpfxgcFtModels());
        if(possibleDoctypeByLaws != null){
            return possibleDoctypeByLaws;
        }
        this.ssjl = wsModel.getWsssjlSegment();
        this.cpjg = wsModel.getWscpjgSegment();
        this.cpgc = wsModel.getWscpfxgcSegment();
        for (String es : list) {
            String methodName = getMethodName(es);
            try {
                Method method = getClass().getDeclaredMethod(methodName);
                Boolean isMatch = ((boolean) method.invoke(this));
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


    private boolean match(String content, String pattern){
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        return matcher.find();
    }

    protected boolean matchSsjl(String pattern) {
        return match(ssjl, pattern);
    }

    protected boolean matchCpjg(String pattern){
        return match(cpjg, pattern);
    }

    protected boolean matchCpgc(String pattern){
        return match(cpgc, pattern);
    }

}
