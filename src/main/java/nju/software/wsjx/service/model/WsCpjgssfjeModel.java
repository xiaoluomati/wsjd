package nju.software.wsjx.service.model;

/**
 * 判决结果中诉讼费金额类，描述诉讼费的金额、种类
 * @author 服兰
 *
 */
public class WsCpjgssfjeModel {
	private String value;
	private String category;
	
	public WsCpjgssfjeModel() {
		super();
	}
	public WsCpjgssfjeModel(String value, String category) {
		super();
		this.value = value;
		this.category = category;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

}
