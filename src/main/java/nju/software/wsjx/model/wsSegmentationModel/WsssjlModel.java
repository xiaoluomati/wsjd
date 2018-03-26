package nju.software.wsjx.model.wsSegmentationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjd.model.gxWsModel.ZyfyModel;
import nju.software.wsjd.model.ysptWsModel.ssjl.FsModel;
import nju.software.wsjd.model.ysptWsModel.ssjl.SsrqydsrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsssjlZkjlModel;

/**
 * hufk
 * 文书诉讼记录
 */
public class WsssjlModel {
	private String ay;//案由
	private String wzay;//完整案由
	private String ajly;//案件来源
	private String ajsj;//案件涉及
	private String ktsl;//开庭审理
	private String aydm;//案由代码
	private ArrayList<String> ktrq;//开庭日期(;)
	private ArrayList<String> qsah;//前审案号(;)
	private ArrayList<String>zsscajah;//再审审查案件案号
	private String qsfy;//前审法院
	private String ktslxx;//开庭审理信息
	private String bgkslyy;//不公开审理原因
	private String larq;//立案日期 
	
	private String ysajsycx;//一审案件适用程序 
	private String jyzpt;//简易转普通 
	private String ysajly;//一审案件来源 
	private String slrq;//受理日期
	private String spzz;//审判组织
	private String drsp;//独任审判
	private String sqcsrq;//申请撤诉日期
	private HashMap<String,String> qxrxx;//缺席人信息(单独)
	private HashMap<String,String> ctrxx;//出庭人信息(单独)
	private String qsrq;//起诉日期
	private String bgzyldct;//被告主要领导出庭
	private String xzxwzl;//行政行为种类
	private String xzqqxwzl;//行政侵权行为种类
	
	private String jysyjycx;//建议适用简易程序
	private String msbfjxsl;//附带民事部分继续审理
	private String ssxz;//诉讼性质
	private String jcy;//检察员
	private String js;//检察员角色
	private ArrayList<WsssjlZkjlModel> wsssjlZkjl;//文书诉讼记录指控记录
	private String qszay;//起诉主案由
	private String gsjg;//公诉机关
	private String gsah;//公诉案号
	private String slztqfdmsss;//审理中提起附带民事诉讼
	private String jcyjyyqsl;//检察院建议延期审理
	private String snft;//少年法庭
	
	private String xzesqsah;//行政二审前审案号
	private String xzzsqsah;//行政再审前审案号
	private String xszsqsah;//刑事再审前审案号
	private String qsahlasj;//前审案号立案时间
	private String qsland;//前审立案年度
	private String qsfyjc;//前审法院简称
	private String fyjb;//前审法院级别
	private String qsahsxh;//前审案号顺序号
	private String qscpsj;//前审裁判时间
	private String qswszl;//前审文书种类
	private String qsajyl;//前审案件由来
	private String qsjafs;//前审结案方式
	private String qssj;//前审审级
	
	private String sshksfw;//上诉或抗诉范围
	private String qspj;//前审判决
	private String xsesqsah;//刑事二审前审案号
	private String bzfymc;//标准法院名称
	private String qsgsjg;//原公诉机关

	private String yg;//原告 管辖 第一审 变更当事人
	private String bg;//被告 管辖 第一审 变更当事人
	private String lafy;//立案法院 管辖
	private String ssr;//上诉人 管辖
	private String sscdfymc;//上诉裁定法院名称 管辖
	private String sscdah;//上诉裁定案号 管辖
	private List<ZyfyModel> zymf;//争议法院 管辖
	private String bqfymc;//报请时间 管辖
	private String bqsj;//报请法院 管辖

	public String gxString(){
		return "temo{" +
				"yg='" + yg + '\'' +
				", bg='" + bg + '\'' +
				", lafy='" + lafy + '\'' +
				", ssr='" + ssr + '\'' +
				", sscdfymc='" + sscdfymc + '\'' +
				", sscdah='" + sscdah + '\'' +
				", zymf=" + zymf +
				", bqfymc='" + bqfymc + '\'' +
				", bqsj='" + bqsj + '\'' +
				'}';
	}

	private SsrqydsrModel qsz;//起诉状 第一审
	private SsrqydsrModel fsz;//反诉状 第一审
	private SsrqydsrModel jjct;//拒绝出庭 第一审
	private SsrqydsrModel cs;//撤诉 第一审
	private FsModel fs;//反诉 第一审

