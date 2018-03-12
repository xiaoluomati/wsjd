package nju.software.wsjx.model.Enum;

import java.util.HashMap;

public class TshyEnum {
	public static String selectTshy(String dwmc){
		String tshy=null;
		HashMap<String,String[]> tshymap=new HashMap();//定义一个HashMap放特殊行业和特殊行业对应判别的条件
		//保险
		String[] bx={"保险"};
		tshymap.put("保险",bx);
		//财政
		String[] cz={"财政","财政局"};
		tshymap.put("财政", cz);
		//城管
		String[] cg={"城管","管理处"};
		tshymap.put("城管", cz);
		//党委
		String[] dw={"党委"};
		tshymap.put("党委", dw);
		//地矿
		String[] dk={"地热","地质"};
		tshymap.put("地矿",dk);
		//电力
		String[] dl={"电力","供电","水电站","家电站"};
		tshymap.put("电力", dl);
		//电信
		String[] dx={"电信","铁通","联通","移动","网通"};
		tshymap.put("电信", dx);
		//法院
		String[] fy={"法院"};
		tshymap.put("法院", fy);
		//工会
		String[] gh={"工会","工人文化宫"};
		tshymap.put("工会", gh);
		//工商
		String[] gs={"工商局","工商行政管理"};
		tshymap.put("工商", gs);
		//公安
		String[] ga={"公安","消防支队","消防局","消防","派出所","警察"};
		tshymap.put("公安", ga);
		//国土
		String[] gt={"国土"};
		tshymap.put("国土", gt);
		//国资
		String[] gz={"国有资产","产权交易"};
		tshymap.put("国资", gz);
		//海关
		String[] hg={"海关"};
		tshymap.put("海关", hg);
		//环保
		String[] hb={"环保","环境保护","自然保护区"};
		tshymap.put("环保", hb);
		//会计
		String[] kj={"会计","财务","出纳"};
		tshymap.put("会计", kj);
		//计生
		String[] js={"计生局","计划生育"};
		tshymap.put("计生", js);
		//技术监督
		String[] jsjd={"技术监督"};
		tshymap.put("技术监督",jsjd);
		//检察院
		String[] jcy={"检察院"};
		tshymap.put("检察院", jcy);
		//交通稽查
		String[] jtjc={"交通局"};
		tshymap.put("交通稽查", jtjc);
		//教育
		String[] jy={"教育","中学","小学","高中","大学","学院","学校","教师"};
		tshymap.put("教育", jy);
		//金融
		String[] jr={"银行","金融","基金","信用合作社"};
		tshymap.put("金融", jr);
		//劳动社保
		String[] ldsb={"人力资源和社会保障","劳动监察","劳动和社会保障","社会保障","人力资源和社会劳动保障"};
		tshymap.put("劳动社保", ldsb);
		//经贸
		String[] jm={"经贸","信用担保","贷款"};
		tshymap.put("经贸", jm);
		//林业
		String[] ly={"林业局","林业厅"};
		tshymap.put("林业", ly);
		//民航
		String[] mh={"民航","机场","航空","民用航空"};
		tshymap.put("民航", mh);
		//民政
		String[] mz={"民政","社区服务","置业","拆迁安置","革命公墓管理处","移民安置","老年人活动中心"};
		tshymap.put("民政", mz);
		//农水
		String[] ns={"农水","水利水电","水库"};
		tshymap.put("农水",ns);
		//人大
		String[] rd={"人大"};
		tshymap.put("人大", rd);
		//商检
		String[] sj={"出入境检验检疫局"};
		tshymap.put("商检", sj);
		//审计
		String[] sji={"审计"};
		tshymap.put("审计", sji);
		//税务
		String[] sw={"税务","国税","税"};
		tshymap.put("税务", sw);
		//司法行政
		String[] sfxz={"司法","公证处"};
		tshymap.put("司法行政", sfxz);
		//体育
		String[] ty={"体育"};
		tshymap.put("体育", ty);
		//铁路
		String[] tl={"铁路","列车"};
		tshymap.put("铁路", tl);
		//统计
		String[] tj={"统计"};
		tshymap.put("统计", tj);
		//外贸
		String[] wm={"国际贸易","外贸"};
		tshymap.put("外贸", tj);
		//卫生
		String[] ws={"卫生局"};
		tshymap.put("卫生", ws);
		//文化
		String[] wh={"图书","文化","环境艺术","传播广告"};
		tshymap.put("文化", wh);
		//物价
		String[] wj={"物价"};
		tshymap.put("物价", wj);
		//烟草专卖
		String[] yc={"烟草专卖"};
		tshymap.put("烟草专卖", yc);
		//药品管理
		String[] ypgl={"药品监督"};
		tshymap.put("药品管理", ypgl);
		//医院
		String[] yy={"医院","急救中心"};
		tshymap.put("医院", yy);
		//邮电
		String[] yd={"邮电","邮政局"};
		tshymap.put("邮电", yd);
		//政府
		String[] zf={"政府","居民委员会"};
		tshymap.put("政府", zf);
		//政协
		String[] zx={"政协"};
		tshymap.put("政协", zx);
		for(String key:tshymap.keySet()){
			for(String item:tshymap.get(key)){
				if(dwmc.contains(item)){
					tshy=key;
				}	
			}
		}
		return tshy;
		
		
	}
}
