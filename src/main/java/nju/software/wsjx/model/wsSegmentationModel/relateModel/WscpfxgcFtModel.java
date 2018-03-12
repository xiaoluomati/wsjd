package nju.software.wsjx.model.wsSegmentationModel.relateModel;

import java.util.HashMap;

public class WscpfxgcFtModel {
	/**
	 * 法律法条名称
	 */
	private String flftmc;
	/**
	 * 法律条款Map<条目，款目>
	 */
	private HashMap<String,String> ftMap;
	private String sfcxf;//是否是程序法 管辖
	public String getFlftmc() {
		return flftmc;
	}
	public void setFlftmc(String flftmc) {
		this.flftmc = flftmc;
	}
	public HashMap<String,String> getFtMap() {
		return ftMap;
	}
	public void setFtMap(HashMap<String,String> ftMap) {
		this.ftMap = ftMap;
	}

	public String getSfcxf() {
		return sfcxf;
	}

	public void setSfcxf(String sfcxf) {
		this.sfcxf = sfcxf;
	}
}
