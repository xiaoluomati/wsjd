 package nju.software.wsjx.service.model.msysFactorModel;
import java.util.*;

public class JrjkModel {
	/*共有部分*/
	private int htfs;//合同份数
	private String sj;//时间
	private List<String> qzr;//签字人                                                                                                                                                              
	private String jkje;//借款金额
	private String jkqx;//借款期限
	private String jkkssj;//借款开始时间
	private String jkjssj;//借款结束时间
	private String jklx;//是否约定借款利息
	private String sfydyqhklx;//是否约定逾期还款利息
	private String sfydyqhkfl;//是否约定逾期还款复利
	private String sfydyqhkwyj;//是否约定逾期还款违约金
	private String jkhsfchbj;//借款后是否偿还本金
	private String jkhsfchlx;//借款后是否偿还利息
	private String jkhsfchfx;//借款后是否偿还罚息
	private String jkhsfchfl;//借款后是否偿还复利
	private String sfczdb;//是否存在担保
	/*根据担保方式分类*/
	//保证
	private String bzhtlx;//保证合同类型
	private String bzfs;//保证方式
	private String bzfw;//保证范围
	private String bzqj;//保证期间
	private String bzqxnzzzr;//保证期限内主张责任
	//抵押
	private String dyhtlx;//抵押合同类型
	private String dywlx;//抵押物类型
	private String dydbsfdj;//抵押担保是否登记
	private String dywxz;//抵押物现状
	//质押
	private String zylx;//质押类型
	private String zwsfjfzqr;//质物是否交付质权人
	private String zqrsfyxsqlzq;//质权人是否已行使权利质权
	/*其他要素*/
	private String ywydhtlxd;//有无约定合同履行地
	private String ywxygxydtk;//有无协议管辖约定条款
	private String sfydzc;//是否约定仲裁
	private String sfydzszqfydcd;//是否约定追索债权费用的承担
	private String sfyqtxgfydyd;//是否有其他相关费用的约定
	private String sfqdgzqht;//是否签订过展期合同
	private String zqhtcxyddxm;//展期合同重新约定的项目
	private String sftsydrbhwb;//是否同时约定人保和物保
	private String jkzqhtydsxsfddbzrdty;//借款展期合同约定事项是否得到保证人的同意
	private String sszwsfczzwrzrzwdqk;//涉诉债务是否存在债务人转让债务的情况
	private String zrxwsfzdbzrty;//转让行为是否征得保证人同意
	private String yqpocdzrdly;//要求配偶承担责任的理由
	private String jkrmqdhyzk;//借款人目前的婚姻状况
	private String ygqsqsfyct;//原告起诉前是否有催讨
	private String mqsfkylxdbg;//目前是否可以联系到被告
	public int getHtfs() {
		return htfs;
	}
	public void setHtfs(int htfs) {
		this.htfs=htfs;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj=sj;
	}
	public List<String> getQzr() {
		return qzr;
	}
	public void setQzr(List<String> qzr) {
		this.qzr=qzr;
	}
	public String getJkje() {
		return jkje;
	}
	public void setJkje(String jkje) {
		this.jkje=jkje;
	}
	public String getJkqx(){
		return jkqx;
	}
	public void setJkqx(String jkqx){
		this.jkqx=jkqx;
	}
	public String getJkkssj() {
		return jkkssj;
	}
	public void setJkkssj(String jkkssj) {
		this.jkkssj=jkkssj;
	}
	public String getJkjssj() {
		return jkjssj;
	}
	public void setJkjssj(String jkjssj) {
		this.jkjssj=jkjssj;
	}
	public String getJklx() {
		return jklx;
	}
	public void setJklx(String jklx) {
		this.jklx=jklx;
	}
	