	private String bgsqr;//申请人 变更当事人
	private String bgsqrq;//申请日期 变更当事人

	public String getBgsqr() {
		return bgsqr;
	}

	public void setBgsqr(String bgsqr) {
		this.bgsqr = bgsqr;
	}

	public String getBgsqrq() {
		return bgsqrq;
	}

	public void setBgsqrq(String bgsqrq) {
		this.bgsqrq = bgsqrq;
	}

	public SsrqydsrModel getQsz() {
		return qsz;
	}

	public void setQsz(SsrqydsrModel qsz) {
		this.qsz = qsz;
	}

	public SsrqydsrModel getFsz() {
		return fsz;
	}

	public void setFsz(SsrqydsrModel fsz) {
		this.fsz = fsz;
	}

	public SsrqydsrModel getJjct() {
		return jjct;
	}

	public void setJjct(SsrqydsrModel jjct) {
		this.jjct = jjct;
	}

	public SsrqydsrModel getCs() {
		return cs;
	}

	public void setCs(SsrqydsrModel cs) {
		this.cs = cs;
	}

	public FsModel getFs() {
		return fs;
	}

	public void setFs(FsModel fs) {
		this.fs = fs;
	}

	public String getXzzsqsah() {
		return xzzsqsah;
	}

	public void setXzzsqsah(String xzzsqsah) {
		this.xzzsqsah = xzzsqsah;
	}

	public String getYg() {
		return yg;
	}

