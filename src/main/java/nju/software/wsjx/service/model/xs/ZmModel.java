package nju.software.wsjx.service.model.xs;

/**
 * 刑事 罪名
 * @author 服兰
 *
 */
public class ZmModel {
	private String zm;//罪名
	private String zmdm;//罪名代码
	private String wzzm;//完整罪名
	
	
	public ZmModel() {
		super();
	}
	
	public ZmModel(String zm) {
		super();
		this.zm = zm;
	}


	public String getZmdm() {
		return zmdm;
	}
	public void setZmdm(String zmdm) {
		this.zmdm = zmdm;
	}
	public String getWzzm() {
		return wzzm;
	}
	public void setWzzm(String wzzm) {
		this.wzzm = wzzm;
	}

	public String getZm() {
		return zm;
	}

	public void setZm(String zm) {
		this.zm = zm;
	}
	
	

}
