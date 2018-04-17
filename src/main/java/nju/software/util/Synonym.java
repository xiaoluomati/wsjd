package nju.software.util;

import java.util.ArrayList;
import java.util.Collection;
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

    // 两个字符串是否为同义词
    public static boolean isEqual(String a, String b) {
        for (String[] strings : synonymList) {
            if (same(strings, a, b)) {
                return true;
            }
        }
        return a.equals(b);
    }

    // 数组中是否有该字符串或其同义词
    public static boolean isContains(Collection<String> list, String str) {
        for (String s : list) {
            if (isEqual(str, s)) {
//                System.out.println("equal: " + str + " " + s);
                return true;
            }
        }
        return false;
    }


    // 两个字符串是否在同一个数组中
    private static boolean same(String[] list, String a, String b) {
//        System.out.println("list " + Arrays.toString(list) + " " + a + " " + b);
//        System.out.println(contains(list, a));
//        System.out.println(contains(list, b));
        return contains(list, a) && contains(list, b);
    }

    // 数组中是否有这个字符串, 这里指初始字符串, 同义词算不相等的两个字符串
    private static boolean contains(String[] list, String str) {
        for (String s : list) {
//            System.out.print(s + " ");
//            System.out.print(str + " ");
//            System.out.println(s.equals(str));
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        Synonym.isEqual("a", "b");
//    }
}
