package nju.software.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by away on 2018/4/7.
 */
public class REUtil {


    public static final String SINGLE_CHINESE = "[\\u0391-\\uFFE5]{1}";
    public static final String CHINESE = "[\\u0391-\\uFFE5]+";

    public static boolean match(String content, String pattern){
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        return matcher.find();
    }

    public static Matcher getMatch(String content, String pattern){
        Pattern compile = Pattern.compile(pattern);
        return compile.matcher(content);
    }
}
