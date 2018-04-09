package nju.software.wsjx.model.wsSegmentationModel;

import java.util.List;

import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;

/**
 * 文书案件基本情况model
 * @author lr12
 *
 */
public class WsajjbqkModel {
	private String qyspjd;//前一审判决段 民事再审 行政再审
	private String qysdl;//前一审段落 民事再审 行政再审
	private String qsdl;//前审段落 民事二审 行政二审
	private String qsygscd;//前审原告诉称 民事二审 行政二审
	private String qsbgbcd;//前审被告辩称段 民事二审 行政二审
	private String qsdsryjd;//前审第三人意见段 无
	private List<String> qszjd;//前审证据段 行政二审
	private List<String> qssld;//前审审理段 民事二审 行政二审
	private String qscmd;//前审查明段 民事二审

	public String getQscmd() {
		return qscmd;
	}

	public void setQscmd(String qscmd) {
		this.qscmd = qscmd;
	}

	private String qsfsscd;//前审反诉诉称段 民事二审 行政二审
	private String qspjd;//前审判决段 民事二审 行政二审
	private String bsdl;//本审段落 民事二审 行政二审
	private String ssrscd;//上诉人诉称段 民事二审 行政二审
	private List<String> bssrbcd;//被上诉人辩称段 民事二审 行政二审
	private String bsdsryjd;//本审第三人意见 行政二审
	private List<String> bssld;//本审审理段 民事二审 行政二审 刑事一审
	private List<String> bszjd;//本审证据段 行政二审
	private String ygscd;//原告诉称段 民事一审 行政一审
	private String bgbcd;//被告辩称段 民事一审 行政一审
	private List<String> cmssd;//查明事实段 民事一审 行政一审
	private List<String> zjd;//证据段 民事一审 行政一审 行政二审
	private String dsryjd;//当事人意见段 民事一审 行政一审
	private String fsscd;//反诉诉称段 民事一审
	private String fsbcd;//反诉辩称段 民事一审
	private String xzsszyd;//行政诉讼争议段 行政一审
	private String zkdl;//指控段落 刑事一审
	private String bhdl;//辩护段落 刑事一审
	private String xsbssld;//刑事本审审理段 刑事二审 刑事一审
	private String xsqssld;//刑事前审审理段 刑事二审
	private String qscpyzypjjg;//前审裁判要旨与判决结果 刑事二审
	private String qscmssyzj;//前审查明事实与证据 刑事二审
	private String ssssbhyj;//上诉申诉辩护意见 刑事二审
	private String jcjgyj;//检察机关意见 刑事二审
	private String fdmsssqqd;//附带民事诉讼请求段 刑事一审
	private String zkss;//指控事实 刑事一审 
	private String zkzj;//指控证据 刑事一审
	private String zkyj;//指控意见 刑事一审
	private List<String> bgrbc;//被告人辩称 刑事一审
	private List<String> bhrbh;//辩护人辩护 刑事一审
	private List<PjjeModel> ssqqjeList;//诉讼请求金额
	
	private String ysfylyd;//移送法院理由段 管辖
	private String bqfylyd;//报请法院理由段 管辖
	public String getYsfylyd() {
		return ysfylyd;
	}
	public void setYsfylyd(String ysfylyd) {
		this.ysfylyd = ysfylyd;
	}
	public String getBqfylyd() {
		return bqfylyd;
	}
	public void setBqfylyd(String bqfylyd) {
		this.bqfylyd = bqfylyd;
	}
	public String gxString() {
		return "temo{" +
				"ysfylyd='" + ysfylyd + '\'' +
				", bqfylyd='" + bqfylyd + '\'' +
				'}';
	}

