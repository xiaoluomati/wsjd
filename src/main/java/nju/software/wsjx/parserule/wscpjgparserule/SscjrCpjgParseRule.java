package nju.software.wsjx.parserule.wscpjgparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.List;

/**
 * Created by zhuding on 2018/3/26.
 */
public class SscjrCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule {

    /**
     * @param wsAnalyse
     * @param wssscyrModellist
     * @return
     * @throws ParseException
     */
    @Override
    public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
        MsysCpjgParseRule msysCpjgParseRule = new MsysCpjgParseRule();
        WscpjgModel wscpjgModel = msysCpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
        List<String> cpjg = wsAnalyse.getCpjg();
        for (String s : cpjg) {
            if(s.contains("替代") && s.contains("参加诉讼") && s.contains("退出诉讼")){
                wscpjgModel.setTdr(s.substring(s.indexOf("准")+2, s.indexOf("替代")));
                wscpjgModel.setBtdr(s.substring(s.indexOf("替代")+2, s.indexOf("作为")));
                String s1 = s.substring(s.indexOf("本案") + 2, s.indexOf("参加诉讼"));
                if(s1.contains("原告")){
                    wscpjgModel.setSsdw("原告");
                }else if(s1.contains("被告")){
                    wscpjgModel.setSsdw("被告");
                }
                wscpjgModel.setTcssr(s.substring(s.indexOf("参加诉讼")+5, s.indexOf("退出诉讼")));
                break;
            }
        }
        return wscpjgModel;
    }
}
