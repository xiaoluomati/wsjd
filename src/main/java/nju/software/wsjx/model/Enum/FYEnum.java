package nju.software.wsjx.model.Enum;



/**
 * 法院的Enum
 * @author byron
 *
 */
public enum FYEnum {
	
	TJGY("天津市高级人民法院","市高级法院","120000 200","130.1.1.111"),
	TJYZY("天津市第一中级人民法院","第一中级法院","120100 210","130.2.0.110"),//,有问题
	TJEZY("天津市第二中级人民法院","第二中级法院","120200 220","130.3.100.36"),
	TJHS("天津海事法院","海事法院","960200 230","130.4.1.196"),
	TJHP("天津市和平区人民法院","和平区法院","120101 211","130.6.0.200"),
	TJNK("天津市南开区人民法院","南开区法院","120104 212","130.5.0.14"),
	TJHX("天津市河西区人民法院","河西区法院","120203 222","130.10.0.167"),
	TJHD("天津市河东区人民法院","河东区法院","120202 221","130.9.40.13"),
	TJHB("天津市河北区人民法院","河北区法院","120105 213","130.7.0.7"),
	TJHQ("天津市红桥区人民法院","红桥区法院","120106 214","130.8.0.73"),//有问题
	TJBH("天津滨海法院","滨海新区法院","120242 22A","130.25.1.13"),
	TJTG("天津市塘沽区人民法院","塘沽审判区","120204 223","130.15.0.21"),
	TJHG("天津市汉沽区人民法院","汉沽审判区","120205 224","130.16.0.18"),
	TJDG("天津市大港区人民法院","大港审判区","120206 225","130.17.0.12"),
	TJKFQ("天津市经济技术开发区人民法院","功能区审判区","120241 229","130.23.0.15"),
	TJDL("天津市东丽区人民法院","东丽区法院","120207 226","130.13.0.13"),
	TJJN("天津市津南区人民法院","津南区法院","120208 227","130.14.0.22"),
	TJXQ("天津市西青区人民法院","西青区法院","120107 215","130.11.1.5"),
	TJBC("天津市北辰区人民法院","北辰区法院","120108 216","130.12.0.2"),
	TJWQ("天津市武清区人民法院","武清区法院","120222 217","130.19.0.12"),
	TJBD("天津市宝坻县人民法院","宝坻区法院","120224 219","130.21.0.5"),
	TJJH("天津市静海县人民法院","静海县法院","120223 218","130.20.1.8"),
	TJNH("宁河县人民法院","宁河县法院","120221 228","130.18.0.11"),
	JX("天津市蓟县人民法院","蓟县法院","120225 21A","130.22.0.8"),
	TJTL("天津市铁路运输法院","铁路法院","920103 132","130.26.0.7"),
	HJ("天津全市法院合计","合计","1200");

	String name;
	
	String jc;
	
	String fydm;

	String fydz;
	
	private FYEnum(String name, String jc, String fydm) {
		this.name = name;
		this.jc = jc;
		this.fydm = fydm;
	}

	
	private FYEnum(String name, String jc, String fydm, String fydz) {
		this.name = name;
		this.jc = jc;
		this.fydm = fydm;
		this.fydz = fydz;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJc() {
		return jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

	public String getFydm() {
		return fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}
	
	public String getFydz() {
		return fydz;
	}

	public void setFydz(String fydz) {
		this.fydz = fydz;
	}

	
	
	public static String getFYDMByName(String name) {
		for(FYEnum fy:FYEnum.values()){
			if(fy.getName().equals(name))
				return fy.getFydm();
		}
		return null;
	}
	
	public static String getNameByFYDM(String fydm) {
		for(FYEnum fy:FYEnum.values()){
			if(fy.getFydm().equals(fydm))
				return fy.getName();
		}
		return null;
	}
}
