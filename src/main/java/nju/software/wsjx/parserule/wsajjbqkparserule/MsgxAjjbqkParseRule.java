package nju.software.wsjx.parserule.wsajjbqkparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by zhuding
 */
public class MsgxAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule {

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
        MsysAjjbqkParseRule msysAjjbqkParseRule = new MsysAjjbqkParseRule();
        NewAjjbqkParseRule newAjjbqkParseRule = new NewAjjbqkParseRule();
        WsajjbqkModel wsajjbqkModel = msysAjjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
        wsajjbqkModel = newAjjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist, wsajjbqkModel);
        List<String> ajjbqk = wsAnalyse.getAjjbqk();
        if(ajjbqk==null||ajjbqk.size()==0)
            return wsajjbqkModel;

        for (String s : ajjbqk) {
            if(s.contains("报请本院指定管辖")){
                wsajjbqkModel.setBqfylyd(s);
            }
            else if(Pattern.compile("移送.*人民法院审理").matcher(s).find()){
                wsajjbqkModel.setYsfylyd(s);
            }
        }
        return wsajjbqkModel;
    }
}
