package nju.software.wsjx.service.model.msysFactorModel;

import java.util.List;

public class JdcjtsgModel {

	/**
	 * 参考文书抽取的model，自行添加需要抽取的要素属性
	 */
	
	/**
	 * 共用项目
	 */
	private String sgfssj;//一、事故发生时间：年月日时分
	private String sgfsdd;//二、事故发生地点
	private String sgjg;//三、事故经过
	private List<String> sgzrrdqk;//四、事故责任认定情况
	private List<String> sgclph;//五、事故车辆牌号
	private List<String> sgclsyz;//六、事故车辆所有者
	private List<String> sgcljsy;//七、事故车辆驾驶员
	private List<String> sgcljqxbxgs;//八、事故车辆交强险保险公司
	private List<String> sgclsyszxbxgs;//九、事故车辆商业三者险保险公司
	private List<String> syszxpcxe;//十、商业三者险赔偿限额
	private List<String> sgqtpczrzt;//十一、事故其他赔偿责任主体
	private String ydfpckse;//十二、已垫付赔偿款数额
	private String dfr;//十三、垫付人

    private String sglx;//事故类型
    private String shlx;//损害类型，分为致人伤残、致人死亡
    private String sfCcss;//是否有财产损失
    private String sfsjjqx;//是否上交交强险
    private String sfsjsyx;//是否上交商业险
    private String hasZrrds;//是否有交通责任认定书
    private String tcmz;//保险公司是否提出免责
	
	/**
	 * 一般人身损害适用、致受害人伤残适用、致受害人死亡适用
	 * */
	private String ylfse;//十四、医疗费数额
	private String zysj;//十五、住院时间
	private String zyhsbzfse;//十六、住院伙食补助费数额
	private String hlq;//十七、护理期
	private String hlfse;//十八、护理费数额
	private String yyq;//十九、营养期
	private String yyfse;//二十、营养费数额
	private String wgq;//二十一、误工期
	private String wgfse;//二十二、误工费数额
	private String jtfse;//二十三、交通费数额
	private String zsfse;//二十四、住宿费数额
	
	/**
	 * 致受害人伤残的适用一般人身损害及以下
	 * */
	private String cjdjjdjlsj;//二十五、残疾等级鉴定结论时间
	private String cjdjjdjl;//二十六、残疾等级鉴定结论
	private String bfyrshfse_meim;//二十七、被扶养人生活费数额
	private String cjpcjse;//二十八、残疾赔偿金数额
	private String jsshfwj_meim;//二十九、精神损害抚慰金
	private String cjshfzjfse;//三十、残疾生活辅助具费数额
	private String dchhlylcdjdyj;//三十一、定残后护理依赖程度鉴定意见
	private String dchhlfse;//三十二、定残后护理费数额
	
	/**
	 * 致受害人死亡的适用一般人身损害及以下
	 * */
	private String swpcjse;//二十五、死亡赔偿金数额
	private String bfyrshfse_death;//二十四、被抚养人生活费数额
	private String jsshfwjse_death;//二十七、精神损害抚慰金数额
	private String szfse;//二十八、丧葬费数额
	private String shrqsjtfse;//二十九、受害人亲属办理丧葬事宜支出的交通费数额
	private String shrqszsfse;//三十、受害人亲属办理丧葬事宜支出的住宿费数额
	private String shrqswgfse;//三十一、受害人亲属办理丧葬事宜支出的误工费数额
	
	/**
	 * 财产损失适用
	 * */
	private String clwxfse;//十四、车辆维修费数额
	private String czwpssfse;//十五、车载物品损失费数额
	private String sjfse;//十六、施救费数额
	private String clczfse;//十七、车辆重置费数额
	private String tyqj;//十八、停运期间
	private String tyssse;//十九、停运损失数额
	private String tctdxjtgjfyse;//二十、通常替代性交通工具费用数额


    public String getSglx() {
        return sglx;
    }

