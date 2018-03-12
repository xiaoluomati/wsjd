package nju.software.wsjx.model.caseinfo;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;

import nju.software.wsjx.parserule.wswsparserule.WsParseRule;

import java.sql.Timestamp;

/**
 * 文档的父类
 * @author lr12
 *
 */
public abstract class BaseCaseInfo {

	 String wdbs;//文档标识
	 String ah;//案号
	 String fy;//法院
	 String ajxz;//案件性质
	 String spcx;//审判程序
	 String zay;//主案由
	 String ajly;//案件来源
	 String ktsl;//开庭审理
	 String dtxp;//当庭宣判
	 String jafs;//结案方式
	 Timestamp larq;//立案日期
	 Timestamp jarq;//结案日期
	 String spy;//审判员
	 String sjy;//书记员
	
	public String getWdbs() {
		return wdbs;
	}

	public void setWdbs(String wdbs) {
		this.wdbs = wdbs;
	}

	public String getAh() {
		return ah;
	}

	public void setAh(String ah) {
		this.ah = ah;
	}

	public String getFy() {
		return fy;
	}

	public void setFy(String fy) {
		this.fy = fy;
	}

	public String getAjxz() {
		return ajxz;
	}

	public void setAjxz(String ajxz) {
		this.ajxz = ajxz;
	}

	public String getSpcx() {
		return spcx;
	}

	public void setSpcx(String spcx) {
		this.spcx = spcx;
	}

	public String getZay() {
		return zay;
	}

	public void setZay(String zay) {
		this.zay = zay;
	}

	public String getAjly() {
		return ajly;
	}

	public void setAjly(String ajly) {
		this.ajly = ajly;
	}

	public String getJafs() {
		return jafs;
	}

	public void setJafs(String jafs) {
		this.jafs = jafs;
	}

	//抽象 把生成自己的方法委托给自己，根据各个段落的model填充自己属性
	public abstract void generate(WsModel wsModel);
	
}
