package nju.software.wsjx.service.model.xs;

import nju.software.wsjx.util.StringUtil;

public enum XszxEnum {
	SX("死刑"),
	SH("死缓"),
	WQTX("无期徒刑"),
	YQTX("有期徒刑"),
	JY("拘役"),
	GZ("管制");
	String xz;

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	private XszxEnum(String xz) {
		this.xz = xz;
	}
	
	public static XszxEnum getXszx(String conten){
		for(XszxEnum xz:XszxEnum.values()){
			if(StringUtil.contains(conten, xz.getXz())){
				return xz;
			}
		}
		return null;
	}

}