    public void setSglx(String sglx) {
        this.sglx = sglx;
    }

    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
    }

    public String getSfCcss() {
        return sfCcss;
    }

    public void setSfCcss(String sfCcss) {
        this.sfCcss = sfCcss;
    }

    public String getSfsjjqx() {
        return sfsjjqx;
    }

    public void setSfsjjqx(String sfsjjqx) {
        this.sfsjjqx = sfsjjqx;
    }

    public String getSfsjsyx() {
        return sfsjsyx;
    }

    public void setSfsjsyx(String sfsjsyx) {
        this.sfsjsyx = sfsjsyx;
    }

    public String getHasZrrds() {
        return hasZrrds;
    }

    public void setHasZrrds(String hasZrrds) {
        this.hasZrrds = hasZrrds;
    }

    public String getTcmz() {
        return tcmz;
    }

    public void setTcmz(String tcmz) {
        this.tcmz = tcmz;
    }


    public String getSgfssj() {
		return sgfssj;
	}

	public void setSgfssj(String sgfssj) {
		this.sgfssj = sgfssj;
	}

	public String getSgfsdd() {
		return sgfsdd;
	}

	public void setSgfsdd(String sgfsdd) {
		this.sgfsdd = sgfsdd;
	}

	public String getSgjg() {
		return sgjg;
	}

	public void setSgjg(String sgjg) {
		this.sgjg = sgjg;
	}

	public List<String> getSgzrrdqk() {
		return sgzrrdqk;
	}

	public void setSgzrrdqk(List<String> sgzrrdqk) {
		this.sgzrrdqk = sgzrrdqk;
	}

	public List<String> getSgclph() {
		return sgclph;
	}

	public void setSgclph(List<String> sgclph) {
		this.sgclph = sgclph;
	}

	public List<String> getSgclsyz() {
		return sgclsyz;
	}

	public void setSgclsyz(List<String> sgclsyz) {
		this.sgclsyz = sgclsyz;
	}

	public List<String> getSgcljsy() {
		return sgcljsy;
	}

	public void setSgcljsy(List<String> sgcljsy) {
		this.sgcljsy = sgcljsy;
	}

	public List<String> getSgcljqxbxgs() {
		return sgcljqxbxgs;
	}

	public void setSgcljqxbxgs(List<String> sgcljqxbxgs) {
		this.sgcljqxbxgs = sgcljqxbxgs;
	}

	public List<String> getSgclsyszxbxgs() {
		return sgclsyszxbxgs;
	}

	public void setSgclsyszxbxgs(List<String> sgclsyszxbxgs) {
		this.sgclsyszxbxgs = sgclsyszxbxgs;
	}

	public List<String> getSyszxpcxe() {
		return syszxpcxe;
	}

	public void setSyszxpcxe(List<String> syszxpcxe) {
		this.syszxpcxe = syszxpcxe;
	}

	public List<String> getSgqtpczrzt() {
		return sgqtpczrzt;
	}

	public void setSgqtpczrzt(List<String> sgqtpczrzt) {
		this.sgqtpczrzt = sgqtpczrzt;
	}

	public String getYdfpckse() {
		return ydfpckse;
	}

	public void setYdfpckse(String ydfpckse) {
		this.ydfpckse = ydfpckse;
	}

	public String getDfr() {
		return dfr;
	}

	public void setDfr(String dfr) {
		this.dfr = dfr;
	}

	public String getYlfse() {
		return ylfse;
	}

	public void setYlfse(String ylfse) {
		this.ylfse = ylfse;
	}

	public String getZysj() {
		return zysj;
	}

	public void setZysj(String zysj) {
		this.zysj = zysj;
	}

	public String getZyhsbzfse() {
		return zyhsbzfse;
	}

	public void setZyhsbzfse(String zyhsbzfse) {
		this.zyhsbzfse = zyhsbzfse;
	}

	public String getHlq() {
		return hlq;
	}

	public void setHlq(String hlq) {
		this.hlq = hlq;
	}

	public String getHlfse() {
		return hlfse;
	}

	public void setHlfse(String hlfse) {
		this.hlfse = hlfse;
	}

	public String getYyq() {
		return yyq;
	}

	public void setYyq(String yyq) {
		this.yyq = yyq;
	}

	public String getYyfse() {
		return yyfse;
	}

	public void setYyfse(String yyfse) {
		this.yyfse = yyfse;
	}

	public String getWgq() {
		return wgq;
	}

	public void setWgq(String wgq) {
		this.wgq = wgq;
	}

	public String getWgfse() {
		return wgfse;
	}

	public void setWgfse(String wgfse) {
		this.wgfse = wgfse;
	}

	public String getJtfse() {
		return jtfse;
	}

	public void setJtfse(String jtfse) {
		this.jtfse = jtfse;
	}

	public String getZsfse() {
		return zsfse;
	}

	public void setZsfse(String zsfse) {
		this.zsfse = zsfse;
	}

	public String getCjdjjdjlsj() {
		return cjdjjdjlsj;
	}

	public void setCjdjjdjlsj(String cjdjjdjlsj) {
		this.cjdjjdjlsj = cjdjjdjlsj;
	}

	public String getCjdjjdjl() {
		return cjdjjdjl;
	}

	public void setCjdjjdjl(String cjdjjdjl) {
		this.cjdjjdjl = cjdjjdjl;
	}

	public String getBfyrshfse_meim() {
		return bfyrshfse_meim;
	}

	public void setBfyrshfse_meim(String bfyrshfse) {
		this.bfyrshfse_meim = bfyrshfse;
	}

	public String getCjpcjse() {
		return cjpcjse;
	}

	public void setCjpcjse(String cjpcjse) {
		this.cjpcjse = cjpcjse;
	}

	public String getJsshfwj_meim() {
		return jsshfwj_meim;
	}

	public void setJsshfwj_meim(String jsshfwj) {
		this.jsshfwj_meim = jsshfwj;
	}

	public String getCjshfzjfse() {
		return cjshfzjfse;
	}

	public void setCjshfzjfse(String cjshfzjfse) {
		this.cjshfzjfse = cjshfzjfse;
	}

	public String getDchhlylcdjdyj() {
		return dchhlylcdjdyj;
	}

	public void setDchhlylcdjdyj(String dchhlylcdjdyj) {
		this.dchhlylcdjdyj = dchhlylcdjdyj;
	}

	public String getDchhlfse() {
		return dchhlfse;
	}

	public void setDchhlfse(String dchhlfse) {
		this.dchhlfse = dchhlfse;
	}

	public String getSwpcjse() {
		return swpcjse;
	}

	public void setSwpcjse(String swpcjse) {
		this.swpcjse = swpcjse;
	}

	public String getShrqsjtfse() {
		return shrqsjtfse;
	}

	public void setShrqsjtfse(String shrqsjtfse) {
		this.shrqsjtfse = shrqsjtfse;
	}

	public String getShrqszsfse() {
		return shrqszsfse;
	}

	public void setShrqszsfse(String shrqszsfse) {
		this.shrqszsfse = shrqszsfse;
	}

	public String getShrqsblszsyzcdwgfse() {
		return shrqswgfse;
	}

	public void setShrqsblszsyzcdwgfse(String shrqswgfse) {
		this.shrqswgfse = shrqswgfse;
	}

	public String getClwxfse() {
		return clwxfse;
	}

	public void setClwxfse(String clwxfse) {
		this.clwxfse = clwxfse;
	}

	public String getCzwpssfse() {
		return czwpssfse;
	}

	public void setCzwpssfse(String czwpssfse) {
		this.czwpssfse = czwpssfse;
	}

	public String getSjfse() {
		return sjfse;
	}

	public void setSjfse(String sjfse) {
		this.sjfse = sjfse;
	}

	public String getClczfse() {
		return clczfse;
	}

	public void setClczfse(String clczfse) {
		this.clczfse = clczfse;
	}

	public String getTyqj() {
		return tyqj;
	}

	public void setTyqj(String tyqj) {
		this.tyqj = tyqj;
	}

	public String getTyssse() {
		return tyssse;
	}

	public void setTyssse(String tyssse) {
		this.tyssse = tyssse;
	}

	public String getTctdxjtgjfyse() {
		return tctdxjtgjfyse;
	}

	public void setTctdxjtgjfyse(String tctdxjtgjfyse) {
		this.tctdxjtgjfyse = tctdxjtgjfyse;
	}

	public String getSzfse() {
		return szfse;
	}

	public void setSzfse(String szfse) {
		this.szfse = szfse;
	}

	public String getBfyrshfse_death() {
		return bfyrshfse_death;
	}

	public void setBfyrshfse_death(String bfyrshfse_death) {
		this.bfyrshfse_death = bfyrshfse_death;
	}

	public String getJsshfwjse_death() {
		return jsshfwjse_death;
	}

	public void setJsshfwjse_death(String jsshfwjse_death) {
		this.jsshfwjse_death = jsshfwjse_death;
	}
}
