package nju.software.wsjx.model.Enum;

public enum SsfEnum {
	SLF("受理费"),
	SSFY("诉讼费用"),
	SSF("诉讼费"),
	CCBQF("财产保全申请费"),
	BQF("保全费"),
	GGF("公告费"),
	JDF("鉴定费"),
	YDF("邮递费"),
	YDSDF("邮寄送达费"),
	QTFY("其他费用"),
	QTF("其他"),
	JBSQ("减半收取"),
	JBJN("减半交纳");
	private String ssfName;//诉讼费种类

	private SsfEnum() {
	}

	private SsfEnum(String ssfName) {
		this.ssfName = ssfName;
	}

	public String getSsfName() {
		return ssfName;
	}

	public void setSsfName(String ssfName) {
		this.ssfName = ssfName;
	}
	

}
