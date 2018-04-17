package nju.software.preProcess;

import nju.software.classify.ParseMap;
import nju.software.util.IOHelper;
import nju.software.vo.DocType;
import nju.software.vo.TemplateLawVO;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhuding on 2018/4/11.
 */
public class PreClassifyLaws {

    private PreClassifyLaws() {}

    //每个小类的法条
    private static HashMap<String, List<TemplateLawVO>> map;

    //每个大类的独有法条
    private static HashMap<String, List<TemplateLawVO>> uniqueLaws;

    //每个小类的独有法条
    private static HashMap<String, List<TemplateLawVO>> uniqueLawsDetail;

    static {
        map = new HashMap<>();
        uniqueLaws = new HashMap<>();
        uniqueLawsDetail = new HashMap<>();
        File file = new File("template");
        File[] files = file.listFiles();
        for (File f : files) {
            if(f.isDirectory()){
                String classname = f.getName();
                File[] files1 = f.listFiles();
                List<TemplateLawVO> templateLawVOS = new ArrayList<>();
                for (File file1 : files1) {
                    String filename = file1.getName();
                    String name = "template/" + classname+"/" +filename;
                    if(file1.getName().contains("裁定")){
                        List<TemplateLawVO> lawList = getLawList(IOHelper.read(name));
                        map.put(filename.substring(0, filename.indexOf(".")), lawList);
                        templateLawVOS.addAll(lawList);
                    }
                }
                uniqueLaws.put(classname, TemplateLawVO.mergeList(templateLawVOS));
            }
        }
        uniqueLaws = produceUniqueLaws(uniqueLaws);
        uniqueLawsDetail = produceUniqueLaws(map);
    }

    private static HashMap<String, List<TemplateLawVO>> produceUniqueLaws(HashMap<String, List<TemplateLawVO>> map){
        HashMap<String, List<TemplateLawVO>> target = new HashMap<>();
        for (String s : map.keySet()) {
            List<TemplateLawVO> templateLawVOS = map.get(s);
            List<TemplateLawVO> unique = new ArrayList<>();
            for (TemplateLawVO templateLawVO : templateLawVOS) {
                TemplateLawVO templateLawVO1 = new TemplateLawVO();
                for (String s1 : templateLawVO.getItems()) {
                    if(!isCommonLaw(map, s, templateLawVO.getName(),s1)){
                        templateLawVO1.setName(templateLawVO.getName());
                        templateLawVO1.addItem(s1);
                    }
                }
                if(templateLawVO1.getName() != null)
                    unique.add(templateLawVO1);
            }
            if(unique.size() != 0)
                target.put(s, unique);
        }
        return target;
    }

