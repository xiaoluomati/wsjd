package nju.software.wsjx.parserule.wsssjlparserule;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

import java.util.List;

/**
 * Created by zhuding on 2018/3/25.
 */
public class SscjrSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) throws ParseException {
        SsjlParseRule msysSsjlParseRule = new MsysSsjlParseRule();
        WsssjlModel wsssjlModel = msysSsjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
        String[] contentArray = wsssjl.split("，|,|\\.|。|、|;|；");
        //原告和被告
        String ay = wsssjlModel.getAy();
        for (String s : contentArray) {
            if (s.contains("原告") && s.contains("被告") && s.contains("一案")){
                wsssjlModel.setYg(s.substring(s.indexOf("原告") + 2, s.indexOf("与")));
                wsssjlModel.setBg(s.substring(s.indexOf("被告") + 2, s.indexOf(ay)));
                break;
            } else if (s.contains("原告") && s.contains("被告")){
                wsssjlModel.setYg(s.substring(s.indexOf("原告") + 2, s.indexOf("与")));
                wsssjlModel.setBg(s.substring(s.indexOf("被告") + 2));
                break;
            }
        }

        contentArray = wsssjl.split("\\.|。");
        for (String s : contentArray) {
            if(s.contains("变更当事人")){
                String[] strings = s.split("，|,|;|；");
                for (String string : strings) {
                    if(string.contains("年") && string.contains("月") && string.contains("日")){
                        wsssjlModel.setBgsqrq(string.substring(string.indexOf("年")-4, string.indexOf("日")+1));
                    }else if(string.contains("变更当事人") && s.contains("申请")){
                        wsssjlModel.setBgsqr(string.substring(0, string.indexOf("向")));
                    }
                }
                break;
            }
        }
        return wsssjlModel;
    }
}