	public void setYg(String yg) {
		this.yg = yg;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public String getLafy() {
		return lafy;
	}

	public void setLafy(String lafy) {
		this.lafy = lafy;
	}

	public String getSsr() {
		return ssr;
	}

	public void setSsr(String ssr) {
		this.ssr = ssr;
	}

	public String getSscdfymc() {
		return sscdfymc;
	}

	public void setSscdfymc(String sscdfymc) {
		this.sscdfymc = sscdfymc;
	}

	public String getSscdah() {
		return sscdah;
	}

	public void setSscdah(String sscdah) {
		this.sscdah = sscdah;
	}

	public List<ZyfyModel> getZymf() {
		return zymf;
	}

	public void setZymf(List<ZyfyModel> zymf) {
		this.zymf = zymf;
	}

	public String getBqfymc() {
		return bqfymc;
	}

	public void setBqfymc(String bqfymc) {
		this.bqfymc = bqfymc;
	}

	public String getBqsj() {
		return bqsj;
	}

	public void setBqsj(String bqsj) {
		this.bqsj = bqsj;
	}

	public String getAy() {
		return ay;
	}
	public void setAy(String ay) {
		this.ay = ay;
	}
	public String getWzay() {
		return wzay;
	}
	public void setWzay(String wzay) {
		this.wzay = wzay;
	}
	public String getAjly() {
		return ajly;
	}
	public void setAjly(String ajly) {
		this.ajly = ajly;
	}
	public String getAjsj() {
		return ajsj;
	}
	public void setAjsj(String ajsj) {
		this.ajsj = ajsj;
	}
	public String getKtsl() {
		return ktsl;
	}
	public void setKtsl(String ktsl) {
		this.ktsl = ktsl;
	}
	public String getAydm() {
		return aydm;
	}
	public void setAydm(String aydm) {
		this.aydm = aydm;
	}
	public ArrayList<String> getKtrq() {
		return ktrq;
	}
	public void setKtrq(ArrayList<String> ktrq) {
		this.ktrq = ktrq;
	}
	public ArrayList<String> getQsah() {
		return qsah;
	}
	public void setQsah(ArrayList<String> qsah) {
		this.qsah = qsah;
	}
	public String getQsfy() {
		return qsfy;
	}
	public ArrayList<String> getZsscajah(){
		return zsscajah;
	}
	public void setZsscajah(ArrayList<String> zsscajah){
		this.zsscajah=zsscajah;
	}
	public void setQsfy(String qsfy) {
		this.qsfy = qsfy;
	}
	public String getKtslxx() {
		return ktslxx;
	}
	public void setKtslxx(String ktslxx) {
		this.ktslxx = ktslxx;
	}
	public String getBgkslyy() {
		return bgkslyy;
	}
	public void setBgkslyy(String bgkslyy) {
		this.bgkslyy = bgkslyy;
	}
	public String getLarq() {
		return larq;
	}
	public void setLarq(String larq) {
		this.larq = larq;
	}
	
	public String getYsajsycx() {
		return ysajsycx;
	}
	public void setYsajsycx(String ysajsycx) {
		this.ysajsycx = ysajsycx;
	}
	public String getJyzpt() {
		return jyzpt;
	}
	public void setJyzpt(String jyzpt) {
		this.jyzpt = jyzpt;
	}
	public String getYsajly() {
		return ysajly;
	}
	public void setYsajly(String ysajly) {
		this.ysajly = ysajly;
	}
	public String getSlrq() {
		return slrq;
	}
	public void setSlrq(String slrq) {
		this.slrq = slrq;
	}
	public String getSpzz() {
		return spzz;
	}
	public void setSpzz(String spzz) {
		this.spzz = spzz;
	}
	public String getDrsp() {
		return drsp;
	}
	public void setDrsp(String drsp) {
		this.drsp = drsp;
	}
	public String getSqcsrq() {
		return sqcsrq;
	}
	public void setSqcsrq(String sqcsrq) {
		this.sqcsrq = sqcsrq;
	}
	public HashMap<String, String> getQxrxx() {
		return qxrxx;
	}
	public void setQxrxx(HashMap<String, String> qxrxx) {
		this.qxrxx = qxrxx;
	}
	public HashMap<String, String> getCtrxx() {
		return ctrxx;
	}
	public void setCtrxx(HashMap<String, String> ctrxx) {
		this.ctrxx = ctrxx;
	}
	public String getXzxwzl() {
		return xzxwzl;
	}
	public void setXzxwzl(String xzxwzl) {
		this.xzxwzl = xzxwzl;
	}
	public String getXzqqxwzl() {
		return xzqqxwzl;
	}
	public void setXzqqxwzl(String xzqqxwzl) {
		this.xzqqxwzl = xzqqxwzl;
	}
	public String getBgzyldct() {
		return bgzyldct;
	}
	public void setBgzyldct(String bgzyldct) {
		this.bgzyldct = bgzyldct;
	}
	public String getQsrq() {
		return qsrq;
	}
	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}
	public String getSsxz() {
		return ssxz;
	}
	public void setSsxz(String ssxz) {
		this.ssxz = ssxz;
	}
	public String getQszay() {
		return qszay;
	}
	public void setQszay(String qszay) {
		this.qszay = qszay;
	}
	public String getGsjg() {
		return gsjg;
	}
	public void setGsjg(String gsjg) {
		this.gsjg = gsjg;
	}
	public String getGsah() {
		return gsah;
	}
	public void setGsah(String gsah) {
		this.gsah = gsah;
	}
	public String getJcyjyyqsl() {
		return jcyjyyqsl;
	}
	public void setJcyjyyqsl(String jcyjyyqsl) {
		this.jcyjyyqsl = jcyjyyqsl;
	}
	public String getSnft() {
		return snft;
	}
	public void setSnft(String snft) {
		this.snft = snft;
	}
	public String getJysyjycx() {
		return jysyjycx;
	}
	public void setJysyjycx(String jysyjycx) {
		this.jysyjycx = jysyjycx;
	}
	public ArrayList<WsssjlZkjlModel> getWsssjlZkjl() {
		return wsssjlZkjl;
	}
	public void setWsssjlZkjl(ArrayList<WsssjlZkjlModel> wsssjlZkjl) {
		this.wsssjlZkjl = wsssjlZkjl;
	}
	public String getSlztqfdmsss() {
		return slztqfdmsss;
	}
	public void setSlztqfdmsss(String slztqfdmsss) {
		this.slztqfdmsss = slztqfdmsss;
	}
	public String getJcy() {
		return jcy;
	}
	public void setJcy(String jcy) {
		this.jcy = jcy;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getQsfyjc() {
		return qsfyjc;
	}
	public void setQsfyjc(String qsfyjc) {
		this.qsfyjc = qsfyjc;
	}
	public String getQsahsxh() {
		return qsahsxh;
	}
	public void setQsahsxh(String qsahsxh) {
		this.qsahsxh = qsahsxh;
	}
	public String getXzesqsah() {
		return xzesqsah;
	}
	public void setXzesqsah(String xzesqsah) {
		this.xzesqsah = xzesqsah;
	}
	public String getQswszl() {
		return qswszl;
	}
	public void setQswszl(String qswszl) {
		this.qswszl = qswszl;
	}
	public String getQsjafs() {
		return qsjafs;
	}
	public void setQsjafs(String qsjafs) {
		this.qsjafs = qsjafs;
	}
	public String getQssj() {
		return qssj;
	}
	public void setQssj(String qssj) {
		this.qssj = qssj;
	}
	public String getMsbfjxsl() {
		return msbfjxsl;
	}
	public void setMsbfjxsl(String msbfjxsl) {
		this.msbfjxsl = msbfjxsl;
	}
	public String getQsland() {
		return qsland;
	}
	public void setQsland(String qsland) {
		this.qsland = qsland;
	}
	public String getFyjb() {
		return fyjb;
	}
	public void setFyjb(String fyjb) {
		this.fyjb = fyjb;
	}
	public String getQscpsj() {
		return qscpsj;
	}
	public void setQscpsj(String qscpsj) {
		this.qscpsj = qscpsj;
	}
	public String getQsajyl() {
		return qsajyl;
	}
	public void setQsajyl(String qsajyl) {
		this.qsajyl = qsajyl;
	}
	public String getSshksfw() {
		return sshksfw;
	}
	public void setSshksfw(String sshksfw) {
		this.sshksfw = sshksfw;
	}
	public String getQspj() {
		return qspj;
	}
	public void setQspj(String qspj) {
		this.qspj = qspj;
	}
	public String getXsesqsah() {
		return xsesqsah;
	}
	public void setXsesqsah(String xsesqsah) {
		this.xsesqsah = xsesqsah;
	}
	public String getXszsqsah() {
		return xszsqsah;
	}
	public void setXszsqsah(String xszsqsah) {
		this.xszsqsah = xszsqsah;
	}
	public String getQsahlasj() {
		return qsahlasj;
	}
	public void setQsahlasj(String qsahlasj) {
		this.qsahlasj = qsahlasj;
	}
	public String getBzfymc() {
		return bzfymc;
	}
	public void setBzfymc(String bzfymc) {
		this.bzfymc = bzfymc;
	}
	public String getQsgsjg() {
		return qsgsjg;
	}
	public void setQsgsjg(String qsgsjg) {
		this.qsgsjg = qsgsjg;
	}
	@Override
	public String toString() {
		return "WsssjlModel [ay=" + ay + ", wzay=" + wzay + ", ajly=" + ajly
				+ ", ajsj=" + ajsj + ", ktsl=" + ktsl + ", aydm=" + aydm
				+ ", ktrq=" + ktrq + ", qsah=" + qsah + ", qsfy=" + qsfy
				+ ", ktslxx=" + ktslxx + ", bgkslyy=" + bgkslyy + ", larq="
				+ larq + ", ysajsycx=" + ysajsycx + ", jyzpt=" + jyzpt
				+ ", ysajly=" + ysajly + ", slrq=" + slrq + ", spzz=" + spzz
				+ ", drsp=" + drsp + ", sqcsrq=" + sqcsrq + ", qxrxx=" + qxrxx
				+ ", ctrxx=" + ctrxx + ", qsrq=" + qsrq + ", bgzyldct="
				+ bgzyldct + ", xzxwzl=" + xzxwzl + ", xzqqxwzl=" + xzqqxwzl
				+ ", jysyjycx=" + jysyjycx + ", msbfjxsl=" + msbfjxsl
				+ ", ssxz=" + ssxz + ", jcy=" + jcy + ", js=" + js
				+ ", wsssjlZkjl=" + wsssjlZkjl + ", qszay=" + qszay + ", gsjg="
				+ gsjg + ", gsah=" + gsah + ", slztqfdmsss=" + slztqfdmsss
				+ ", jcyjyyqsl=" + jcyjyyqsl + ", snft=" + snft + ", xzesqsah="
				+ xzesqsah + ", qsland=" + qsland + ", qsfyjc=" + qsfyjc
				+ ", fyjb=" + fyjb + ", qsahsxh=" + qsahsxh + ", qscpsj="
				+ qscpsj + ", qswszl=" + qswszl + ", qsajyl=" + qsajyl
				+ ", qsjafs=" + qsjafs + ", qssj=" + qssj + ", sshksfw="
				+ sshksfw + ", qspj=" + qspj + ", xsesqsah=" + xsesqsah
				+ ", bzfymc=" + bzfymc + ", qsgsjg=" + qsgsjg + "]";
	}
	
	
}
