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
import nju.software.wsjx.parserule.wsajjbqkparserule.MsysAjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.MsysCpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.MsysCpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.MsysSscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.MsysSsjlParseRule;
import nju.software.wsjx.util.ListToString;

/**
 * 民事一审解析逻辑
 * @author wangzh
 *
 */
public class ParseMsysSegment extends ParseSegment implements ParseflexibleSegment{

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
		ajjbqkParseRule=new MsysAjjbqkParseRule();
		return ajjbqkParseRule.jxWsajjbqkModel(wsAnalyse,wssscyrModellist);
	}


	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		cpjgParseRule = new MsysCpjgParseRule();
		return cpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
	}

	@Override
	public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
		sscyrParseRule = new MsysSscyrParseRule();
		return sscyrParseRule.jxWssscyrModelList(wsAnalyse);
	}

	@Override
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,String wsssjl) {
		ssjlParseRule = new MsysSsjlParseRule();
		return ssjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
	}

	@Override
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		cpfxgcParseRule = new MsysCpfxgcParseRule();		
		return cpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
	}
	

}
