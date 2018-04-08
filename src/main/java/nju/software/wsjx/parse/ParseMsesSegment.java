package nju.software.wsjx.parse;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.parserule.wsajjbqkparserule.MsesAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.CpfxgcParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.MsesCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.MsesCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.MsesSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.MsesSsjlParseRule;
import nju.software.wsjx.util.ListToString;

/**
 * 民事二审解析逻辑
 * @author wangzh
 *
 */
public class ParseMsesSegment extends ParseSegment implements ParseflexibleSegment{

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

	@Override
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		ajjbqkParseRule=new MsesAjjbqkParseRule();
		return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse,wssscyrModellist);
	}

	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		cpjgParseRule = new MsesCpjgParseRule();
		return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
	}

	@Override
	public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
		sscyrParseRule = new MsesSscyrParseRule();
		return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
	}

	@Override
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,String wsssjl) {
		ssjlParseRule = new MsesSsjlParseRule();
		return ssjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
	}

	@Override
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		// TODO Auto-generated method stub
		cpfxgcParseRule = new MsesCpfxgcParseRule();
		return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
	}
	

}
