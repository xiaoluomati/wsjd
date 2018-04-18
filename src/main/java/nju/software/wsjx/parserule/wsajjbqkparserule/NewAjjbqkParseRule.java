package nju.software.wsjx.parserule.wsajjbqkparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuding on 2018/3/31.
 */
public class NewAjjbqkParseRule {

    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist, WsajjbqkModel wsajjbqkModel)  {
        List<String> ajjbqk = wsAnalyse.getAjjbqk();
        List<String> tempAjjbqk = new ArrayList<>();
        for (String s : ajjbqk) {
            for (WssscyrModel wssscyrModel : wssscyrModellist) {
                if(s.contains(wssscyrModel.getSscyr()))
                    s = s.replaceAll(wssscyrModel.getSscyr(), wssscyrModel.getSssf());
            }
            tempAjjbqk.add(s);
        }

        if(ajjbqk.size()==0){
            return wsajjbqkModel;
        }

        List<List<String>> keySentence = new ArrayList<>();
        for (String s : tempAjjbqk) {
            String[] strings = s.split("\\.|。");
            List<String> list = new ArrayList<>();
            for (String string : strings) {

                if(string.contains("：")){
                    string = string.substring(0, string.indexOf("："));
                }
                if(string.contains(":")){
                    string = string.substring(0, string.indexOf(":"));
                }
                boolean isContainsFY = string.contains("法院")||string.contains("本院");
                boolean isContainsRD = string.contains("认为")||string.contains("审理")||string.contains("认定")||string.contains("查明")||string.contains("判决");
                boolean isContainsSSCYR = string.contains("上诉人")||string.contains("原告")||string.contains("被告");
                boolean isContainsSS = string.contains("辩称")||string.contains("诉称")||string.contains("提起上诉")||string.contains("请求");
                boolean isContainsFL = string.contains("《");
                if(((isContainsFY && isContainsRD) || (isContainsSSCYR && isContainsSS)) && !isContainsFL ){
                    list.add(string);
                }
            }
            keySentence.add(list);
        }


        int index = -1;//前审段落序号
        String[] types = new String[keySentence.size()];
        for (int i = 0; i < keySentence.size(); i++) {
            List<String> strings = keySentence.get(i);
            System.out.println(strings);
            String type = getQsType(strings);
            if(type != null){
                index = i;
                types[i] = type;
            }
        }
        for (int i = 0; i <= index; i++) {
            if(types[i] == null){
                List<String> strings = keySentence.get(i);
                for (String string : strings) {
                    if(isQSYGSC(string)){
                        types[i] = "前审原告诉称段";
                    }
                    if(isQSBGBC(string)){
                        types[i] = "前审被告辩称段";
                    }
                }
            }
        }
        for (int i = index == -1 ? 0 : index + 1; i < keySentence.size(); i++) {
            if(types[i] == null){
                List<String> strings = keySentence.get(i);
                for (String string : strings) {
                    if(isBSYGSC(string)){
                        types[i] = "上诉人诉称段";
                    }
                    if(isBSBGBC(string)){
                        types[i] = "被上诉人辩称段";
                    }
                    if(isBSSLD(string)){
                        types[i] = "本审审理段";
                    }
                }
            }
        }
        types = removeNull(types);
        for (String type : types) {
            System.out.print(type);
        }
        System.out.println();

        StringBuilder qsdl = new StringBuilder();
        for (int i = 0; i <= index; i++) {
            qsdl.append(ajjbqk.get(i));
        }
        wsajjbqkModel.setQsdl(qsdl.toString());
        StringBuilder bsdl = new StringBuilder();
        for (int i = index == -1 ? 0 : index + 1; i < keySentence.size(); i++) {
            bsdl.append(ajjbqk.get(i));
        }
        wsajjbqkModel.setBsdl(bsdl.toString());
        wsajjbqkModel.setQsygscd(getStringData(ajjbqk,types,"前审原告诉称段"));
        wsajjbqkModel.setQsbgbcd(getStringData(ajjbqk,types,"前审被告辩称段"));
        wsajjbqkModel.setQssld(getListData(ajjbqk,types,"前审审理段"));
        wsajjbqkModel.setQscmd(getStringData(ajjbqk,types,"前审查明事实段"));
        wsajjbqkModel.setQspjd(getStringData(ajjbqk,types,"前审判决段"));
        wsajjbqkModel.setSsrscd(getStringData(ajjbqk,types,"上诉人诉称段"));
        wsajjbqkModel.setBssrbcd(getListData(ajjbqk,types,"被上诉人辩称段"));
        wsajjbqkModel.setBssld(getListData(ajjbqk,types,"本审审理段"));
        return wsajjbqkModel;
    }

    private String getStringData(List<String> ajjbqk,String[] types, String name){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : getListData(ajjbqk, types, name)) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    private List<String> getListData(List<String> ajjbqk,String[] types, String name){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            if(types[i].equals(name)){
                strings.add(ajjbqk.get(i));
            }
        }
        return strings;
    }

    private static final String[] QS_TYPES = {"前审判决段","前审审理段","前审查明事实段","前审原告诉称段","前审被告辩称段"};

    private static final String[] BS_TYPES = {"上诉人诉称段","被上诉人辩称段","本审审理段"};

    private static String[] removeNull(String[] strings){
        for (int i = 0; i < strings.length; i++) {
            if(strings[i] == null){
                removeNull(strings, i);
            }
        }
        return strings;
    }

    private static String removeNull(String[] strings, int i){
        int preIndex = i - 1;
        if(preIndex >= 0 && preIndex < strings.length){
            if(strings[preIndex] != null){
                strings[i] = strings[preIndex];
            }else{
                strings[i] = removeNull(strings, preIndex);
            }
        }
        return strings[i];
    }

    private static boolean existNull(String[] strings){
        for (Object o : strings) {
            if(o == null){
                return true;
            }
        }
        return false;
    }
    
    protected String getQsType(List<String> strings){
        String type = null;
        for (String string : strings) {
            if(isQSCMD(string)){
                type = "前审查明事实段";
            }
            if (isQSSLD(string)){
                type = "前审审理段";
            }
            if(isQSPJD(string)){
                type = "前审判决段";
            }
        }
        return type;
    }

    protected boolean isQSCMD(String string){
        boolean contQSFY = string.contains("原审法院")||string.contains("前审法院")||string.contains("一审法院");
        boolean contCM = string.contains("查明");
        return contQSFY && contCM;
    }

    protected boolean isQSSLD(String string){
        boolean contQSFY = string.contains("原审法院")||string.contains("前审法院")||string.contains("一审法院");
        boolean contSL = string.contains("认为")||string.contains("认定");
        return contQSFY && contSL;
    }

    //原审段落最后一段
    protected boolean isQSPJD(String string){
        boolean contQSFY = string.contains("原审法院")||string.contains("前审法院")||string.contains("一审法院");
        boolean contPJ = string.contains("判决");
        return contQSFY && contPJ;
    }

    protected boolean isBSSLD(String string){
        boolean contQSFY = string.contains("本院");
        boolean contSL = string.contains("认为")||string.contains("审理")||string.contains("认定");
        return contQSFY && contSL;
    }

    protected boolean isQSYGSC(String string){
        boolean contQSYG = string.contains("原告");
        boolean contQQ = string.contains("请求")||string.contains("诉称");
        return contQSYG && contQQ;
    }

    protected boolean isQSBGBC(String string){
        boolean contQSYG = string.contains("被告");
        boolean contQQ = string.contains("答辩")||string.contains("辩称");
        return contQSYG && contQQ;
    }

    protected boolean isBSYGSC(String string){
        boolean contQSYG = string.contains("上诉人");
        boolean contQQ = string.contains("请求")||string.contains("诉称");
        return contQSYG && contQQ;
    }

    protected boolean isBSBGBC(String string){
        boolean contQSYG = string.contains("被上诉人");
        boolean contQQ = string.contains("答辩")||string.contains("辩称");
        return contQSYG && contQQ;
    }
}
