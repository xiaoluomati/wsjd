package nju.software.wsjx.parse;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.*;
import nju.software.wsjx.parserule.wsajjbqkparserule.MsysAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.MsgxCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.SscjrCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.MsysSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.SscjrSsjlParseRule;

import java.util.List;

/**
 * Created by zhuding on 2018/3/25.
 */
public class ParseSscjrSegment extends DefaultParseSegment{

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) {
        ajjbqkParseRule = new MsysAjjbqkParseRule();
        return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
    }

    @Override
    public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
        cpfxgcParseRule = new MsgxCpfxgcParseRule();
        return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
    }

    @Override
    public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) {
        cpjgParseRule = new SscjrCpjgParseRule();
        return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
    }

    @Override
    public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
        sscyrParseRule = new MsysSscyrParseRule();
        return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
    }

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) {
        ssjlParseRule = new SscjrSsjlParseRule();
        return ssjlParseRule.jxWsssjlModel(wssscyrModellist,wsssjl);
    }

}
