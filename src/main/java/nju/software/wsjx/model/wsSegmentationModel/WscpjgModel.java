
package nju.software.wsjx.model.wsSegmentationModel;

import java.util.List;
import java.util.Map;

import nju.software.wsjd.model.msesWsModel.QscdModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;
import nju.software.wsjx.service.model.xs.FdmspjfzModel;

/**
 * 文书裁判结果 model
 * @author lr12
 *
 */
public class WscpjgModel {
	private String ajslf;// 案件受理费，指案件受理费所的句
	
	List<String> cpjgnr;// 裁判结果内容
	
	private String jafs;//结案方式
	
	private String sfzcssqq;//是否支持诉讼请求
	
	private String sbsf;//胜败诉方
	/**
	 * 是否支持行政赔偿，行政案件特有
	 */
	private String sfzcxzpc;
	/**
	 * 行政赔偿金额，行政案件特有
	 */
	private String xzpcje;
	private List<String> jabde;//结案标的额
	private String jabdze;//结案标的总额
	
	private String sffhcs;//是否发回重审
	private String fhcsyy;//发回重审原因
	private List<PjjgnrModel> pjjgList;//判决结果
	private String kssz;//可上诉至
	private String sstjcl;//上诉提交材料
	private String ssqx;//上诉期限
	private List<String> csrjh;//撤诉人集合
	private String cslx;//撤诉类型
//	private List<WsCpjgssfModel> ssfModelList;//诉讼费
	private  WsCpjgssfModel  ssfModel ;//诉讼费
	private String sftcgxyy;//提出管辖异议
	private String pcsxsfzq;//交通事故赔偿顺序是否正确
	private String bsgssfbg;//当事人保险公司是否作为被告
	/**
	 * 刑事裁判结果
	 */
	private String tcgxyy;//提出管辖异议
	private List<String> gpyy;//改判原因
	private String ysxsbfpjjg;//一审刑事部分裁判结果
	private String fdmscpjg;//附带民事部分判决结果
	private List<XspjjgfzModel> pjjgfzModels;//刑事判决结果分组
	private String dfdmspccl;//对附带民事赔偿处理
	private FdmspjfzModel fdmspjfzModel;//附带民事判决结果分组
	private String jtfz;//集团犯罪

	private String tdr;//替代人 诉讼参加人
	private String btdr;//被替代人 诉讼参加人
	private String ssdw;//诉讼地位 诉讼参加人
	private String tcssr;//退出诉讼人 诉讼参加人

	private String ssr;//上诉人 民事二审
	private QscdModel qscdModel;//前审裁定的处理 民事二审

	public String getSsr() {
		return ssr;
	}

	public void setSsr(String ssr) {
		this.ssr = ssr;
	}

	public QscdModel getQscdModel() {
		return qscdModel;
	}

	public void setQscdModel(QscdModel qscdModel) {
		this.qscdModel = qscdModel;
	}

	public String getTdr() {
		return tdr;
	}

	public void setTdr(String tdr) {
		this.tdr = tdr;
	}

	public String getBtdr() {
		return btdr;
	}

	public void setBtdr(String btdr) {
		this.btdr = btdr;
	}