	private FsqqModel fsqqModel;//反诉请求 第一审普通程序
	private SsqqModel ssqqModel;//诉讼请求 第一审普通程序
	private ZjdsrModel zjdsrModel;//追加当事人段 第一审普通程序
	public FsqqModel getFsqqModel() {
		return fsqqModel;
	}
	public void setFsqqModel(FsqqModel fsqqModel) {
		this.fsqqModel = fsqqModel;
	}
	public SsqqModel getSsqqModel() {
		return ssqqModel;
	}
	public void setSsqqModel(SsqqModel ssqqModel) {
		this.ssqqModel = ssqqModel;
	}
	public ZjdsrModel getZjdsrModel() {
		return zjdsrModel;
	}
	public void setZjdsrModel(ZjdsrModel zjdsrModel) {
		this.zjdsrModel = zjdsrModel;
	}
	public String ysptString(){
		return "yspt{" +
				"fsqqModel=" + fsqqModel +
				", ssqqModel=" + ssqqModel +
				", zjdsrModel=" + zjdsrModel +
				'}';
	}

	private String sgxq;//事故详情
	private String sgsj;//事故时间
	private String sgdd;//事故地点
	private String jdcsyr;//机动车所有人
	private String jdcglr;//机动车管理人
	private String gajgrdyj;//公安机关认定意见
	private String shrjzd;//受害人居住地
	private String shrzy;//受害人职业
	private String sftb;//是否投保
	private String tbxz;//投保险种
	private String sfzbxqn;//是否在保险期内
	private String sfxqpf;//是否先期赔付
	private String shangQing;//伤情
	private String realPay;//实际支出情况
	private String identifyContent;//相关鉴定
	private String jdsfkk;//鉴定是否可靠
	public String getQyspjd() {
		return qyspjd;
	}
	public void setQyspjd(String qyspjd) {
		this.qyspjd = qyspjd;
	}
	public String getQysdl() {
		return qysdl;
	}
	public void setQysdl(String qysdl) {
		this.qysdl = qysdl;
	}
	public String getQsdl() {
		return qsdl;
	}
	public void setQsdl(String qsdl) {
		this.qsdl = qsdl;
	}
	public String getQsygscd() {
		return qsygscd;
	}
	public void setQsygscd(String qsygscd) {
		this.qsygscd = qsygscd;
	}
	public String getQsbgbcd() {
		return qsbgbcd;
	}
	public void setQsbgbcd(String qsbgbcd) {
		this.qsbgbcd = qsbgbcd;
	}
	public String getQsdsryjd() {
		return qsdsryjd;
	}
	public void setQsdsryjd(String qsdsryjd) {
		this.qsdsryjd = qsdsryjd;
	}
	public List<String> getQszjd() {
		return qszjd;
	}
	public void setQszjd(List<String> qszjd) {
		this.qszjd = qszjd;
	}

	public String getQsfsscd() {
		return qsfsscd;
	}
	public void setQsfsscd(String qsfsscd) {
		this.qsfsscd = qsfsscd;
	}
	public List<String> getQssld() {
		return qssld;
	}
	public void setQssld(List<String> qssld) {
		this.qssld = qssld;
	}
	public String getQspjd() {
		return qspjd;
	}
	public void setQspjd(String qspjd) {
		this.qspjd = qspjd;
	}
	public String getBsdl() {
		return bsdl;
	}
	public void setBsdl(String bsdl) {
		this.bsdl = bsdl;
	}

	public String getSsrscd() {
		return ssrscd;
	}
	public void setSsrscd(String ssrscd) {
		this.ssrscd = ssrscd;
	}


	public String getFdmsssqqd() {
		return fdmsssqqd;
	}
	public void setFdmsssqqd(String fdmsssqqd) {
		this.fdmsssqqd = fdmsssqqd;
	}
	public List<String> getBszjd() {
		return bszjd;
	}
	public void setBszjd(List<String> bszjd) {
		this.bszjd = bszjd;
	}
	public List<String> getBssrbcd() {
		return bssrbcd;
	}
	public void setBssrbcd(List<String> bssrbcd) {
		this.bssrbcd = bssrbcd;
	}
	public List<String> getBssld() {
		return bssld;
	}
	public void setBssld(List<String> bssld) {
		this.bssld = bssld;
	}

