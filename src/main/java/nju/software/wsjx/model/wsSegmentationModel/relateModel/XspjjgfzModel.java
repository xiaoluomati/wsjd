package nju.software.wsjx.model.wsSegmentationModel.relateModel;

import java.util.List;

import nju.software.wsjx.service.model.xs.PfModel;
import nju.software.wsjx.service.model.xs.ZmModel;

/**
 * 刑事判决结果分组
 * @author 服兰
 *
 */
public class XspjjgfzModel {
	
	private String sscyr;//诉讼参与人
	private List<PfModel> dzpf;//单罪判罚(本审判决结果
	private PfModel zxpf;//执行判罚(本审判决结果
	private ZmModel pjzzm;//判决主罪名(本审判决结果
	private String xqksrq;//刑期开始日期
	private String xqjsrq;//刑期结束日期
	private String xqzdbf;//刑期折抵办法
	private String mzhwzsf;//免罪或无罪释放
	private String szbf;//数罪并罚：是/否
	private String hblx;//合并量刑：是/否
	private List<PfModel> yzpf;//原罪判罚(原罪判决结果
	private String essljg;
	private String eslxjg;
	
	public XspjjgfzModel() {
		super();
	}
	
	public XspjjgfzModel(String sscyr) {
		super();
		this.sscyr = sscyr;
	}

	public String getSscyr() {
		return sscyr;
	}
	public void setSscyr(String sscyr) {
		this.sscyr = sscyr;
	}
	
	
   public List<PfModel> getDzpf() {
		return dzpf;
	}
	public void setDzpf(List<PfModel> dzpf) {
		this.dzpf = dzpf;
	}
	//	public PfModel getDzpf() {
//		return dzpf;
//	}
//	public void setDzpf(PfModel dzpf) {
//		this.dzpf = dzpf;
//	}
	public PfModel getZxpf() {
		return zxpf;
	}
	public void setZxpf(PfModel zxpf) {
		this.zxpf = zxpf;
	}
	public String getXqksrq() {
		return xqksrq;
	}
	public void setXqksrq(String xqksrq) {
		this.xqksrq = xqksrq;
	}
	public String getXqjsrq() {
		return xqjsrq;
	}
	public void setXqjsrq(String xqjsrq) {
		this.xqjsrq = xqjsrq;
	}
	public ZmModel getPjzzm() {
		return pjzzm;
	}
	public void setPjzzm(ZmModel pjzzm) {
		this.pjzzm = pjzzm;
	}
	public String getXqzdbf() {
		return xqzdbf;
	}
	public void setXqzdbf(String xqzdbf) {
		this.xqzdbf = xqzdbf;
	}

	public String getSzbf() {
		return szbf;
	}

	public void setSzbf(String szbf) {
		this.szbf = szbf;
	}

	public String getHblx() {
		return hblx;
	}

	public void setHblx(String hblx) {
		this.hblx = hblx;
	}

	public String getMzhwzsf() {
		return mzhwzsf;
	}

	public void setMzhwzsf(String mzhwzsf) {
		this.mzhwzsf = mzhwzsf;
	}

	public List<PfModel> getYzpf() {
		return yzpf;
	}

	public void setYzpf(List<PfModel> yzpf) {
		this.yzpf = yzpf;
	}

	public String getEssljg() {
		return essljg;
	}

	public void setEssljg(String essljg) {
		this.essljg = essljg;
	}

	public String getEslxjg() {
		return eslxjg;
	}

	public void setEslxjg(String eslxjg) {
		this.eslxjg = eslxjg;
	}
	

}
