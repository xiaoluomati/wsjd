package nju.software.wsjx.service.model.xs;

import java.util.List;

/**
 * 单罪判罚、执行判罚
 * @author 服兰
 *
 */
public class PfModel {
	private String pfnr;//判罚内容
	private ZmModel zm;//罪名
	private Xszx zx;//主刑
	private Xszx hx;//缓刑
	private List<FjxModel> fjxList;//附加刑
	private String pjjglx;//判决结果类型
	private String yzszbf;//原罪数罪并罚
	
	public PfModel(String pfnr) {
		super();
		this.pfnr = pfnr;
	}
	public PfModel() {
		super();
	}
	public ZmModel getZm() {
		return zm;
	}
	public void setZm(ZmModel zm) {
		this.zm = zm;
	}
	public Xszx getZx() {
		return zx;
	}
	public void setZx(Xszx zx) {
		this.zx = zx;
	}
	public String getPjjglx() {
		return pjjglx;
	}
	public void setPjjglx(String pjjglx) {
		this.pjjglx = pjjglx;
	}
	public List<FjxModel> getFjxList() {
		return fjxList;
	}
	public void setFjxList(List<FjxModel> fjxList) {
		this.fjxList = fjxList;
	}
	public Xszx getHx() {
		return hx;
	}
	public void setHx(Xszx hx) {
		this.hx = hx;
	}
	public String getPfnr() {
		return pfnr;
	}
	public void setPfnr(String pfnr) {
		this.pfnr = pfnr;
	}
	public String getYzszbf() {
		return yzszbf;
	}
	public void setYzszbf(String yzszbf) {
		this.yzszbf = yzszbf;
	}
	

}