	public String getBsdsryjd() {
		return bsdsryjd;
	}
	public void setBsdsryjd(String bsdsryjd) {
		this.bsdsryjd = bsdsryjd;
	}
	public String getYgscd() {
		return ygscd;
	}
	public void setYgscd(String ygscd) {
		this.ygscd = ygscd;
	}
	public String getBgbcd() {
		return bgbcd;
	}
	public void setBgbcd(String bgbcd) {
		this.bgbcd = bgbcd;
	}
	public List<String> getCmssd() {
		return cmssd;
	}
	public void setCmssd(List<String> cmssd) {
		this.cmssd = cmssd;
	}
	public List<String> getZjd() {
		return zjd;
	}
	public void setZjd(List<String> zjd) {
		this.zjd = zjd;
	}
	public String getDsryjd() {
		return dsryjd;
	}
	public void setDsryjd(String dsryjd) {
		this.dsryjd = dsryjd;
	}
	public String getFsscd() {
		return fsscd;
	}
	public void setFsscd(String fsscd) {
		this.fsscd = fsscd;
	}
	public String getFsbcd() {
		return fsbcd;
	}
	public void setFsbcd(String fsbcd) {
		this.fsbcd = fsbcd;
	}
	public String getXzsszyd() {
		return xzsszyd;
	}
	public void setXzsszyd(String xzsszyd) {
		this.xzsszyd = xzsszyd;
	}
	public String getZkdl() {
		return zkdl;
	}
	public void setZkdl(String zkdl) {
		this.zkdl = zkdl;
	}
	public String getBhdl() {
		return bhdl;
	}
	public void setBhdl(String bhdl) {
		this.bhdl = bhdl;
	}
	public String getXsbssld() {
		return xsbssld;
	}
	public void setXsbssld(String xsbssld) {
		this.xsbssld = xsbssld;
	}
	public String getZkss() {
		return zkss;
	}
	public void setZkss(String zkss) {
		this.zkss = zkss;
	}
	public String getZkzj() {
		return zkzj;
	}
	public void setZkzj(String zkzj) {
		this.zkzj = zkzj;
	}
	public String getZkyj() {
		return zkyj;
	}
	public void setZkyj(String zkyj) {
		this.zkyj = zkyj;
	}
	public List<String> getBgrbc() {
		return bgrbc;
	}
	public void setBgrbc(List<String> bgrbc) {
		this.bgrbc = bgrbc;
	}
	public List<String> getBhrbh() {
		return bhrbh;
	}
	public void setBhrbh(List<String> bhrbh) {
		this.bhrbh = bhrbh;
	}
	public String getXsqssld() {
		return xsqssld;
	}
	public void setXsqssld(String xsqssld) {
		this.xsqssld = xsqssld;
	}
	public String getQscpyzypjjg() {
		return qscpyzypjjg;
	}
	public void setQscpyzypjjg(String qscpyzypjjg) {
		this.qscpyzypjjg = qscpyzypjjg;
	}
	public String getQscmssyzj() {
		return qscmssyzj;
	}
	public void setQscmssyzj(String qscmssyzj) {
		this.qscmssyzj = qscmssyzj;
	}
	public String getSsssbhyj() {
		return ssssbhyj;
	}
	public void setSsssbhyj(String ssssbhyj) {
		this.ssssbhyj = ssssbhyj;
	}
	public String getGsjgctyj() {
		return jcjgyj;
	}
	public void setGsjgctyj(String gsjgctyj) {
		this.jcjgyj = gsjgctyj;
	}
	public List<PjjeModel> getSsqqjeList() {
		return ssqqjeList;
	}
	public void setSsqqjeList(List<PjjeModel> ssqqjeList) {
		this.ssqqjeList = ssqqjeList;
	}
	public String getJcjgyj() {
		return jcjgyj;
	}
	public void setJcjgyj(String jcjgyj) {
		this.jcjgyj = jcjgyj;
	}
	public String getSgxq() {
		return sgxq;
	}
	public void setSgxq(String sgxq) {
		this.sgxq = sgxq;
	}
	public String getSgsj() {
		return sgsj;
	}
	public void setSgsj(String sgsj) {
		this.sgsj = sgsj;
	}
	public String getSgdd() {
		return sgdd;
	}
	public void setSgdd(String sgdd) {
		this.sgdd = sgdd;
	}
	public String getJdcsyr() {
		return jdcsyr;
	}
	public void setJdcsyr(String jdcsyr) {
		this.jdcsyr = jdcsyr;
	}
	public String getJdcglr() {
		return jdcglr;
	}
	public void setJdcglr(String jdcglr) {
		this.jdcglr = jdcglr;
	}
	public String getGajgrdyj() {
		return gajgrdyj;
	}
	public void setGajgrdyj(String gajgrdyj) {
		this.gajgrdyj = gajgrdyj;
	}
	public String getShrjzd() {
		return shrjzd;
	}
	public void setShrjzd(String shrjzd) {
		this.shrjzd = shrjzd;
	}
	public String getShrzy() {
		return shrzy;
	}
	public void setShrzy(String shrzy) {
		this.shrzy = shrzy;
	}
	public String getSftb() {
		return sftb;
	}
	public void setSftb(String sftb) {
		this.sftb = sftb;
	}
	public String getTbxz() {
		return tbxz;
	}
	public void setTbxz(String tbxz) {
		this.tbxz = tbxz;
	}
	public String getSfzbxqn() {
		return sfzbxqn;
	}
	public void setSfzbxqn(String sfzbxqn) {
		this.sfzbxqn = sfzbxqn;
	}
	public String getSfxqpf() {
		return sfxqpf;
	}
	public void setSfxqpf(String sfxqpf) {
		this.sfxqpf = sfxqpf;
	}
	public String getJdsfkk() {
		return jdsfkk;
	}
	public void setJdsfkk(String jdsfkk) {
		this.jdsfkk = jdsfkk;
	}
	public String getShangQing() {
		return shangQing;
	}
	public void setShangQing(String shangQing) {
		this.shangQing = shangQing;
	}
	public String getRealPay() {
		return realPay;
	}
	public void setRealPay(String realPay) {
		this.realPay = realPay;
	}
	public String getIdentifyContent() {
		return identifyContent;
	}
	public void setIdentifyContent(String identifyContent) {
		this.identifyContent = identifyContent;
	}
	@Override
	public String toString() {
		return "WsajjbqkModel [qsdl=" + qsdl + ", qsygscd=" + qsygscd
				+ ", qsbgbcd=" + qsbgbcd + ", qsdsryjd=" + qsdsryjd
				+ ", qszjd=" + qszjd + ", qssld=" + qssld + ", qsfsscd="
				+ qsfsscd + ", qspjd=" + qspjd + ", bsdl=" + bsdl + ", ssrscd="
				+ ssrscd + ", bssrbcd=" + bssrbcd + ", bsdsryjd=" + bsdsryjd
				+ ", bssld=" + bssld + ", bszjd=" + bszjd + ", ygscd=" + ygscd
				+ ", bgbcd=" + bgbcd + ", cmssd=" + cmssd + ", zjd=" + zjd
				+ ", dsryjd=" + dsryjd + ", fsscd=" + fsscd + ", fsbcd="
				+ fsbcd + ", xzsszyd=" + xzsszyd + ", zkdl=" + zkdl + ", bhdl="
				+ bhdl + ", xsbssld=" + xsbssld + ", xsqssld=" + xsqssld
				+ ", qscpyzypjjg=" + qscpyzypjjg + ", qscmssyzj=" + qscmssyzj
				+ ", ssssbhyj=" + ssssbhyj + ", jcjgyj=" + jcjgyj
				+ ", fdmsssqqd=" + fdmsssqqd + ", zkss=" + zkss + ", zkzj="
				+ zkzj + ", zkyj=" + zkyj + ", bgrbc=" + bgrbc + ", bhrbh="
				+ bhrbh + ", ssqqjeList=" + ssqqjeList + ", sgxq=" + sgxq
				+ ", sgsj=" + sgsj + ", sgdd=" + sgdd + ", jdcsyr=" + jdcsyr
				+ ", jdcglr=" + jdcglr + ", gajgrdyj=" + gajgrdyj + ", shrjzd="
				+ shrjzd + ", shrzy=" + shrzy + ", sftb=" + sftb + ", tbxz="
				+ tbxz + ", sfzbxqn=" + sfzbxqn + ", sfxqpf=" + sfxqpf
				+ ", shangQing=" + shangQing + ", realPay=" + realPay
				+ ", identifyContent=" + identifyContent + ", jdsfkk=" + jdsfkk
				+ "]";
	}
	
	
}
