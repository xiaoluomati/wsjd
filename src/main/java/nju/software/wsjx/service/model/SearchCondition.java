package nju.software.wsjx.service.model;

import nju.software.wsjx.util.StringUtil;

public class SearchCondition {

	String scrqks;
	String scrqjs;
	String wslb;
	String zz;
	String ah;
	String wsnr;
	
	public String getScrqks() {
		return scrqks;
	}
	public void setScrqks(String scrqks) {
		this.scrqks = StringUtil.trim(scrqks);
	}
	public String getScrqjs() {
		return scrqjs;
	}
	public void setScrqjs(String scrqjs) {
		this.scrqjs = StringUtil.trim(scrqjs);
	}
	public String getWslb() {
		return wslb;
	}
	public void setWslb(String wslb) {
		this.wslb = StringUtil.trim(wslb);
	}
	public String getZz() {
		return zz;
	}
	public void setZz(String zz) {
		this.zz = StringUtil.trim(zz);
	}
	public String getAh() {
		return ah;
	}
	public void setAh(String ah) {
		this.ah = StringUtil.trim(ah);
	}
	public String getWsnr() {
		return wsnr;
	}
	public void setWsnr(String wsnr) {
		this.wsnr = StringUtil.trim(wsnr);
	}
	
	public boolean isNull(){
		if(StringUtil.isBlank(scrqks)&&StringUtil.isBlank(scrqjs)&&StringUtil.isBlank(wslb)&&StringUtil.isBlank(zz)&&StringUtil.isBlank(ah)&&StringUtil.isBlank(wsnr)){
			return true;
		}
		return false;
	}
}