    private static boolean isCommonLaw(HashMap<String, List<TemplateLawVO>> map, String classname, String lawname, String item){
        for (String s : map.keySet()) {
            if(!s.equals(classname)){
                List<TemplateLawVO> templateLawVOS = map.get(s);
                for (TemplateLawVO templateLawVO : templateLawVOS) {
                    if(templateLawVO.getName().equals(lawname) && templateLawVO.containsItem(item)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static List<TemplateLawVO> getUniqueLaws(String name){
        if(uniqueLaws.containsKey(name)){
            return uniqueLaws.get(name);
        }
        return new ArrayList<>();
    }

    public static String getPossibleType(List<WscpfxgcFtModel> wscpfxgcFtModelList){
        for (String s : uniqueLaws.keySet()) {
            List<TemplateLawVO> templateLawVOS = uniqueLaws.get(s);
            for (WscpfxgcFtModel wscpfxgcFtModel : wscpfxgcFtModelList) {
                if(matchLaws(templateLawVOS, wscpfxgcFtModel)){
                    return s;
                }
            }
        }
        return ParseMap.NOT_DETERMINED;
    }

    public static boolean matchLaws(List<TemplateLawVO> templateLawVOS,WscpfxgcFtModel wscpfxgcFtModel){
        for (TemplateLawVO templateLawVO : templateLawVOS) {
            String name = templateLawVO.getName();
            name = name.substring(1, name.length() - 1);
            if(wscpfxgcFtModel.getFlftmc().equals(name)){
                HashMap<String, String> ftMap = wscpfxgcFtModel.getFtMap();
                for (String key : ftMap.keySet()) {
                    String s = key + "条" + (ftMap.get(key).equals("没有款目") ? "":ftMap.get(key) );
                    return templateLawVO.containsItem(s);
                }
            }else{
                return false;
            }
        }
        return false;
    }

    public static List<TemplateLawVO> getTemplateLaws(String name){
        if(map.containsKey(name)){
            return map.get(name);
        }
        return new ArrayList<>();
    }

    public static List<TemplateLawVO> getUniqueLawsDetail(String name){
        if(uniqueLawsDetail.containsKey(name)){
            return uniqueLawsDetail.get(name);
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(map.size());
        System.out.println(uniqueLaws);
        System.out.println(uniqueLaws.keySet().size());
        System.out.println(uniqueLawsDetail);
        System.out.println(uniqueLawsDetail.size());
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (String s : uniqueLaws.keySet()) {
//            stringBuilder.append(s);
//            stringBuilder.append("\n");
//            List<TemplateLawVO> templateLawVOS = uniqueLaws.get(s);
//            int count = 0;
//            for (TemplateLawVO templateLawVO : templateLawVOS) {
//                count+=templateLawVO.getItems().size();
//            }
//            stringBuilder.append(" 独有:");
//            stringBuilder.append(count);
//            stringBuilder.append("条\n");
//        }
//
//        for (String s : map.keySet()) {
//            stringBuilder.append(s);
//            stringBuilder.append("\n");
//            List<TemplateLawVO> templateLawVOS = map.get(s);
//            int count = 0;
//            for (TemplateLawVO templateLawVO : templateLawVOS) {
//                count += templateLawVO.getItems().size();
//            }
//            stringBuilder.append(" 总计:");
//            stringBuilder.append(count);
//            stringBuilder.append("条\n");
//            if(uniqueLawsDetail.containsKey(s)){
//                templateLawVOS = uniqueLawsDetail.get(s);
//                for (TemplateLawVO templateLawVO : templateLawVOS) {
//                    stringBuilder.append(" 独有:");
//                    stringBuilder.append(templateLawVO.getItems().size());
//                    stringBuilder.append("条\n");
//                }
//            }
//        }
//        IOHelper.write("src/main/resources/templatelaws.txt",stringBuilder.toString());
    }

    public static List<TemplateLawVO> getLawList(List<String> content){
        List<String> lawList = null;
        for (String s : content) {
            if (s.contains("依照")) {
                lawList = getLaws(s);
                if (s.contains("裁定如下")) {
                    break;
                }
            }
        }
        List<TemplateLawVO> lawVOS = new ArrayList<>();
        String zhNum = "零一二三四五六七八九十百/";
        for (String s : lawList) {
            TemplateLawVO templateLawVO = new TemplateLawVO();
            int i = 0;
            while (i < s.toCharArray().length){
                if(s.charAt(i) == '《'){
                    //法律名称
                    int start = i;
                    while (s.charAt(i) != '》'){
                        i++;
                    }
                    if(templateLawVO.getName() != null)
                        lawVOS.add(templateLawVO);
                    templateLawVO.setName(s.substring(start, i)+"》");
                }
                if(s.charAt(i) == '第'){
                    //条目
                    int start = i;
                    i++;
                    while (zhNum.contains(""+s.charAt(i))){
                        i++;
                    }
                    if(s.charAt(i) != '条'){
                        i++;
                        continue;
                    }
                    i++;
                    if(i< s.toCharArray().length&&s.charAt(i)=='第'){
                        i++;
                        while (zhNum.contains(""+s.charAt(i))){
                            i++;
                        }
                        i++;
                    }
                    int tag = i;
                    if(i< s.toCharArray().length&&s.charAt(i)=='第'){
                        i++;
                        while (zhNum.contains(""+s.charAt(i))){
                            i++;
                        }
                        if(s.charAt(i) != '项'){
                            i = tag;
                        }else{
                            i++;
                        }
                    }
                    templateLawVO.addItem(s.substring(start,i));
                }
                i++;
            }
            if(templateLawVO.getName() != null && !lawVOS.contains(templateLawVO))
                lawVOS.add(templateLawVO);
        }
        return lawVOS;
    }

    private static List<String> getLaws(String str) {
        List<String> laws = new ArrayList<>();
        int startLaw = str.indexOf('《');
        str = str.substring(startLaw, str.length() - 8);
        int start = 0;
        for (int i = 0; i < str.toCharArray().length - 1; i++) {
            char c = str.charAt(i);
            if (c == '、' && str.charAt(i + 1) == '《') {
                String law = str.substring(start, i);
                laws.add(law);
                start = i + 1;
            }
        }
        laws.add(str.substring(start));
        return laws;
    }

}
