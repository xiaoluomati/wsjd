package nju.software.wsjx.service.model.xs;

import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;

/**
 * 刑事一审判决结果
 * @author 服兰
 *
 */
public class XsPjjgModel {
	
	private String tcgxyy;//提出管辖异议
	private  String jafs;//结案方式
	private List<String> gpyy;//改判原因
	private String ysxsbfpjjg;//一审刑事部分裁判结果
	private String fdmscpjg;//附带民事部分判决结果
	private List<XspjjgfzModel> pjjgfzModels;//刑事判决结果分组
	private String kssz;//可上诉至
	private String ssqx;//上诉期限
	private String sstjcl;//上诉提交材料
	private List<String> csrjh;//撤诉人集合
	private String cslx;//撤诉类型
	private String dfdmspccl;//对附带民事赔偿处理
	private FdmspjfzModel fdmspjfzModel;//附带民事判决结果分组
	private String jtfz;//集团犯罪
	
	public XsPjjgModel() {
		super();
	}
	public String getTcgxyy() {
		return tcgxyy;
	}
	public void setTcgxyy(String tcgxyy) {
		this.tcgxyy = tcgxyy;
	}
	public String getJafs() {
		return jafs;
	}
	public void setJafs(String jafs) {
		this.jafs = jafs;
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
	public String getKssz() {
		return kssz;
	}
	public void setKssz(String kssz) {
		this.kssz = kssz;
	}
	public String getSsqx() {
		return ssqx;
	}
	public void setSsqx(String ssqx) {
		this.ssqx = ssqx;
	}
	public String getSstjcl() {
		return sstjcl;
	}
	public void setSstjcl(String sstjcl) {
		this.sstjcl = sstjcl;
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
	public FdmspjfzModel getFdmspjfzModel() {
		return fdmspjfzModel;
	}
	public void setFdmspjfzModel(FdmspjfzModel fdmspjfzModel) {
		this.fdmspjfzModel = fdmspjfzModel;
	}
	public String getDfdmspccl() {
		return dfdmspccl;
	}
	public void setDfdmspccl(String dfdmspccl) {
		this.dfdmspccl = dfdmspccl;
	}
	public List<String> getGpyy() {
		return gpyy;
	}
	public void setGpyy(List<String> gpyy) {
		this.gpyy = gpyy;
	}
	public String getJtfz() {
		return jtfz;
	}
	public void setJtfz(String jtfz) {
		this.jtfz = jtfz;
	}

}
