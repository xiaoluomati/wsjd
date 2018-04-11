package nju.software.preProcess;

import nju.software.util.IOHelper;
import nju.software.wsjx.util.ListToString;

import java.io.File;
import java.util.*;

/**
 * Created by zhuding on 2018/4/10.
 */
public class PreClassifyKeyword {

    private static final String KEYWORDS_PATH = "src/main/resources/templatekeywords.txt";

    private PreClassifyKeyword(){}

    private static HashMap<String,String[]> map = new HashMap<>();

    static {
        List<String> strings = IOHelper.read(KEYWORDS_PATH);
        for (String string : strings) {
            String name = string.substring(0, string.indexOf(":"));
            String[] keywords = string.substring(string.indexOf("[")+1, string.indexOf("]")).split(",");
            map.put(name, keywords);
        }
    }

    public static String getPossibleType(String content){
        String result = "不能确定";
        double maxRate = 0;
        for (String s : map.keySet()) {
            String[] strings = map.get(s);
            double count = 0;
            for (String string : strings) {
                if(content.contains(string)){
                    count++;
                }
            }
            if(count / strings.length > maxRate){
                maxRate = count / strings.length;
                result = s;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getPossibleType("黑龙江省哈尔滨市中级人民法院\n" +
                "民 事 裁 定 书\n" +
                "（2018）黑01民辖4号\n" +
                "原告：徐佑武，男，1963年8月25日出生，汉族，住湖北省孝感市孝南区。\n" +
                "被告：时美良，男，1957年10日15日出生，汉族，住黑龙江省哈尔滨市南岗区。\n" +
                "原告徐佑武与被告时美良劳务合同纠纷一案，哈尔滨市南岗区人民法院于2017年7月21日立案。\n" +
                "徐佑武诉称，2010年6月5日，其与时美良签订劳务分包合同书。工程地点在黑龙江省哈尔滨市××乡××村，工程名称：私人住宅，工程范围：内、外墙抹灰、外墙粘苯板、外墙粘砖、室内地面，另外加上民主村围墙抹灰、粘砖、排水坡、台阶抹灰，工程价款共计60万元整。施工过程中及施工后给付了部分人工费。后其又为时美良平房9#楼进行抹灰施工。2012年6月12日，经双方对账确认时美良欠其人工费31万元整。此后，时美良先后给付人工费1.8万元，用车抵付7万元，尚欠22.2万元未付，其虽经多次索要，但均被拒绝，故诉至原审法院，请求：一、判令时美良给付拖欠劳务报酬22.2万元及利息；二、诉讼费用由时美良承担。\n" +
                "哈尔滨市南岗区人民法院认为，本案系建设工程施工合同纠纷，属不动产纠纷，应适用专属管辖的规定。双方的分包协议中确认的工程地址位于哈尔滨市××乡红星村，故该院对本案无管辖权。于2017年10月18日裁定：本案移送哈尔滨市道外人民法院审理。\n" +
                "2018年1月3日，哈尔滨市道外区人民法院以本案为劳务合同纠纷，不是建设工程施工合同纠纷，不属于专属管辖案件，时美良的住所地位于黑龙江省××南岗区，哈尔滨市南岗区人民法院对本案有管辖权，哈尔滨市南岗区人民法院将本案移送至该院审理错误为由，报请本院指定管辖。\n" +
                "本院经审查认为，根据时美良与徐佑武签订的《合同书》的内容以及徐佑武向原审法院起诉时提交的起诉状上所载明的诉讼请求，本案应系劳务合同纠纷。本案双方当事人虽在《合同书》中约定：“如果发生合同纠纷，可选择合同履行劳动仲裁部门或向人民法院起诉。”但该约定属约定不明，依法不能作为确定本案管辖法院的依据。《最高人民法院关于适用〈中华人民共和国民事诉讼法〉的解释》第十八条第二款规定：“合同对履行地点没有约定或者约定不明确，争议标的为给付货币的，接收货币一方所在地为合同履行地；交付不动产的，不动产所在地为合同履行地；其他标的，履行义务一方所在地为合同履行地。即时结清的合同，交易行为地为合同履行地。”本案双方当事人对合同履行地未做明确约定，且争议标的为给付货币，故依照上述法律规定，本案作为接收货币一方的徐佑武的住所地应为本案的合同履行地。徐佑武的住所地位于湖北省××南区，故本案的合同履行地应为湖北省××南区。《中华人民共和国民事诉讼法》第二十三条规定：“因合同纠纷提起的诉讼，由被告住所地或者合同履行地人民法院管辖。”本案时美良的住所地位于黑龙江省××南岗区，合同履行地位于湖北省××南区，故依照上述法律规定，哈尔滨市南岗区人民法院与孝感市孝南区人民法院对本案均享有管辖权，徐佑武选择向时美良的住所地哈尔滨市南岗区人民法院提起诉讼符合法律规定，哈尔滨市南岗区人民法院对本案应予受理并进行审理。哈尔滨市南岗区人民法院以本案系建筑工程施工合同纠纷，应适用专属管辖为由将案件移送哈尔滨市道外区人民法院处理，认定事实不清，适用法律错误，本院予以纠正。哈尔滨市道外区人民法院报请指定管辖的意见成立，本院予以支持。\n" +
                "依照《中华人民共和国民事诉讼法》第三十六条规定，裁定如下：\n" +
                "本案由哈尔滨市南岗区人民法院审理。\n" +
                "本裁定一经作出即生效。\n" +
                "审判长　曲 海 涛\n" +
                "审判员　周 志 杰\n" +
                "审判员　端木繁辉\n" +
                "\n" +
                "二〇一八年一月十六日\n" +
                "书记员　马 欣 悦\n" +
                "\n"));
    }

//    public static void main(String[] args) {
//        File file = new File("template");
//        File[] files = file.listFiles();
//        KeywordsMaker keywordsMaker = new KeywordsMaker();
//        for (File f : files) {
//            if(f.isDirectory()){
//                String classname = f.getName();
//                System.out.println(classname);
//
//                List<String> list = new ArrayList<>();
//                File[] files1 = f.listFiles();
//                for (File file1 : files1) {
//                    String name = "template/" + classname+"/" +file1.getName();
//                    if(file1.getName().contains("裁定")){
//                        List<String> read = IOHelper.read(name);
//                        list.add(ListToString.List2String(read));
//                    }
//
//                }
//
//
//                TemplatePrePro templatePrePro = new TemplatePrePro(list);
//                keywordsMaker.addData(classname, templatePrePro.getWordNums());
//            }
//        }
//        HashMap<String, HashMap<String, Integer>> keywords = keywordsMaker.getKeywords();
//
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String s : keywords.keySet()) {
//            System.out.println(s+":"+keywords.get(s).keySet());
//            stringBuilder.append(s+":"+keywords.get(s).keySet()+"\n");
//        }
//        IOHelper.write("src/main/resources/templatekeywords.txt",stringBuilder.toString());
//    }

}
