package nju.software.util;

/**
 * Created by away on 2018/4/11.
 */
public class StringUtil {

    // 去除半角全角制表符等各类空格
    public static String trim(String content) {
        if (null == content) {
            return null;
        }
        content = content.replace((char) 65279, ' ');
        content = content.replace((char) 12288, ' ');
        content = content.replace((char) 32, ' ');
        content = content.replace(" ", "");
        content = content.replaceAll("\\u00A0","");
        content = content.replace("\t", "");
        return content;
    }
}
