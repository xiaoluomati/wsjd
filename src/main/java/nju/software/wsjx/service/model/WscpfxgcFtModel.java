package nju.software.wsjx.service.model;

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
}
