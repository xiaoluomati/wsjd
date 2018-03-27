package nju.software.wsjx.parse;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.*;
import nju.software.wsjx.parserule.wsajjbqkparserule.MsysAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.ZjCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.ZjCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.MsysSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.ZjSsjlParseRule;

import java.util.List;

/**
 * Created by zhuding on 2018/3/27.
 */
public class ParseZjSegment extends DefaultParseSegment {

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) {
        ajjbqkParseRule = new MsysAjjbqkParseRule();
        return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
    }

    @Override
    public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
        cpfxgcParseRule = new ZjCpfxgcParseRule();
        return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
    }

    @Override
    public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) {
        cpjgParseRule = new ZjCpjgParseRule();
        return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
    }

    @Override
    public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
        sscyrParseRule = new MsysSscyrParseRule();
        return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
    }

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) {
        ssjlParseRule = new ZjSsjlParseRule();
        return ssjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
    }
}
