package nju.software.wsjx.parserule.wssscyrparserule;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

/**
 * 诉讼参与人解析
 * @author lr12
 *
 */
public interface SscyrParseRule {
	public List<WssscyrModel> jxWssscyrModelList(WsAnalyse wsAnalyse) throws ParseException;
}