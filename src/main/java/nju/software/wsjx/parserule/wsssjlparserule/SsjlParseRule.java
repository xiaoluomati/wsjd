package nju.software.wsjx.parserule.wsssjlparserule;

import java.util.List;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

/**
 * ËßËÏ¼ÇÂ¼½âÎö
 * @author lr12
 *
 */
public interface SsjlParseRule {
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
                                     String wsssjl) throws ParseException;
	
}