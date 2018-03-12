package nju.software.wsjx.service.model;

/**
 * 判决结果诉讼费承担人模型，描述了当事人承担诉讼费相关信息
 * @author 服兰
 *
 */
public class WscpjgssfcdModel {
	private String cdr;//承担人
	private String cdrdw;//承担人思维
	private String cdje;//承担金额
	private String cdfs;//承担方式
	
	public WscpjgssfcdModel() {
		super();
	}
	public WscpjgssfcdModel(String cdr, String cdrdw, String cdje, String cdfs) {
		super();
		this.cdr = cdr;
		this.cdrdw = cdrdw;
		this.cdje = cdje;
		this.cdfs = cdfs;
	}
	public String getCdr() {
		return cdr;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
	public String getCdrdw() {
		return cdrdw;
	}
	public void setCdrdw(String cdrdw) {
		this.cdrdw = cdrdw;
	}
	public String getCdje() {
		return cdje;
	}
	public void setCdje(String cdje) {
		this.cdje = cdje;
	}
	public String getCdfs() {
		return cdfs;
	}
	public void setCdfs(String cdfs) {
		this.cdfs = cdfs;
	}
	

}
