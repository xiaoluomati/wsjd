package nju.software.wsjx.parse;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.*;
import nju.software.wsjx.parserule.wsajjbqkparserule.XsesAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.XszsCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.XszsCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.XszsSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.XszsSsjlParseRule;
import nju.software.wsjx.util.ListToString;

public class ParseXszsSegment extends ParseSegment implements ParseflexibleSegment{

	@Override
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,
			List<WssscyrModel> wssscyrModellist) {
		// TODO Need to change to Xszs
		ajjbqkParseRule = new XsesAjjbqkParseRule();
		return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
	}

	

	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,
			List<WssscyrModel> wssscyrModellist) {
		cpjgParseRule = new XszsCpjgParseRule();
		return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
	}

	@Override
	public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
		sscyrParseRule = new XszsSscyrParseRule();
		return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
	}

	@Override
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		ssjlParseRule = new XszsSsjlParseRule();
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
		//Ìî³äwsModelµÄ¸÷¸öString¶ÎÂä
		fillWsModelSegment(wsModel, wsAnalyse);

		return wsModel;
	}

	@Override
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		cpfxgcParseRule = new XszsCpfxgcParseRule();
		return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
	}

}
