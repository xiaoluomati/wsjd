package nju.software.wsjx.factory;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.ParseEnum;
import nju.software.wsjx.model.caseinfo.BaseCaseInfo;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.parse.ParseSegment;

/**
 * 使用反射动态加载解析实体类和解析生成的具体文档
 * 文文书model工厂
 * @author lr12
 *
 */
public class WsModelFactory {
	public static WsModel getWsModel(String parseName,WsAnalyse wsAnalyse) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
	
		StringBuilder pareseName=new StringBuilder();
		pareseName.append("nju.software.wsjx.parse.");
		pareseName.append(parseName);
//		ParseEnum parseEnum=ParseEnum.getParseEnumByParse(parseName);
//		if(parseEnum==null)
//			throw new ParseException("无法找寻加载类，未定义该解析类型");
//		pareseName.append(parseEnum.getParseDocumentName());
		Class<?> parseDocumentClass = Class.forName(pareseName.toString());
		//创建解析类
		ParseSegment parseCaseinfo = (ParseSegment) parseDocumentClass.newInstance();
		parseCaseinfo.registerWsAnalyse(wsAnalyse);
		WsModel ws = parseCaseinfo.transformToWsModel();
		return ws;
	}
}
