package nju.software.util;

import java.util.List;

/**
 * Created by away on 2018/4/3.
 */
public class Synonym {

    // TODO 同义词
    private static String[] sscyr = {"诉讼费承担记录", "诉讼费承担"};
    private static String[] ssr = {"上诉人(原审诉讼地位)", "上诉人", "上诉人(原审原告)", "上诉人(原审被告)"};
    private static String[] bssr = {"被上诉人(原审诉讼地位)", "被上诉人", "被上诉人(原审原告)", "被上诉人(原审被告)"};

    public static boolean isEqual(String a, String b) {
        if (same(sscyr, a, b)) {
            return true;
        } else if (same(ssr, a, b)) {
            return true;
        } else if (same(bssr, a, b)) {
            return true;
        }
        return a.equals(b);
    }

    public static boolean isContains(List<String> list, String str) {
        for (String s : list) {
            if (isEqual(str, s)) {
                return true;
            }
        }
        return false;
    }

    private static boolean same(String[] list, String a, String b) {
        return contains(list, a) && contains(list, b);
    }

    private static boolean contains(String[] list, String str) {
        for (String s : list) {
            if (s.contains(str)) {
                return true;
            }
        }
        return true;
    }
}
