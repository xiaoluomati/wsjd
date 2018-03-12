package nju.software.wsjx.model.Enum;

import nju.software.wsjx.util.StringUtil;

public enum ParseEnum {
	MSYS("民事一审","Msys","ParseMsysSegment","MsysCaseInfo","",""),
	MSGX("民事管辖","Msgx","ParseMsgxSegment","MsysCaseInfo","",""),
	MSES("民事二审","Mses","ParseMsesSegment","MsesCaseInfo","",""),
	MSZS("民事再审","Mszs","ParseMszsSegment","MszsCaseInfo","",""),
	MSZSSC("民事再审审查","Mszssc","ParseMszsSegment","MszsscCaseInfo","","");
//	XZYS("行政一审","XZYS","ParseXzysSegment","XzysCaseInfo","",""),
//	XZES("行政二审","XZES","ParseXzesSegment","XzesCaseInfo","",""),
//	XZZS("行政再审","XZZS","ParseXzzsSegment","XzzsCaseInfo","",""),
//	XZZSSC("行政再审审查","XZZSSC","ParseXzzsSegment","XzzsscCaseInfo","",""),
//	XSYS("刑事一审","XSYS","ParseXsysSegment","XsysCaseInfo","",""),
//	XSES("刑事二审","XSES","ParseXsesSegment","XsesCaseInfo","",""),
//	XSZS("刑事再审","XSZS","ParseXszsSegment","XszsCaseInfo","",""),
//	XSZSSC("刑事再审审查","Xszssc","ParseXszsSegment","XszsscCaseInfo","","");
	private String parse;//解析类型
	private String handlerName;//无用
	private String parseDocumentName;//ParseXzesSegment
	private String caseinfoName;//XzesCaseInfo
	private String tsblSegment;
	private String tsblModel;
	private ParseEnum() {
	}			
	
	private ParseEnum(String parse, String handlerName,
			String parseDocumentName, String caseinfoName, String tsblSegment,
			String tsblModel) {
		this.parse = parse;
		this.handlerName = handlerName;
		this.parseDocumentName = parseDocumentName;
		this.caseinfoName = caseinfoName;
		this.tsblSegment = tsblSegment;
		this.tsblModel = tsblModel;
	}

	public String getParse() {
		return parse;
	}
	public void setParse(String parse) {
		this.parse = parse;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	
	
	public String getParseDocumentName() {
		return parseDocumentName;
	}

	public void setParseDocumentName(String parseDocumentName) {
		this.parseDocumentName = parseDocumentName;
	}

	public String getCaseinfoName() {
		return caseinfoName;
	}

	public void setCaseinfoName(String caseinfoName) {
		this.caseinfoName = caseinfoName;
	}

	public String getTsblSegment() {
		return tsblSegment;
	}
	public void setTsblSegment(String tsblSegment) {
		this.tsblSegment = tsblSegment;
	}
	public String getTsblModel() {
		return tsblModel;
	}
	public void setTsblModel(String tsblModel) {
		this.tsblModel = tsblModel;
	}
	public static String getHandlerByParse(String Parse){
		for(ParseEnum parseEnum:ParseEnum.values()){
			if(StringUtil.equals(parseEnum.getParse(), Parse)){
				return parseEnum.getHandlerName();
			}
		}
		return null;
	}
	public static ParseEnum getParseEnumByParse(String Parse){
		for(ParseEnum parseEnum:ParseEnum.values()){
			if(StringUtil.equals(parseEnum.getParse(), Parse)){
				return parseEnum;
			}
		}
		return null;
	}
}
