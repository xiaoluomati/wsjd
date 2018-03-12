package nju.software.wsjx.parserule.wsssjlparserule;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

import java.util.List;

/**
 * Created by zhuding
 */
public class MsgxSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) throws ParseException {
        MsysSsjlParseRule msysSsjlParseRule = new MsysSsjlParseRule();
        WsssjlModel wsssjlModel = msysSsjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
        String[] contentArray = wsssjl.split("，|,|\\.|。|、|;|；");
        //原告和被告
        String ay = wsssjlModel.getAy();
        for (String s : contentArray) {
            if (s.contains("原告") && s.contains("被告")){
                int index = s.indexOf("与");
                wsssjlModel.setYg(s.substring(s.indexOf("原告") + 2, index));
                wsssjlModel.setBg(s.substring(s.indexOf("被告") + 2, s.indexOf(ay)));
                break;
            }
        }
        //立案法院
        for (String s : contentArray) {
            if (s.contains("于") && s.contains("立案")){
                wsssjlModel.setLafy(s.substring(0,s.indexOf("于")));
                break;
            }
        }
        //上诉人和上诉裁定
        for (String s : contentArray) {
            if (s.contains("上诉人")){
                wsssjlModel.setSsr(s.substring(s.indexOf("上诉人") + 3,s.indexOf("因")));
                break;
            }
        }
        for (String s : contentArray) {
            if (s.contains("民事裁定") && s.contains("人民法院") && s.contains("不服")){
                wsssjlModel.setSscdfymc(s.substring(s.indexOf("不服") + 2, s.indexOf("人民法院") + 4));
                wsssjlModel.setSscdah(s.substring(s.indexOf("人民法院") + 4,s.indexOf("民事裁定")));
                break;
            }
        }
        return wsssjlModel;
    }
}
