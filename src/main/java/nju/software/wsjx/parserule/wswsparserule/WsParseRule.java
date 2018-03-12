package nju.software.wsjx.parserule.wswsparserule;

import java.util.List;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;

/**
 * 文首解析的规则定义，为了规范化，让父类的接口都跑出异常
 * @author lr12
 *
 */
public interface WsParseRule {

	/**
	 * 父类的解析文书文首接口
	 * @param wsws
	 * @return
	 */
	public WswsModel jxWswsModel(List<String> wsws) throws ParseException;
	
}
