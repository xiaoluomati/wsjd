package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by away on 2018/3/31.
 */
public abstract class BaseClassifier {

    public static final String YS_PREFIX = "YS";

    public static final String ES_PREFIX = "ES";

    public static final String GX_PREFIX = "GX";

    protected String ssjl;

    protected String cpjg;

    protected String cpgc;

    final String CHINESE = "[\\u0391-\\uFFE5]+";

    public abstract DocType getType(WsModel wsModel);

    public abstract String getParseRuleName();

    protected String getMethodName(String ESName) {
        int index = ESName.indexOf("_");
        return "is" + ESName.substring(index+1);
    }

    protected DocType getType(List<String> list, Class c, Object o){
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
