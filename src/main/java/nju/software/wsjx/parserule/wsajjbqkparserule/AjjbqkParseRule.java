package nju.software.wsjx.parserule.wsajjbqkparserule;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

/**
 * 案件基本情况解析
 * @author lr12
 *
 */
public interface AjjbqkParseRule {
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException;
}
