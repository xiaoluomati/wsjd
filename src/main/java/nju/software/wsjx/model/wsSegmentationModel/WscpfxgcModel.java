package nju.software.wsjx.model.wsSegmentationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFdlxModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcZdlxModel;

/**
 * 文书裁判分析过程model
 * @author LXY
 *
 */
public class WscpfxgcModel {
	/**
	 * 法条Model
	 */
	private ArrayList<WscpfxgcFtModel> ftModellist;
	/**
	 * 结案方式类型
	 */
	private String Jafslx;
	/**
	 * 行政赔偿，行政案件特有
	 */
	private String xzpc;
	/**
	 * 一审案件来源，一审案件特有
	 */
	private String ysajly;
	/**
	 * 是否经过行政复议
	 */
	private String sfjgxzfy;
	/**
	 * 行政行为违法补救
	 */
	private String xzxwwfbj;
	/**
	 * 开庭前申请撤诉
	 */
	private String ktqsqchss;
	
	//刑事相关：
	/**
	 * 法定量刑
	 */
	private ArrayList<WscpfxgcFdlxModel> fdlxModel; 
	/**
	 * 酌定量刑
	 */
	private ArrayList<WscpfxgcZdlxModel> zdlxModel; 
	/**
	 * 共同犯罪
	 */
	private String gtfz;
	/**
	 * 被告人同意认罪程序
	 */
	private String bgrtyrzcx;
	
	public String getJafslx() {
		return Jafslx;
	}
	public void setJafslx(String jafslx) {
		Jafslx = jafslx;
	}
	public String getXzpc() {
		return xzpc;
	}
	public void setXzpc(String xzpc) {
		this.xzpc = xzpc;
	}
	public String getYsajly() {
		return ysajly;
	}
	public void setYsajly(String ysajly) {
		this.ysajly = ysajly;
	}
	public String getSfjgxzfy() {
		return sfjgxzfy;
	}
	public void setSfjgxzfy(String sfjgxzfy) {
		this.sfjgxzfy = sfjgxzfy;
	}
	public String getXzxwwfbj() {
		return xzxwwfbj;
	}
	public void setXzxwwfbj(String xzxwwfbj) {
		this.xzxwwfbj = xzxwwfbj;
	}
	public String getKtqsqchss() {
		return ktqsqchss;
	}
	public void setKtqsqchss(String ktqsqchss) {
		this.ktqsqchss = ktqsqchss;
	}
	public ArrayList<WscpfxgcFdlxModel> getFdlxModel() {
		return fdlxModel;
	}
	public void setFdlxModel(ArrayList<WscpfxgcFdlxModel> fdlxModel) {
		this.fdlxModel = fdlxModel;
	}
	public ArrayList<WscpfxgcZdlxModel> getZdlxModel() {
		return zdlxModel;
	}
	public void setZdlxModel(ArrayList<WscpfxgcZdlxModel> zdlxModel) {
		this.zdlxModel = zdlxModel;
	}
	public ArrayList<WscpfxgcFtModel> getFtModellist() {
		return ftModellist;
	}
	public void setFtModellist(ArrayList<WscpfxgcFtModel> ftModellist) {
		this.ftModellist = ftModellist;
	}
	public String getGtfz() {
		return gtfz;
	}
	public void setGtfz(String gtfz) {
		this.gtfz = gtfz;
	}
	public String getBgrtyrzcx() {
		return bgrtyrzcx;
	}
	public void setBgrtyrzcx(String bgrtyrzcx) {
		this.bgrtyrzcx = bgrtyrzcx;
	}
}
