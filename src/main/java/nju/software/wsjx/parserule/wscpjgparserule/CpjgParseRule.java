package nju.software.wsjx.parserule.wscpjgparserule;


import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.AjlxEnum;
import nju.software.wsjx.model.Enum.ParseEnum;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.model.xs.XsPjjgModel;

/**
 * ²ÃÅÐ½á¹û½âÎö
 * @author lr12
 *
 */
public interface CpjgParseRule {
	/**
	 * 
	 * @param wsAnalyse
	 * @param wssscyrModellist
	 * @return
	 * @throws ParseException
	 */
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException;
}