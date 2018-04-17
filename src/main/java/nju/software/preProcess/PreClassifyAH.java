package nju.software.preProcess;

import nju.software.classify.ParseMap;
import nju.software.util.IOHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuding on 2018/4/12.
 */
public class PreClassifyAH {
    private static final String CHINESE = "[\\u4e00-\\u9fa5]+";

    private static HashMap<String, String> ahMap;

    private static HashMap<String, List<String>> uniqueAH;

    private static HashMap<String, String> uniqueAHDetail;

    static {
        ahMap = new HashMap<>();
        uniqueAH = new HashMap<>();
        uniqueAHDetail = new HashMap<>();
        File file = new File("template");
        File[] files = file.listFiles();
        for (File f : files) {
            if(f.isDirectory()){
                String classname = f.getName();
                File[] files1 = f.listFiles();
                List<String> ahs = new ArrayList<>();
                for (File file1 : files1) {
                    String filename = file1.getName();
                    String name = "template/" + classname+"/" +filename;
                    if(file1.getName().contains("裁定")){
                        String s = IOHelper.read(name).get(2);
                        ahMap.put(filename.substring(0,filename.indexOf(".")), s);
                        if(!ahs.contains(s)){
                            ahs.add(s);
                        }
                    }
                }
                uniqueAH.put(classname, ahs);
            }
//            System.out.println(uniqueAH);
        }
        produceUniqueAH();
        produceUniqueAHD();
    }

    public static String getUniqueAHD(String templatename){
        if(uniqueAHDetail.containsKey(templatename)){
            return uniqueAHDetail.get(templatename);
        }
        return null;
    }

    public static List<String> getUniqueAH(String classname){
        if(uniqueAH.containsKey(classname)){
            return uniqueAH.get(classname);
        }
        return new ArrayList<>();
    }

    public static String getPossibleClass(String ah){
        for (String s : uniqueAH.keySet()) {
            for (String s1 : uniqueAH.get(s)) {
                if(ah.contains(s1)){
                    return s;
                }
            }
        }
        return ParseMap.NOT_DETERMINED;
    }

    private static void produceUniqueAHD(){
        for (String s : ahMap.keySet()) {
            String ah = ahMap.get(s);
            if(!isCommonAHD(ahMap, ah)){
                Matcher matcher = Pattern.compile(CHINESE).matcher(ah);
                if(matcher.find())
                    uniqueAHDetail.put(s, matcher.group());
            }
        }
    }

    private static void produceUniqueAH(){
        HashMap<String, List<String>> temp = new HashMap<>();
        for (String s : uniqueAH.keySet()) {
            List<String> strings = uniqueAH.get(s);
            List<String> uniquewords = new ArrayList<>();
            for (String string : strings) {
                if(!isCommonAH(uniqueAH, string)){
                    Matcher matcher = Pattern.compile(CHINESE).matcher(string);
                    if(matcher.find())
                        uniquewords.add(matcher.group());
                }
            }
            temp.put(s, uniquewords);
        }
        uniqueAH = temp;
    }

    private static boolean isCommonAHD(HashMap<String, String> map, String value){
        int count = 0;
        for (String s : map.keySet()) {
            if(map.get(s).equals(value) ){
                count++;
            }
            if(count > 1|| value.equals("(××××)……号") || value.equals("(××××)……民×……号")){
                return true;
            }
        }
        return false;
    }
    private static boolean isCommonAH(HashMap<String, List<String>> map, String value){
        int count = 0;
        for (String s : map.keySet()) {
            if(map.get(s).contains(value)){
                count++;
            }
            if(count > 1|| value.equals("(××××)……号") || value.equals("(××××)……民×……号")){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(ahMap);
        System.out.println(uniqueAH);
        System.out.println(uniqueAHDetail);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : uniqueAH.keySet()) {
            List<String> strings = uniqueAH.get(s);
            if(!strings.isEmpty()){
                stringBuilder.append(s);
                stringBuilder.append(" 独有案号：");
                stringBuilder.append(strings);
                stringBuilder.append("\n");
            }
        }
        for (String s : uniqueAHDetail.keySet()) {
            String string = uniqueAHDetail.get(s);
            stringBuilder.append(s);
            stringBuilder.append(" 独有案号：");
            stringBuilder.append(string);
            stringBuilder.append("\n");
        }
        IOHelper.write("src/main/resources/templateAH.txt",stringBuilder.toString());
    }

}
