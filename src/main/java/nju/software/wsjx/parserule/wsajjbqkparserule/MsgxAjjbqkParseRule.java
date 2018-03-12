package nju.software.wsjx.parserule.wsajjbqkparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.List;

/**
 * Created by zhuding
 */
public class MsgxAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule {

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
        MsysAjjbqkParseRule msysAjjbqkParseRule = new MsysAjjbqkParseRule();
        WsajjbqkModel wsajjbqkModel = msysAjjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
        List<String> ajjbqk = wsAnalyse.getAjjbqk();
        if(ajjbqk==null||ajjbqk.size()==0)
            return wsajjbqkModel;

        for (String s : ajjbqk) {
            if(s.contains("±¨Çë")){
                wsajjbqkModel.setBqfylyd(s);
            }
            else if(s.contains("ÒÆËÍ")){
                wsajjbqkModel.setYsfylyd(s);
            }
        }
        return wsajjbqkModel;
    }
}
