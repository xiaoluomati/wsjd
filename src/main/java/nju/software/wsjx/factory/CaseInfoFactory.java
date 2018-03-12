package nju.software.wsjx.factory;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.ParseEnum;
import nju.software.wsjx.model.caseinfo.BaseCaseInfo;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.parse.ParseSegment;

/**
 * 使用反射动态加载解析实体类和解析生成的具体文档
 * 案件信息工厂
 * @author lr12
 *
 */
public class CaseInfoFactory {

	public static BaseCaseInfo getCaseinfo(String type,WsAnalyse wsAnalyse) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		StringBuilder pareseName=new StringBuilder();
		pareseName.append("nju.software.wsjx.parse.");
		ParseEnum parseEnum=ParseEnum.getParseEnumByParse(type);
		if(parseEnum==null)
			throw new ParseException("无法找寻加载类");
		pareseName.append(parseEnum.getParseDocumentName());
		Class<?> parseDocumentClass = Class.forName(pareseName.toString());
		//创建解析类
		ParseSegment parseSegment = (ParseSegment) parseDocumentClass.newInstance();
		StringBuffer caseinfoName=new StringBuffer("nju.software.wsjx.model.caseinfo.");
		caseinfoName.append(parseEnum.getParseDocumentName());
		parseSegment.registerWsAnalyse(wsAnalyse);
		//创建解析文档
		Class<?> caseinfoClass = Class.forName(caseinfoName.toString());
		BaseCaseInfo baseCaseinfo=(BaseCaseInfo) caseinfoClass.newInstance();
		
		WsModel ws = parseSegment.transformToWsModel();
//		parseDocument.buildDocument(baseDocument);
		baseCaseinfo.generate(ws);
		return baseCaseinfo;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
	
	}
	
}
