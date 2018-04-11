package nju.software.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2018/4/3.
 */
public class Synonym {

    private static final String PATH = "src\\main\\resources\\synonym.txt";

    private static final String SPLIT = ", ";

    private static List<String[]> synonymList;

    static {
        synonymList = new ArrayList<>();
        List<String> read = IOHelper.readRaw(PATH);
        for (String s : read) {
            String[] split = s.split(SPLIT);
//            System.out.println("split = " + Arrays.toString(split));
            synonymList.add(split);
        }
    }

    public static boolean isEqual(String a, String b) {
        for (String[] strings : synonymList) {
            if (same(strings, a, b)) {
                return true;
            }
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

//    public static void main(String[] args) {
//        Synonym.isEqual("a", "b");
//    }
}
