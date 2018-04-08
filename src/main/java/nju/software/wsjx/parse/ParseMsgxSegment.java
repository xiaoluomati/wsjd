package nju.software.wsjx.parse;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.*;
import nju.software.wsjx.parserule.wsajjbqkparserule.MsgxAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.MsgxCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.MsysCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.MsysSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.MsgxSsjlParseRule;

import java.util.List;

/**
 * 民事管辖解析逻辑
 * Created by zhuding
 */
public class ParseMsgxSegment extends ParseSegment implements ParseflexibleSegment{

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) {
        ajjbqkParseRule = new MsgxAjjbqkParseRule();
        return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
    }

    @Override
    public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
        cpfxgcParseRule = new MsgxCpfxgcParseRule();
        return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
    }

    @Override
    public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) {
        cpjgParseRule = new MsysCpjgParseRule();
        return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
    }

    @Override
    public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
        sscyrParseRule = new MsysSscyrParseRule();
        return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
    }

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) {
        ssjlParseRule = new MsgxSsjlParseRule();
        return ssjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
    }

    @Override
    public WsModel transformToWsModel() {
        WswsModel wswsModel = jxWswsModel(wsAnalyse.getWs());
        List<WssscyrModel> wssscyrModels = jxWssscyrModel(wsAnalyse);
        WsssjlModel wsssjlModel = jxWsssjlModel(wssscyrModels,wsAnalyse.getSsjl());
        WsajjbqkModel wsajjbqkModel = jxWsajjbqkModel(wsAnalyse,wssscyrModels);
        WscpfxgcModel wscpfxgcModel = jxWscpfxgcModel(wsAnalyse);
        WscpjgModel wscpjgModel = jxWscpjgModel(wsAnalyse,wssscyrModels);
        WswwModel wswwModel = jxWswwModel(wsAnalyse.getWw());
        WsModel wsModel = new WsModel(wswsModel, wssscyrModels, wsajjbqkModel, wsssjlModel, wscpfxgcModel, wscpjgModel, wswwModel);

        return wsModel;
    }

//    public WsModel defaultTransformToWsModel(){
//        WswsModel wswsModel = jxWswsModel(wsAnalyse.getWs());
//        List<WssscyrModel> wssscyrModels = jxWssscyrModel(wsAnalyse);
//        WsssjlModel wsssjlModel = jxWsssjlModel(wssscyrModels,wsAnalyse.getSsjl());
//        WsajjbqkModel wsajjbqkModel = jxWsajjbqkModel(wsAnalyse,wssscyrModels);
//        WscpfxgcModel wscpfxgcModel = jxWscpfxgcModel(wsAnalyse);
//        WscpjgModel wscpjgModel = jxWscpjgModel(wsAnalyse,wssscyrModels);
//        WswwModel wswwModel = jxWswwModel(wsAnalyse.getWw());
//        WsModel wsModel = new WsModel(wswsModel, wssscyrModels, wsajjbqkModel, wsssjlModel, wscpfxgcModel, wscpjgModel, wswwModel);
//
//        //填充wsModel的各个String段落
//        fillWsModelSegment(wsModel, wsAnalyse);
//
//        return wsModel;
//    }
}
