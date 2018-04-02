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
public class EsAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule {

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
        List<String> ajjbqk = wsAnalyse.getAjjbqk();
        List<String> tempAjjbqk = new ArrayList<>();
        for (String s : ajjbqk) {
            for (WssscyrModel wssscyrModel : wssscyrModellist) {
                if(s.contains(wssscyrModel.getSscyr()))
                    s = s.replaceAll(wssscyrModel.getSscyr(), wssscyrModel.getSssf());
            }
            tempAjjbqk.add(s);
        }

        WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();

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



        for (int i = 0; i < keySentence.size(); i++) {
            List<String> strings = keySentence.get(i);
            System.out.println(strings);

        }



        return wsajjbqkModel;
    }
}
