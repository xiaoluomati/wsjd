package nju.software.wsjx.service.model;

import java.util.List;

public class PjjeModel {

	private String value;
	private List<String> categorys;
	private String kssj;
	private String jssj;
	private String jsfs;
	
	public PjjeModel() {
		super();
	}
	public PjjeModel(String value, List<String> categorys) {
		super();
		this.value = value;
		this.categorys = categorys;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<String> getCategorys() {
		return categorys;
	}
	public void setCategorys(List<String> categorys) {
		this.categorys = categorys;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getJsfs() {
		return jsfs;
	}
	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}
	
}
