package nju.software.wsjx.parse;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.*;
import nju.software.wsjx.parserule.wsajjbqkparserule.XzesAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.XzzsCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.XzzsCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.XzzsSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.XzzsSsjlParseRule;
import nju.software.wsjx.util.ListToString;

public class ParseXzzsSegment extends ParseSegment implements ParseflexibleSegment{

	@Override
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,
			List<WssscyrModel> wssscyrModellist) {
		// TODO need to change to 行政再审
		ajjbqkParseRule = new XzesAjjbqkParseRule();
		return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
	}

	@Override
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		cpfxgcParseRule = new XzzsCpfxgcParseRule();
		return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
	}

	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,
			List<WssscyrModel> wssscyrModellist) {
		cpjgParseRule = new XzzsCpjgParseRule();
		return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
	}

	@Override
	public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
		sscyrParseRule = new XzzsSscyrParseRule();
		return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
	}

	@Override
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		ssjlParseRule = new XzzsSsjlParseRule();
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
		//填充wsModel的各个String段落
		fillWsModelSegment(wsModel, wsAnalyse);

		return wsModel;
	}

}