	public String getSfydyqhklx() {
		return sfydyqhklx;
	}
	public void setSfydyqhklx(String sfydyqhklx) {
		this.sfydyqhklx=sfydyqhklx;
	}
	public String getSfydyqhkfl() {
		return sfydyqhkfl;
	}
	public void setSfydyqhkfl(String sfydyqhkfl) {
		this.sfydyqhkfl=sfydyqhkfl;
	}
	public String getSfydyqhkwyj() {
		return sfydyqhkwyj;
	}
	public void setSfydyqhkwyj(String sfydyqhkwyj) {
		this.sfydyqhkwyj=sfydyqhkwyj;
	}
	public String getJkhsfchbj() {
		return jkhsfchbj;
	}
	public void setJkhsfchbj(String jkhsfchbj) {
		this.jkhsfchbj=jkhsfchbj;
	}
	public String getJkhsfchlx() {
		return jkhsfchlx;
	}
	public void setJkhsfchlx(String jkhsfchlx) {
		this.jkhsfchlx=jkhsfchlx;
	}
	public String getJkhsfchfx() {
		return jkhsfchfx;
	}
	public void setJkhsfchfx(String jkhsfchfx) {
		this.jkhsfchfx=jkhsfchfx;
	}
	public String getJkhsfchfl() {
		return jkhsfchfl;
	}
	public void setJkhsfchfl(String jkhsfchfl) {
		this.jkhsfchfl=jkhsfchfl;
	}
	public String getSfczdb() {
		return sfczdb;
	}
	public void setSfczdb(String sfczdb) {
		this.sfczdb=sfczdb;
	}
	public String getBzhtlx() {
		return bzhtlx;
	}
	public void setBzhtlx(String bzhtlx) {
		this.bzhtlx=bzhtlx;
	}
	public String getBzfs() {
		return bzfs;
	}
	public void setBzfs(String bzfs) {
		this.bzfs=bzfs;
	}
	public String getBzfw() {
		return bzfw;
	}
	public void setBzfw(String bzfw) {
		this.bzfw=bzfw;
	}
	public String getBzqj() {
		return bzqj;
	}
	public void setBzqj(String bzqj) {
		this.bzqj=bzqj;
	}
	public String getBzqxnzzzr() {
		return bzqxnzzzr;
	}
	public void setBzqxnzzzr(String bzqxnzzzr) {
		this.bzqxnzzzr=bzqxnzzzr;
	}
	public String getDyhtlx() {
		return dyhtlx;
	}
	public void setDyhtlx(String dyhtlx) {
		this.dyhtlx=dyhtlx;
	}
	public String getDywlx() {
		return dywlx;
	}
	public void setDywlx(String dywlx) {
		this.dywlx=dywlx;
	}
	public String getDydbsfdj() {
		return dydbsfdj;
	}
	public void setDydbsfdj(String dydbsfdj) {
		this.dydbsfdj=dydbsfdj;
	}
	public String getDywxz() {
		return dywxz;
	}
	public void setDywxz(String dywxz) {
		this.dywxz=dywxz;
	}
	public String getZylx() {
		return zylx;
	}
	public void setZylx(String zylx) {
		this.zylx=zylx;
	}
	public String getZwsfjfzqr() {
		return zwsfjfzqr;
	}
	public void setZwsfjfzqr(String zwsfjfzqr) {
		this.zwsfjfzqr=zwsfjfzqr;
	}
	public String getZqrsfyxsqlzq() {
		return zqrsfyxsqlzq;
	}
	public void setZqrsfyxsqlzq(String zqrsfyxsqlzq) {
		this.zqrsfyxsqlzq=zqrsfyxsqlzq;
	}
	public String getYwydhtlxd() {
		return ywydhtlxd;
	}
	public void setYwydhtlxd(String ywydhtlxd) {
		this.ywydhtlxd=ywydhtlxd;
	}
	public String getYwxygxydtk() {
		return ywxygxydtk;
	}
	public void setYwxygxydtk(String ywxygxydtk) {
		this.ywxygxydtk=ywxygxydtk;
	}
	public String getSfydzc() {
		return sfydzc;
	}
	public void setSfydzc(String sfydzc) {
		this.sfydzc=sfydzc;
	}
	public String getSfydzszqfydcd() {
		return sfydzszqfydcd;
	}
	public void setSfydzszqfydcd(String sfydzszqfydcd) {
		this.sfydzszqfydcd=sfydzszqfydcd;
	}
	public String getSfyqtxgfydyd() {
		return sfyqtxgfydyd;
	}
	public void setSfyqtxgfydyd(String sfyqtxgfydyd) {
		this.sfyqtxgfydyd=sfyqtxgfydyd;
	}
	public String getSfqdgzqht() {
		return  sfqdgzqht;
	}
	public void setSfqdgzqht(String sfqdgzqht) {
		this.sfqdgzqht=sfqdgzqht;
	}
	public String getZqhtcxyddxm() {
		return zqhtcxyddxm;
	}
	public void setZqhtcxyddxm(String zqhtcxyddxm) {
		this.zqhtcxyddxm=zqhtcxyddxm;
	}
	public String getSftsydrbhwb() {
		return sftsydrbhwb;
	}
	public void setSftsydrbhwb(String sftsydrbhwb) {
		this.sftsydrbhwb=sftsydrbhwb;
	}
	public String getJkzqhtydsxsfddbzrdty() {
		return jkzqhtydsxsfddbzrdty;
	}
	public void setJkzqhtydsxsfddbzrdty(String jkzqhtydsxsfddbzrdty) {
		this.jkzqhtydsxsfddbzrdty=jkzqhtydsxsfddbzrdty;
	}
	public String getSszwsfczzwrzrzwdqk() {
		return sszwsfczzwrzrzwdqk;
	}
	public void setSszwsfczzwrzrzwdqk(String sszwsfczzwrzrzwdqk) {
		this.sszwsfczzwrzrzwdqk=sszwsfczzwrzrzwdqk;
	}
	public String getZrxwsfzdbzrty() {
		return zrxwsfzdbzrty;
	}
	public void setZrxwsfzdbzrty(String zrxwsfzdbzrty) {
		this.zrxwsfzdbzrty=zrxwsfzdbzrty;
	}
	public String getYqpocdzrdly() {
		return yqpocdzrdly;
	}
	public void setYqpocdzrdly(String yqpocdzrdly) {
		this.yqpocdzrdly=yqpocdzrdly;
	}
	public String getJkrmqdhyzk() {
		return jkrmqdhyzk;
	}
	public void setJkrmqdhyzk(String jkrmqdhyzk) {
		this.jkrmqdhyzk=jkrmqdhyzk;
	}
	public String getYgqsqsfyct() {
		return ygqsqsfyct;
	}
	public void setYgqsqsfyct(String ygqsqsfyct) {
		this.ygqsqsfyct=ygqsqsfyct;
	}
	public String getMqsfkylxdbg() {
		return mqsfkylxdbg;
	}
	public void setMqsfkylxdbg(String mqsfkylxdbg) {
		this.mqsfkylxdbg=mqsfkylxdbg;
	}
}
