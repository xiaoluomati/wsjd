package nju.software.wsjx.model.wsSegmentationModel;

import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.relateModel.QkqkModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QzcsModel;

/**
 * 文书诉讼参与人model
 * @author lr12
 *
 */
public class WssscyrModel {
	private String sscyrallinfo;//诉讼参与人所有信息
	private String sscyr;//诉讼参与人名称
	private String sssf;//诉讼身份
	private String dsrlx;//当事人类型
	private String mz;//民族
	private String csrq;//出生日期
	private String zjlx;//证件类型
	private String zjhm;//证件号码
	private String dsrdz;//当事人地址
	private String dsrzw;//当事人职务
	private String zrrsf;//自然人身份
	private String dsrwhcd;//当事人文化程度
	private String dsrxw;//当事人学位
	private String xb;//性别
	private String dtqk;//到庭情况
	private String gj;//国籍
	private String year;
	private String month;
	private String day;
	private String ssdw;//诉讼地位
	private String ysssdw;//诉讼地位
	private String esssdw;//二审诉讼地位
	private String dsrlb;//当事人类别
	private String dwxz;//单位性质
	private String fddbr;//单位法定代表人
	private String gzdw;//工作单位
	private String gzdwxz;//工作单位性质
	private String dsrsfzh;//当事人是否再婚
	private String tshy;//特殊行业
	private String xzfagxzt;//行政法律关系主体
	private String bglx;//被告类型
	private String zzjgdm;//组织机构代码
	private String xzglfw;//行政管理范围
	private String zzmm;//政治面貌
	private String hjd;//户籍地
	private String isBhr;//是否被害人
	private String msssygrlx;//民事诉讼原告人类型
	private String xszrablity;//刑事责任能力
	private String hxkyqfz;//缓刑考验期内犯罪
	private String jskyqfz;//假释考验期内犯罪
	private String xjycs;//现羁押场所
	private List<QzcsModel> qzcsList;//强制措施Model
	private List<QkqkModel> qkqkList;//前科情况Model
//	交通事故
//	private boolean jqxgsSfbg;//交强险公司是否列为被告
//	private boolean sysxgsSfg;//商业三险公司是否列为被告
	private int peichangIndex;//赔偿顺序
	private String jtsgzr;//交通事故责任
	private String sfcdpczr;//是否承担赔偿责任

	private String xgsscyr;//相关诉讼参与人
	public List<QkqkModel> getQkqkList() {
		return qkqkList;
	}

	public void setQkqkList(List<QkqkModel> qkqkList) {
		this.qkqkList = qkqkList;
	}

	public List<QzcsModel> getQzcsList() {
		return qzcsList;
	}

	public void setQzcsList(List<QzcsModel> qzcsList) {
		this.qzcsList = qzcsList;
	}

	public String getXjycs() {
		return xjycs;
	}

	public void setXjycs(String xjycs) {
		this.xjycs = xjycs;
	}

	public String getJskyqfz() {
		return jskyqfz;
	}

	public void setJskyqfz(String jskyqfz) {
		this.jskyqfz = jskyqfz;
	}

	public String getHxkyqfz() {
		return hxkyqfz;
	}

	public void setHxkyqfz(String hxkyqfz) {
		this.hxkyqfz = hxkyqfz;
	}

	public String getXszrablity() {
		return xszrablity;
	}

	public void setXszrablity(String xszrablity) {
		this.xszrablity = xszrablity;
	}

	public String getMsssygrlx() {
		return msssygrlx;
	}

	public void setMsssygrlx(String msssygrlx) {
		this.msssygrlx = msssygrlx;
	}

	public String getIsBhr() {
		return isBhr;
	}

	public void setIsBhr(String isBhr) {
		this.isBhr = isBhr;
	}

	public String getHjd() {
		return hjd;
	}

