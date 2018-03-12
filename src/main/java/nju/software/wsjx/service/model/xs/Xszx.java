package nju.software.wsjx.service.model.xs;

/**
 * 刑事 主刑
 * @author 服兰
 *
 */
public class Xszx {

	private String zxlb;//主刑类别
	private String zxxq;//主刑刑期
	
	public Xszx(String zxlb) {
		super();
		this.zxlb = zxlb;
	}
	public Xszx() {
		super();
	}
	public String getZxlb() {
		return zxlb;
	}
	public void setZxlb(String zxlb) {
		this.zxlb = zxlb;
	}
	public String getZxxq() {
		return zxxq;
	}
	public void setZxxq(String zxxq) {
		this.zxxq = zxxq;
	}
	
	
}
