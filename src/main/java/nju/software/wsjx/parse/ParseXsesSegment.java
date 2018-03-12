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
import nju.software.wsjx.parserule.wsajjbqkparserule.XsesAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.XsesCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.XsesCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.XsesSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.XsesSsjlParseRule;
import nju.software.wsjx.util.ListToString;
/**
 * 刑事二审解析逻辑
 * @author wangzh
 *
 */
public class ParseXsesSegment extends ParseSegment implements ParseflexibleSegment{
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
		ajjbqkParseRule=new XsesAjjbqkParseRule();
		return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse,wssscyrModellist);
	}

	

	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		cpjgParseRule = new XsesCpjgParseRule();
		return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
	}

	@Override
	public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
		sscyrParseRule = new XsesSscyrParseRule();
		return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
	}

	@Override
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,String wsssjl) {
		ssjlParseRule = new XsesSsjlParseRule();
		return ssjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
	}

	@Override
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		cpfxgcParseRule = new XsesCpfxgcParseRule();		
		return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
	}
	

}