	public void setHjd(String hjd) {
		this.hjd = hjd;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getZrrsf() {
		return zrrsf;
	}

	public void setZrrsf(String zrrsf) {
		this.zrrsf = zrrsf;
	}

	public String getDsrxw() {
		return dsrxw;
	}

	public void setDsrxw(String dsrxw) {
		this.dsrxw = dsrxw;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getXzglfw() {
		return xzglfw;
	}

	public void setXzglfw(String xzglfw) {
		this.xzglfw = xzglfw;
	}

	public String getZzjgdm() {
		return zzjgdm;
	}
	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}
	public String getBglx() {
		return bglx;
	}
	public void setBglx(String bglx) {
		this.bglx = bglx;
	}
	public String getXzfagxzt() {
		return xzfagxzt;
	}
	public void setXzfagxzt(String xzfagxzt) {
		this.xzfagxzt = xzfagxzt;
	}
	public String getSscyrallinfo() {
		return sscyrallinfo;
	}
	public void setSscyrallinfo(String sscyrallinfo) {
		this.sscyrallinfo = sscyrallinfo;
	}
	public String getTshy() {
		return tshy;
	}
	public void setTshy(String tshy) {
		this.tshy = tshy;
	}
	public String getDsrsfzh() {
		return dsrsfzh;
	}
	public void setDsrsfzh(String dsrsfzh) {
		this.dsrsfzh = dsrsfzh;
	}
	public String getSsdw() {
		return ssdw;
	}
	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}
	public String getYsssdw() {
		return ysssdw;
	}
	public void setYsssdw(String ysssdw) {
		this.ysssdw = ysssdw;
	}
	public String getEsssdw(){
		return esssdw;
	}
	public void setEsssdw(String esssdw){
		this.esssdw = esssdw;
	}
	public String getDsrlb() {
		return dsrlb;
	}
	public void setDsrlb(String dsrlb) {
		this.dsrlb = dsrlb;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}


	public String getDtqk() {
		return dtqk;
	}
	public void setDtqk(String dtqk) {
		this.dtqk = dtqk;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getSscyr() {
		return sscyr;
	}
	public void setSscyr(String sscyr) {
		this.sscyr = sscyr;
	}
	public String getSssf() {
		return sssf;
	}
	public void setSssf(String sssf) {
		this.sssf = sssf;
	}
	public String getDsrlx() {
		return dsrlx;
	}
	public void setDsrlx(String dsrlx) {
		this.dsrlx = dsrlx;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getDsrdz() {
		return dsrdz;
	}
	public void setDsrdz(String dsrdz) {
		this.dsrdz = dsrdz;
	}
	public String getDsrzw() {
		return dsrzw;
	}
	public void setDsrzw(String dsrzw) {
		this.dsrzw = dsrzw;
	}
	public String getDsrwhcd() {
		return dsrwhcd;
	}
	public void setDsrwhcd(String dsrwhcd) {
		this.dsrwhcd = dsrwhcd;
	}
	public String getDwxz() {
		return dwxz;
	}
	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}
	public String getFddbr() {
		return fddbr;
	}
	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}
	public String getGzdw() {
		return gzdw;
	}
	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}
	public String getGzdwxz() {
		return gzdwxz;
	}
	public void setGzdwxz(String gzdwxz) {
		this.gzdwxz = gzdwxz;
	}


	public int getPeichangIndex() {
		return peichangIndex;
	}

	public void setPeichangIndex(int peichangIndex) {
		this.peichangIndex = peichangIndex;
	}

	public String getJtsgzr() {
		return jtsgzr;
	}

	public void setJtsgzr(String jtsgzr) {
		this.jtsgzr = jtsgzr;
	}

	public String getSfcdpczr() {
		return sfcdpczr;
	}

	public void setSfcdpczr(String sfcdpczr) {
		this.sfcdpczr = sfcdpczr;
	}

	public String getXgsscyr() {
		return xgsscyr;
	}

	public void setXgsscyr(String xgsscyr) {
		this.xgsscyr = xgsscyr;
	}
	
}
