package nju.software.wsjx.parse;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.*;
//import nju.software.wsjx.parserule.wsajjbqkparserule.MsesAjjbqkParseRule;
import nju.software.wsjx.parserule.wsajjbqkparserule.MszsAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.MszsCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.MszsCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.MszsSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.MszsSsjlParseRule;
import nju.software.wsjx.util.ListToString;

/**
 * 民事再审
 * @author 服兰
 *
 */
public class ParseMszsSegment extends ParseSegment implements ParseflexibleSegment{

	@Override
	public WsModel transformToWsModel() {
		WswsModel wswsModel = jxWswsModel(wsAnalyse.getWs());
		List<WssscyrModel> wssscyrModels = jxWssscyrModel(wsAnalyse);
		WsssjlModel wsssjlModel = jxWsssjlModel(wssscyrModels,wsAnalyse.getSsjl());
		WsajjbqkModel wsajjbqkModel = jxWsajjbqkModel(wsAnalyse,wssscyrModels);
		WscpfxgcModel wscpfxgcModel = jxWscpfxgcModel(wsAnalyse);
		WscpjgModel wscpjgModel = jxWscpjgModel(wsAnalyse,wssscyrModels);
		WswwModel wswwModel = jxWswwModel(wsAnalyse.getWw());
		WsModel wsModel = new WsModel(wswsModel,wssscyrModels, wsajjbqkModel, wsssjlModel, wscpfxgcModel, wscpjgModel, wswwModel);

		return wsModel;
	}
	
	@Override
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,
			List<WssscyrModel> wssscyrModellist) {
		// TODO need to change to 民事再审
		//ajjbqkParseRule = new MsesAjjbqkParseRule();
		ajjbqkParseRule = new MszsAjjbqkParseRule();
		return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
	}

	

	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,
									 List<WssscyrModel> wssscyrModellist) {
		cpjgParseRule = new MszsCpjgParseRule();
		return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
	}

	@Override
	public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
		sscyrParseRule = new MszsSscyrParseRule();
		return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
	}

	@Override
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
									 String wsssjl) {
		ssjlParseRule = new MszsSsjlParseRule();
		return ssjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
	}

	@Override
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		cpfxgcParseRule = new MszsCpfxgcParseRule();
		return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
	}

}
