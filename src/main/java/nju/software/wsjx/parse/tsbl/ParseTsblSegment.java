package nju.software.wsjx.parse.tsbl;

import nju.software.wsjx.model.tsblModel.TsblModel;
import nju.software.wsjx.parserule.tsblparserule.commonparserule.CommonParseRule;

import java.util.List;
import java.util.Map;

/**
 * ½âÎöÍ¥Éó±ÊÂ¼¶ÎÂä
 * @author lr12
 *
 */
public  class ParseTsblSegment implements ParseTsblFixedSegment{

	protected List<String> paragraphs;
	
	//×¢²á¶ÎÂä
	public void registerParagraphs(List<String> paragraphs){
		this.paragraphs=paragraphs;
	}
	
	@Override
	public Map<String, String> parseFixedSegment(List<String> paragraphs) {
		
		return CommonParseRule.parseCommon(paragraphs);
	}

	
	public TsblModel tranformToTsblModel(){
		TsblModel tsblModel=new TsblModel();
		Map<String, String> map=parseFixedSegment(paragraphs);
		if(map==null)
		return tsblModel;
		tsblModel.setKtdd(map.get("ktdd"));
		tsblModel.setKtsj(map.get("ktsj"));
		tsblModel.setWsmc(map.get("wsmc"));
		return tsblModel;
	}
    //public abstract 
}
