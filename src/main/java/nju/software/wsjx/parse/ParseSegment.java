package nju.software.wsjx.parse;

import java.util.List;








import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.*;
import nju.software.wsjx.parserule.wsajjbqkparserule.AjjbqkParseRule;
import nju.software.wsjx.parserule.wscpfxgcparserule.CpfxgcParseRule;
import nju.software.wsjx.parserule.wscpjgparserule.CpjgParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.SscyrParseRule;
import nju.software.wsjx.parserule.wsssjlparserule.SsjlParseRule;
import nju.software.wsjx.parserule.wswsparserule.GeneralWsParseRule;
import nju.software.wsjx.parserule.wswsparserule.WsParseRule;
import nju.software.wsjx.parserule.wswwparserule.GeneralWwParseRule;
import nju.software.wsjx.parserule.wswwparserule.WwParseRule;
import nju.software.wsjx.util.ListToString;


/**
 * 解析文档抽象类
 * @author lr12
 *
 */
public abstract class ParseSegment implements ParseFixedSegment{

	 WsAnalyse wsAnalyse;
	 WsParseRule wsParseRule;
	 WwParseRule wwParseRule;
	 SsjlParseRule ssjlParseRule;
	 SscyrParseRule sscyrParseRule;
	 AjjbqkParseRule ajjbqkParseRule;
	 CpjgParseRule cpjgParseRule;
	 CpfxgcParseRule cpfxgcParseRule;
	
	//注册分段的实体类
	public void registerWsAnalyse(WsAnalyse wsAnalyse){
		this.wsAnalyse=wsAnalyse;
	}
	
	//解析后转换成文书Model
	public abstract WsModel transformToWsModel();


	@Override
	public WswsModel jxWswsModel(List<String> wswsModel) {
		wsParseRule =new GeneralWsParseRule();
		return wsParseRule.jxWswsModel(wswsModel);
	}
	
	@Override
	public WswwModel jxWswwModel(List<String> wswwModel){
		wwParseRule = new GeneralWwParseRule();
		return wwParseRule.jxWswwModel(wswwModel);
	}
	/**
	 * 填充段落
	 * @param wsModel
	 * @param wsAnalyse
	 */
	public void fillWsModelSegment(WsModel wsModel,WsAnalyse wsAnalyse){
		wsModel.setWswsSegment(ListToString.List2String(wsAnalyse.getWs()));
		wsModel.setWssscyrSegment(wsAnalyse.getSscyr());
		wsModel.setWsssjlSegment(wsAnalyse.getSsjl());
		wsModel.setWsajjbqSegment(ListToString.List2String(wsAnalyse.getAjjbqk()));
		wsModel.setWscpfxgcSegment(ListToString.List2String(wsAnalyse.getCpfxgc()));
		wsModel.setWscpjgSegment(ListToString.List2String(wsAnalyse.getCpjg()));
		wsModel.setWswwSegment(ListToString.List2String(wsAnalyse.getWw()));
		wsModel.setWsqw(wsAnalyse.getWsnr());
		wsModel.setWsfl(ListToString.List2String(wsAnalyse.getFl()));
	}
}
