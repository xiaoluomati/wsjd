package nju.software.wsjx.service.model;

import java.util.List;


/**
 * 判决结果的诉讼费类，描述了所有诉讼费相关信息
 * @author 服兰
 *
 */
public class WsCpjgssfModel {
	private String ssfjl;//诉讼费记录
	private List<WsCpjgssfjeModel> ssfjeModels;//诉讼费金额
	private List<WscpjgssfcdModel> ssfcdModels;//诉讼费承担
	private String jnqk;//缴纳情况
	private String zje;//诉讼费总金额
	
	public WsCpjgssfModel() {
		super();
	}
	public String getSsfjl() {
		return ssfjl;
	}
	public void setSsfjl(String ssfjl) {
		this.ssfjl = ssfjl;
	}
	public List<WsCpjgssfjeModel> getSsfjeModels() {
		return ssfjeModels;
	}
	public void setSsfjeModels(List<WsCpjgssfjeModel> ssfjeModels) {
		this.ssfjeModels = ssfjeModels;
	}
	public List<WscpjgssfcdModel> getSsfcdModels() {
		return ssfcdModels;
	}
	public void setSsfcdModels(List<WscpjgssfcdModel> ssfcdModels) {
		this.ssfcdModels = ssfcdModels;
	}
	public String getJnqk() {
		return jnqk;
	}
	public void setJnqk(String jnqk) {
		this.jnqk = jnqk;
	}
	public String getZje() {
		return zje;
	}
	public void setZje(String zje) {
		this.zje = zje;
	}
	

}
