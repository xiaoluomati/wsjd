package nju.software.wsjx.parserule.wswwparserule;

import java.util.List;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;

/**
 * 文尾解析
 * @author lr12
 *
 */
public interface WwParseRule {
	/**
	 * 父类的解析文书文尾接口
	 * @param wsww
	 * @return
	 */
	public WswwModel jxWswwModel(List<String> wsww) throws ParseException;
	
}