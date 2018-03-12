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
import nju.software.wsjx.parserule.wsajjbqkparserule.XzysAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.XzysCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.CpjgParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.XzysCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.XzysSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.XzysSsjlParseRule;
import nju.software.wsjx.util.ListToString;

/**
 * 行政一审的解析逻辑
 * @author lr12
 *
 */
public class ParseXzysSegment extends ParseSegment implements ParseflexibleSegment{

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

	@Override
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		ajjbqkParseRule=new XzysAjjbqkParseRule();
		return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse,wssscyrModellist);
	}

	@Override
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		cpfxgcParseRule = new XzysCpfxgcParseRule();		
		return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
	}

	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		cpjgParseRule = new XzysCpjgParseRule();
		return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
	}

	@Override
	public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
		sscyrParseRule = new XzysSscyrParseRule();
		return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
	}

	@Override
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,String wsssjl) {
		ssjlParseRule = new XzysSsjlParseRule();
		return ssjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
	}
	
		

}
