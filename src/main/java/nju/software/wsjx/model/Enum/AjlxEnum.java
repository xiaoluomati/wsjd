package nju.software.wsjx.model.Enum;

import nju.software.wsjx.util.StringUtil;

public enum AjlxEnum {
	MSYS("民事一审","Msys","",""),
	MSES("民事二审","Mses","",""),
	XZYS("行政一审","XZYS","ParseXzysDocument","XzysDocument"),
	XZES("行政二审","XZES","",""),
	XSYS("刑事一审","XSYS","",""),
	XSES("刑事二审","XSES","","");
	private String ajlx;
	private String handlerName;
	private String parseDocumentName;
	private String documentName;
	private AjlxEnum() {
	}
	
	
	
	private AjlxEnum(String ajlx, String handlerName, String parseDocumentName,
			String documentName) {
		this.ajlx = ajlx;
		this.handlerName = handlerName;
		this.parseDocumentName = parseDocumentName;
		this.documentName = documentName;
	}



	public String getAjlx() {
		return ajlx;
	}
	public void setAjlx(String ajlx) {
		this.ajlx = ajlx;
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

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public static String getHandlerByAjlx(String ajlx){
		for(AjlxEnum ajlxEnum:AjlxEnum.values()){
			if(StringUtil.equals(ajlxEnum.getAjlx(), ajlx)){
				return ajlxEnum.getHandlerName();
			}
		}
		return null;
	}
	public static AjlxEnum getAjlxEnumByAjlx(String ajlx){
		for(AjlxEnum ajlxEnum:AjlxEnum.values()){
			if(StringUtil.equals(ajlxEnum.getAjlx(), ajlx)){
				return ajlxEnum;
			}
		}
		return null;
	}
}
