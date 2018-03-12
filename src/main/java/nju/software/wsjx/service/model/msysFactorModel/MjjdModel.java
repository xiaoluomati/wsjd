package nju.software.wsjx.service.model.msysFactorModel;

import java.util.List;

/**
 * 参考文书抽取的model，自行添加需要抽取的要素属性
 */
/**
 * 民间借贷model
 */

public class MjjdModel {
	/**
	 * 1、当事人之间的关系（暂不提取）
	 */
	private String dsrzjdgx;
	/**
	 * 2、合同成立时间
	 */
	private String htclsj;
	/**
	 * 3、借款金额
	 */
	private String jkje;
	/**
	 * 4、合同开始时间
	 */
	private String htkssj;
	/**
	 * 5、合同结束时间
	 */
	private String htjssj;
	/**
	 * 6、本金还款情况
	 */
	private List<String> bjhkqk;
	/**
	 * 7、利息还款情况
	 */
	private List<String> lxhkqk;
	/**
	 * 8、担保人
	 */
	private List<String> dbr;
	/**
	 * 9、担保合同内容
	 */
	private String dbhtnr;
	/**
	 * 10、担保金额
	 */
	private String dbje;
	/**
	 * 11、是否有夫妻共同债务
	 */
	private String sfyfqgtzw;
	/**
	 * 12、名义借款人(暂不提取)
	 */
	private String myjkr;
	/**
	 * 1、当事人之间的关系（暂不提取）
	 */
	public String getDsrzjdgx(){
		return dsrzjdgx;
	}
	public void setDsrzjdgx(String dsrzjdgx){
		this.dsrzjdgx=dsrzjdgx;
	}
	/**
	 * 2、合同成立时间
	 * @return
	 */
	public String getHtclsj(){
		return htclsj;
	}
	public void setHtclsj(String htclsj){
		this.htclsj=htclsj;
	}
	/**
	 * 3、借款金额
	 * @return
	 */
	public String getJkje(){
		return jkje;
	}
	public void setJkje(String jkje){
		this.jkje=jkje;
	}
	/**
	 * 4、合同开始时间
	 * @return
	 */
	public String getHtkssj(){
		return htkssj;
	}
	public void setHtkssj(String htkssj){
		this.htkssj=htkssj;
	}
	/**
	 * 5、合同结束时间
	 */
	public String getHtjssj(){
		return htjssj;
	}
	public void setHtjssj(String htjssj){
		this.htjssj=htjssj;
	}
	/**
	 * 6、本金还款情况
	 * @return
	 */
	public List<String> getBjhkqk(){
		return bjhkqk;
	}
	public void setBjhkqk(List<String> bjhkqk){
		this.bjhkqk=bjhkqk;
	}
	/**
	 * 7、利息还款情况
	 * @return
	 */
	public List<String> getLxhkqk(){
		return lxhkqk;
	}
	public void setLxhkqk(List<String> lxhkqk){
		this.lxhkqk=lxhkqk;
	}
	/**
	 * 8、担保人
	 * @return
	 */
	public List<String> getDbr(){
		return dbr;
	}
	public void setDbr(List<String> dbr){
		this.dbr=dbr;
	}
	/**
	 * 9、担保合同内容
	 * @return
	 */
	public String getDbhtnr(){
		return dbhtnr;
	}
	public void setDbhtnr(String dbhtnr){
		this.dbhtnr=dbhtnr;
	}
	/**
	 * 10、担保金额
	 * @return
	 */
	public String getDbje(){
		return dbje;
	}
	public void setDbje(String dbje){
		this.dbje=dbje;
	}
	/**
	 * 11、是否有夫妻共同债务
	 * @return
	 */
	public String getSfyfqgtzw(){
		return sfyfqgtzw;
	}
	public void setSfyfqgtzw(String sfyfqgtzw){
		this.sfyfqgtzw=sfyfqgtzw;
	}
	/**
	 * 12、名义借款人（暂不提取）
	 * @return
	 */
	public String getMyjkr(){
		return myjkr;
	}
	public void setMyjkr(String myjkr){
		this.myjkr=myjkr;
	}
	
	


}