	public String getSsdw() {
		return ssdw;
	}

	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}

	public String getTcssr() {
		return tcssr;
	}

	public void setTcssr(String tcssr) {
		this.tcssr = tcssr;
	}

	public String getAjslf() {
		return ajslf;
	}
	public String getTcgxyy() {
		return tcgxyy;
	}
	public void setTcgxyy(String tcgxyy) {
		this.tcgxyy = tcgxyy;
	}
	public List<String> getGpyy() {
		return gpyy;
	}
	public void setGpyy(List<String> gpyy) {
		this.gpyy = gpyy;
	}
	public String getYsxsbfpjjg() {
		return ysxsbfpjjg;
	}
	public void setYsxsbfpjjg(String ysxsbfpjjg) {
		this.ysxsbfpjjg = ysxsbfpjjg;
	}
	public String getFdmscpjg() {
		return fdmscpjg;
	}
	public void setFdmscpjg(String fdmscpjg) {
		this.fdmscpjg = fdmscpjg;
	}
	public List<XspjjgfzModel> getPjjgfzModels() {
		return pjjgfzModels;
	}
	public void setPjjgfzModels(List<XspjjgfzModel> pjjgfzModels) {
		this.pjjgfzModels = pjjgfzModels;
	}
	public String getDfdmspccl() {
		return dfdmspccl;
	}
	public void setDfdmspccl(String dfdmspccl) {
		this.dfdmspccl = dfdmspccl;
	}
	public FdmspjfzModel getFdmspjfzModel() {
		return fdmspjfzModel;
	}
	public void setFdmspjfzModel(FdmspjfzModel fdmspjfzModel) {
		this.fdmspjfzModel = fdmspjfzModel;
	}
	public String getJtfz() {
		return jtfz;
	}
	public void setJtfz(String jtfz) {
		this.jtfz = jtfz;
	}
	public void setAjslf(String ajslf) {
		this.ajslf = ajslf;
	}

	public List<String> getCpjgnr() {
		return cpjgnr;
	}

	public void setCpjgnr(List<String> cpjgnr) {
		this.cpjgnr = cpjgnr;
	}

	public String getSfzcssqq() {
		return sfzcssqq;
	}

	public void setSfzcssqq(String sfzcssqq) {
		this.sfzcssqq = sfzcssqq;
	}

	public String getSbsf() {
		return sbsf;
	}

	public void setSbsf(String sbsf) {
		this.sbsf = sbsf;
	}
	public String getSfzcxzpc() {
		return sfzcxzpc;
	}
	public void setSfzcxzpc(String sfzcxzpc) {
		this.sfzcxzpc = sfzcxzpc;
	}
	public String getXzpcje() {
		return xzpcje;
	}
	public void setXzpcje(String xzpcje) {
		this.xzpcje = xzpcje;
	}
	public String getSffhcs() {
		return sffhcs;
	}
	public void setSffhcs(String sffhcs) {
		this.sffhcs = sffhcs;
	}
	public String getFhcsyy() {
		return fhcsyy;
	}
	public void setFhcsyy(String fhcsyy) {
		this.fhcsyy = fhcsyy;
	}
	public List<String> getJabde() {
		return jabde;
	}
	public void setJabde(List<String> jabde) {
		this.jabde = jabde;
	}
	public String getJabdze() {
		return jabdze;
	}
	public void setJabdze(String jabdze) {
		this.jabdze = jabdze;
	}
	public List<PjjgnrModel> getPjjgList() {
		return pjjgList;
	}
	public void setPjjgList(List<PjjgnrModel> pjjgList) {
		this.pjjgList = pjjgList;
	}
	public String getKssz() {
		return kssz;
	}
	public void setKssz(String kssz) {
		this.kssz = kssz;
	}
	public String getSstjcl() {
		return sstjcl;
	}
	public void setSstjcl(String sstjcl) {
		this.sstjcl = sstjcl;
	}
	public String getSsqx() {
		return ssqx;
	}
	public void setSsqx(String ssqx) {
		this.ssqx = ssqx;
	}
	public List<String> getCsrjh() {
		return csrjh;
	}
	public void setCsrjh(List<String> csrjh) {
		this.csrjh = csrjh;
	}
	public String getCslx() {
		return cslx;
	}
	public void setCslx(String cslx) {
		this.cslx = cslx;
	}
	public WsCpjgssfModel getSsfModel() {
		return ssfModel;
	}
	public void setSsfModel(WsCpjgssfModel ssfModel) {
		this.ssfModel = ssfModel;
	}
	public String getJafs() {
		return jafs;
	}
	public void setJafs(String jafs) {
		this.jafs = jafs;
	}
	public String getSftcgxyy() {
		return sftcgxyy;
	}
	public void setSftcgxyy(String sftcgxyy) {
		this.sftcgxyy = sftcgxyy;
	}
	public String getPcsxsfzq() {
		return pcsxsfzq;
	}
	public void setPcsxsfzq(String pcsxsfzq) {
		this.pcsxsfzq = pcsxsfzq;
	}
	public String getBsgssfbg() {
		return bsgssfbg;
	}
	public void setBsgssfbg(String bsgssfbg) {
		this.bsgssfbg = bsgssfbg;
	}
	@Override
	public String toString() {
		return "WscpjgModel [ajslf=" + ajslf + ", cpjgnr=" + cpjgnr + ", jafs="
				+ jafs + ", sfzcssqq=" + sfzcssqq + ", sbsf=" + sbsf
				+ ", sfzcxzpc=" + sfzcxzpc + ", xzpcje=" + xzpcje + ", jabde="
				+ jabde + ", jabdze=" + jabdze + ", sffhcs=" + sffhcs
				+ ", fhcsyy=" + fhcsyy + ", pjjgList=" + pjjgList + ", kssz="
				+ kssz + ", sstjcl=" + sstjcl + ", ssqx=" + ssqx + ", csrjh="
				+ csrjh + ", cslx=" + cslx + ", ssfModel=" + ssfModel
				+ ", sftcgxyy=" + sftcgxyy + ", pcsxsfzq=" + pcsxsfzq
				+ ", bsgssfbg=" + bsgssfbg + "]";
	}
	
}
