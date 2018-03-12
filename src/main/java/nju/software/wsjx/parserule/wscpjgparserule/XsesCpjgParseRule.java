package nju.software.wsjx.parserule.wscpjgparserule;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;
import nju.software.wsjx.service.model.xs.FdmspjfzModel;
import nju.software.wsjx.service.model.xs.FjxModel;
import nju.software.wsjx.service.model.xs.PfModel;
import nju.software.wsjx.service.model.xs.XsFjxEnum;
import nju.software.wsjx.service.model.xs.Xszx;
import nju.software.wsjx.service.model.xs.XszxEnum;
import nju.software.wsjx.service.model.xs.ZmModel;
import nju.software.wsjx.util.DateUtil;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.MoneyUtil;
import nju.software.wsjx.util.StringUtil;

/**
 * 刑事二审裁判结果解析
 * @author wangzh
 *
 */
public class XsesCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule{

	private List<String> ayList;
	
	public XsesCpjgParseRule(){
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			is = getClass().getClassLoader().getResourceAsStream("enum/xszm_dm.txt");
			isr = new InputStreamReader(is,"utf-8");
			br = new BufferedReader(isr);
			String str = "";
			ayList = new ArrayList<String>();
			while ((str = br.readLine()) != null) {
				ayList.add(str);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> cpjg = wsAnalyse.getCpjg();
		WscpjgModel cpjgModel = new WscpjgModel();
		String pjjgnr = "";
		int size = cpjg.size();
		for(int i=0;i<cpjg.size();i++){
			String jgnr = cpjg.get(i);
			pjjgnr=pjjgnr+jgnr;
			if(((StringUtil.contains(jgnr, "一、")&&StringUtil.contains(jgnr, "二、"))||(StringUtil.contains(jgnr, "一．")&&StringUtil.contains(jgnr, "二．"))) 
					&& i<size){
				String splitExpression = buildSplitExpression();
				String[] jgnrArrays = jgnr.split(splitExpression);
				if(jgnrArrays.length>1){
					for(String s:jgnrArrays){
						if(!StringUtil.isBlank(s)){
//							判断s开头的是什么
							int indexTemp = jgnr.indexOf(s);
							String xq = "";
							if(indexTemp-3>-1){
								String prefix = jgnr.substring(indexTemp-4, indexTemp);
								if(prefix.contains("维持")||prefix.contains("撤销")){
									s = prefix+s;
								}else if(i<cpjg.size()-1 && StringUtil.contains(cpjg.get(i+1), "刑期") && !StringUtil.contains(cpjg.get(i+1), "罪")){
									//不包含维持撤销，说明是本审判决，刑期可能出现在下一个
									xq = cpjg.get(i+1);
								}
							}
							cpjg.add(s);
							if(!StringUtil.isBlank(xq)){
								cpjg.add(xq);
							}
						}
					}
				}
				
			}else{
//					刑事判决结果
				if(!StringUtil.contains(jgnr, "撤销")&&!StringUtil.contains(jgnr, "维持")&&!StringUtil.contains(jgnr, "核准")){
					if((StringUtil.contains(jgnr, "被告人")||StringUtil.contains(jgnr, "上诉人"))&&StringUtil.contains(jgnr, "无罪")){
						jxWzPf(cpjgModel, jgnr);//无罪判罚刑事判决结果分组
					}else if((StringUtil.contains(jgnr, "被告人")||StringUtil.contains(jgnr, "上诉人"))&&StringUtil.contains(jgnr, "不负刑事责任")){
						jxBfxszrPf(cpjgModel, jgnr);//不负刑事责任判决结果分组
					}else{
//						String reg = "犯[\u4e00-\u9fa5]+?罪";
						String reg = "犯([\u4e00-\u9fa5]+?[、]*[\u4e00-\u9fa5]+?)*罪";
						Pattern p = Pattern.compile(reg);
						Matcher m = p.matcher(jgnr);
						if(m.find()){//刑事二审带有撤销为前审内容
							String xqnr = "";
							if(i<cpjg.size()-1){
								xqnr = cpjg.get(i+1);
							}
							setXspf(cpjgModel,jgnr,xqnr);
						}
					}
				}else if(StringUtil.contains(jgnr, "撤销")||StringUtil.contains(jgnr, "维持")){
					setFz(cpjgModel, jgnr, wssscyrModellist);
				}
			}
			if(StringUtil.contains(jgnr, "附带民事诉讼原告")){
				setFdmspjjgfz(cpjgModel, jgnr, wssscyrModellist);
			}
		}

		if(!StringUtil.isBlank(pjjgnr)){
		//	if(ajlxEnum == AjlxEnum.XSYS){
		//		setXsysjafs(cpjgModel, pjjgnr);//刑事一审结案方式
		//	}else if(ajlxEnum == AjlxEnum.XSES){
				setXsesjafs(cpjgModel, pjjgnr);//刑事二审结案方式
				if(StringUtil.equals(cpjgModel.getJafs(), "改判")){
					setGpyy(cpjgModel, wsAnalyse);//改判原因
				}
		//	}else{
		//		setXsysjafs(cpjgModel, pjjgnr);//其他刑事案件结案方式
		//	}
			
			setJtfz(cpjgModel, pjjgnr);//是否集团犯罪
		}
		return cpjgModel;
	}
	
	public void setFz(WscpjgModel cpjgModel,String pjjg,List<WssscyrModel> sscyrList){
		if(sscyrList==null){
			return;
		}
		for(WssscyrModel sscyr:sscyrList){
			String name = sscyr.getSscyr();
			if(!StringUtil.isBlank(name) && StringUtil.contains(pjjg, name)){
				XspjjgfzModel fzModel = new XspjjgfzModel(name);
				if(StringUtil.contains(pjjg, "维持")){
					fzModel.setEssljg("维持原判");
					fzModel.setEslxjg("维持原判");
				}
				if(cpjgModel.getPjjgfzModels()==null){
					List<XspjjgfzModel> fzs = new ArrayList<XspjjgfzModel>();
					fzs.add(fzModel);
					cpjgModel.setPjjgfzModels(fzs);
				}else{
					cpjgModel.getPjjgfzModels().add(fzModel);
				}
			}
		}
	}
	
	public String buildSplitExpression(){
		String expression = "";
		String[] nums = {"一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五","十六","十七","十八","十九","二十",};
		for(String num:nums){
			expression = expression+"|"+num+"、"+"维持"+"|"+num+"、"+"撤销"+"|"+num+"、"+"被告人"+"|"+num+"、"+"原审被告人"+"|"+num+"、"+"上诉人";
			expression = expression+"|"+num+"．"+"维持"+"|"+num+"．"+"撤销"+"|"+num+"．"+"被告人"+"|"+num+"．"+"原审被告人"+"|"+num+"．"+"上诉人";
		}
		if(expression.startsWith("|")){
			expression = expression.substring(1);
		}
		return expression;
	}
	
	
	public static String getMin(String[] words,String content){
		int index = -1;
		for(String s:words){
			index = content.indexOf(s);
			if(index>-1){
				return s;
			}
		}
		return null;
	}
	/**
	 * 解析刑期起止日期
	 * @param fzModel 刑事判决结果分组
	 * @param pjjg
	 * @return
	 */
	public void setXqqzrq(XspjjgfzModel fzModel,String pjjg){
		String[] qzrq = new String[2];
		String reg = "[即]?[自从][\\d一二三四五六七八九零〇○]*年[\\d一二三四五六七八九零十〇○]*月[\\d一二三四五六七八九零十〇○]*日起至[\\d一二三四五六七八九零十〇○]*年[\\d一二三四五六七八九零十〇○]*月[\\d一二三四五六七八九零十〇○]*日";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(pjjg);
		
		String reg1 = "[即]?[自从][\\d一二三四五六七八九零〇○]*年[\\d一二三四五六七八九零十〇○]*月[\\d一二三四五六七八九零十〇○]*日至[\\d一二三四五六七八九零十〇○]*年[\\d一二三四五六七八九零十〇○]*月[\\d一二三四五六七八九零十〇○]*日";
		Pattern p1 = Pattern.compile(reg1);
		Matcher m1 = p1.matcher(pjjg);
		
		String xqcontent = "";
		if(m.find()){
			xqcontent = m.group();
		}else if(m1.find()){
			xqcontent = m1.group();
		}
		
		if(!StringUtil.isBlank(xqcontent)){
			
			int middleIndex = xqcontent.indexOf("起至");
			int middleIndex1 = xqcontent.indexOf("至");
			int index = -1;
			if(middleIndex==-1){
				index = middleIndex1+1;
				middleIndex = middleIndex1;
			}else{
				index = middleIndex+2;
			}
			
			if(middleIndex>2){
				String ksrq = xqcontent.substring(2, middleIndex);
				if(!xqcontent.startsWith("即")){
					 ksrq = xqcontent.substring(1, middleIndex);
				}
				qzrq[0] = DateUtil.convertToCNDate(ksrq);
				
				fzModel.setXqksrq(qzrq[0]);
//				String jsrq = xqcontent.substring(middleIndex+2, xqcontent.length());
				String jsrq = xqcontent.substring(index, xqcontent.length());
				qzrq[1] = DateUtil.convertToCNDate(jsrq);
				fzModel.setXqjsrq(qzrq[1]);
			}
		}
//			设置刑期折抵办法
		reg = "羁押一日[，]*折抵刑期[^x00-xff]日";
		p = Pattern.compile(reg);
		m = p.matcher(pjjg);
		
		reg1 = "羁押一日[，]*抵刑期[^x00-xff]日";
		p1 = Pattern.compile(reg1);
		m1 = p1.matcher(pjjg);
		if(m.find()){
			String zdbfcontent = m.group();
			fzModel.setXqzdbf(zdbfcontent);
		}else if(m1.find()){
			String zdbfcontent = m1.group();
			fzModel.setXqzdbf(zdbfcontent);
		}
	}
	
	
	/**
	 * 刑事判决结果分组
	 * @param fzModel 此方法解析出的刑事判决结果分组
	 * @param pjjg 判决结果原始文本句
	 * @param xqnr 刑期所在段
	 */
	public void setXspf(WscpjgModel cpjgModel,String pjjg,String xqnr){
//			被告人
		String brgName = "";
		XspjjgfzModel fzModel ;
		
		int nextIndex = pjjg.indexOf("犯");
		
		brgName = pjjg.substring(0, nextIndex);//被告人姓名
		brgName = brgName.replaceAll("（原审被告人）", "");
		brgName = brgName.replaceAll("被告人", "");
		brgName = brgName.replaceAll("上诉人", "");
		if(StringUtil.contains(brgName, "、")){
			brgName = brgName.substring(brgName.indexOf("、")+1);
		}
		
		if(StringUtil.isBlank(brgName)){
			return;
		}else{
			fzModel = new XspjjgfzModel(brgName);
		}
		
		if (StringUtil.contains(pjjg, "无罪释放")||StringUtil.contains(pjjg, "免予刑事处罚")) {
			fzModel.setMzhwzsf("是");
		}else{
			fzModel.setMzhwzsf("否");
		}
		
		String reg = "犯[^x00-xff]+?罪";
//		String reg = "犯([\u4e00-\u9fa5]+?[、]*[\u4e00-\u9fa5]+?)*罪";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(pjjg);
		List<String> zmList = new ArrayList<String>();//所有罪名
		while(m.find()){
			zmList.add(m.group());
		}
		List<PfModel> dzpfList = new ArrayList<PfModel>();
		List<PfModel> yzpfList = new ArrayList<PfModel>();
		String zxPfnr = "";
//			每个罪名，带有一个单罪判罚
		for(int i = 0;i<zmList.size();i++){
			String pfContent = "";
			if(i<zmList.size()-1){
//					A罪；B罪；A罪
				if(pjjg.indexOf(zmList.get(i))<pjjg.indexOf(zmList.get(i+1))){
					pfContent =  pjjg.substring(pjjg.indexOf(zmList.get(i)), pjjg.indexOf(zmList.get(i+1)));
				}else{
					String pjjgTemp = pjjg.substring(pjjg.indexOf(zmList.get(i)));
					pfContent =  pjjgTemp.substring(pjjgTemp.indexOf(zmList.get(i)), pjjgTemp.indexOf(zmList.get(i+1)));
				}
				
			}else{
				pfContent =  pjjg.substring(pjjg.indexOf(zmList.get(i)));
			}
			
			if(zmList.size()>1 && i==zmList.size()-1 && StringUtil.contains(pfContent, "执行")){
				zxPfnr = pfContent.substring(pfContent.indexOf("执行"));
				pfContent = pfContent.substring(0, pfContent.indexOf("执行"));
			} 
			PfModel pfModel = jxPfModelFromPfnr(zmList.get(i), pfContent);
			if(pfModel!=null ){
				dzpfList.add(pfModel);
			}
		}
//			判罚
		if(dzpfList.size()>0){
			fzModel.setDzpf(dzpfList);
//				执行判罚
			if(!StringUtil.isBlank(zxPfnr)){
				PfModel zxpf = jxPfModelFromPfnr(zmList.get(0), zxPfnr);
				fzModel.setZxpf(zxpf);
			}else if (dzpfList.size()==1){
				fzModel.setZxpf(dzpfList.get(0));
			}
			if(dzpfList.get(0)!=null && dzpfList.get(0).getZm()!=null){
				fzModel.setPjzzm(dzpfList.get(0).getZm());//判决主罪名
			}
		}
		if(yzpfList.size()>0){
			fzModel.setYzpf(yzpfList);
		}
		
//			合并量刑
		if(dzpfList.size()>1){
			fzModel.setHblx("是");
			fzModel.setSzbf("是");
		}else{
			fzModel.setHblx("否");
			fzModel.setSzbf("否");
		}
		
//			刑期，如果刑期内容是下一个被告人的判罚，则判断刑期是不是在这个判决结果里
		if(StringUtil.contains(pjjg, "刑期")&&StringUtil.contains(pjjg, "起至")){
			setXqqzrq(fzModel, pjjg);
		}else if(StringUtil.contains(xqnr, "刑期")&&StringUtil.contains(xqnr, "起")&&!StringUtil.contains(xqnr, "被告人")){
			setXqqzrq(fzModel, xqnr);
		}

		if(cpjgModel.getPjjgfzModels()==null){
			List<XspjjgfzModel> xspjjgfzModels = new ArrayList<XspjjgfzModel>();
			xspjjgfzModels.add(fzModel);
			cpjgModel.setPjjgfzModels(xspjjgfzModels);
		}else{
//			已有分组
			List<XspjjgfzModel> xspjjgfzModels = cpjgModel.getPjjgfzModels();
//			for(XspjjgfzModel fz:xspjjgfzModels){
//				if(StringUtil.equals(fz.getSscyr(), fzModel.getSscyr()) && (fz.getEslxjg()!=null ||fz.getEssljg()!=null)){
//					xspjjgfzModels.remove(fz);
//				}
//			}
			for(Iterator it = xspjjgfzModels.iterator();it.hasNext();){
				XspjjgfzModel fz = (XspjjgfzModel) it.next();
				if(StringUtil.equals(fz.getSscyr(), fzModel.getSscyr()) && (fz.getEslxjg()!=null ||fz.getEssljg()!=null)){
					it.remove();
				}
			}
			xspjjgfzModels.add(fzModel);
//			cpjgModel.setFdmspjfzModel(fdmspjfzModel);
			cpjgModel.setPjjgfzModels(xspjjgfzModels);
//			cpjgModel.getPjjgfzModels().add(fzModel);
		}
	}
	
	public int getBgrIndex(String pjjgnr){
		int index1 = pjjgnr.indexOf("（原审被告人）");
		if(index1<0){
			index1 = pjjgnr.indexOf("上诉人");
			if(index1<0){
				index1 =pjjgnr.indexOf("被告人");
				if(index1>-1){
					index1 = index1+3;
				}
			}else{
				index1 = index1+3;
			}
		}else{
			index1 = index1+"（原审被告人）".length();
		}
		if(index1==-1){
			index1=0;
		}
		return index1;
	}
	
	/**
	 * 解析单罪判罚内容
	 * @param zm
	 * @param pfContent
	 * @return
	 */
	public PfModel jxPfModelFromPfnr(String zm,String pfContent){
		if(StringUtil.isBlank(pfContent)){
			return null;
		}
		PfModel pfModel = new PfModel(pfContent);
//			罪名
		String zmName = zm.substring(1);
		if(zmName.endsWith("，")){
			zmName = zmName.substring(0, zmName.length()-1);
		}
		ZmModel zmModel = new ZmModel(zmName);

		/*
		 * 后续增加解析罪名代码、完整罪名
		 */
		setZm(zmModel, zmName);
		
		pfModel.setZm(zmModel);

		if(StringUtil.contains(pfContent, "免予刑事处罚")){
			pfModel.setPjjglx("免予刑事处罚");
		}else{
			pfModel.setPjjglx("给予刑事处罚");
		}
		
		String[] pfArrays = pfContent.split("，|,|\\.|。|、|;|；|（|）");
		List<FjxModel> fjxList = new ArrayList<FjxModel>();
		boolean zx = true;
		boolean hxflag = true;
		boolean fjflag = true;
		for(String pf:pfArrays){
			if(StringUtil.contains(pf, "合并")){
				break;
			}else if(XszxEnum.getXszx(pf)!=null && zx){
				String zxName = XszxEnum.getXszx(pf).getXz();
				Xszx xzModel = new Xszx(zxName);
				if(StringUtil.contains(pf, "缓刑")){
					xzModel.setZxxq(pf.substring(pf.indexOf(zxName)+zxName.length(),pf.indexOf("缓刑")));
				}else{
					xzModel.setZxxq(pf.substring(pf.indexOf(zxName)+zxName.length()));
				}
				if(StringUtil.equals(zxName, "死刑")&&StringUtil.contains(pfContent, "缓期")){
					xzModel.setZxlb(XszxEnum.SH.getXz());
				}
				pfModel.setZx(xzModel);
				zx = false;
			}
			if((StringUtil.contains(pf, "缓刑") ||StringUtil.contains(pf, "缓期"))&& hxflag){
				Xszx hx =  new Xszx();
				if(StringUtil.contains(pf, "战时")){
					hx.setZxlb("战时缓刑");
				}else{
					hx.setZxlb("普通缓刑");
				}
				String hxxq = "";
				if(StringUtil.contains(pf, "缓刑")){
					hxxq = pf.substring(pf.indexOf("缓刑")+2);
				}else if(StringUtil.contains(pf, "缓期") && pf.indexOf("执行")>pf.indexOf("缓期")+2){
					hxxq= pf.substring(pf.indexOf("缓期")+2,pf.indexOf("执行"));
				}else if(StringUtil.contains(pf, "缓期")){
					hxxq= pf.substring(pf.indexOf("缓期")+2);
				}
				hx.setZxxq(hxxq);
				if(!hxxq.contains("考验期限")){
					pfModel.setHx(hx);
					hxflag = false;
				}
			}
			if(XsFjxEnum.getFjx(pf)!=null){
				FjxModel fjx = new FjxModel(XsFjxEnum.getFjx(pf),pf);
				if(XsFjxEnum.getFjx(pf)==XsFjxEnum.FJ && fjflag){
					fjxList.add(fjx);
					fjflag = false;
				}
			}
		}
		if(fjxList.size()>0){
			pfModel.setFjxList(fjxList);
		}
//			原罪判罚-与前罪并罚
		if(StringUtil.contains(pfContent, "前罪并罚")||StringUtil.contains(pfContent, "原罪并罚")){
			pfModel.setYzszbf("是");
		}else if(StringUtil.contains(pfContent, "前罪")||StringUtil.contains(pfContent, "已判决")||StringUtil.contains(pfContent, "判决书")){
			pfModel.setYzszbf("否");
		}
		return pfModel;
	}
	 
	public void setZm(ZmModel zmModel,String zm){
		String wzzm = "";
		String zmdm = "";
		if(zm.endsWith("罪")){
			zm = zm.substring(0, zm.length()-1);
		}
		List<String> zmList = FcUtil.getWholeToken(zm);
		for (int i = 0; i < ayList.size(); i++) {
			String ay = ayList.get(i).substring(4, ayList.get(i).length());
			boolean flag = true;
			for(String s :zmList){
				if(!StringUtil.contains(ay, s)){
					flag = false;
				}
			}
			if (flag) {
				wzzm = ay+ "罪";
				zmdm = ayList.get(i).substring(0, 4).trim();
				zmModel.setWzzm(wzzm);
				zmModel.setZmdm(zmdm);
				break;
			}
		}
	}
	/**
	 * 宣告无罪：刑事判决结果分组
	 * @param cpjgModel
	 * @param jgnr
	 */
	public void jxWzPf(WscpjgModel cpjgModel,String jgnr){
		String bgrName = "";
		if(jgnr.indexOf("被告人")<jgnr.indexOf("无罪")){
			bgrName = jgnr.substring(jgnr.indexOf("被告人")+3, jgnr.indexOf("无罪"));
		}
		if(!StringUtil.isBlank(bgrName)){
			XspjjgfzModel fzModel = new XspjjgfzModel(bgrName);
			PfModel dzpfModel = new PfModel(jgnr.substring(jgnr.indexOf(bgrName)+bgrName.length()));
			dzpfModel.setPjjglx("宣告无罪");
			List<PfModel> dzpfList = new ArrayList<PfModel>();
			dzpfList.add(dzpfModel);
			fzModel.setDzpf(dzpfList);
			fzModel.setZxpf(dzpfModel);
			fzModel.setHblx("否");
			fzModel.setSzbf("否");
			if (StringUtil.contains(jgnr, "无罪释放")||StringUtil.contains(jgnr, "免予刑事处罚")) {
				fzModel.setMzhwzsf("是");
			}else{
				fzModel.setMzhwzsf("否");
			}
		}
	}
	
	/**
	 * 不负刑事责任 刑事判决结果分组
	 * @param cpjgModel
	 * @param jgnr
	 */
	public void jxBfxszrPf(WscpjgModel cpjgModel,String jgnr){
		String bgrName = "";
		if(jgnr.indexOf("被告人")<jgnr.indexOf("不负刑事责任")){
			bgrName = jgnr.substring(jgnr.indexOf("被告人")+3, jgnr.indexOf("不负刑事责任"));
		}
		if(!StringUtil.isBlank(bgrName)){
			XspjjgfzModel fzModel = new XspjjgfzModel(bgrName);
			PfModel dzpfModel = new PfModel(jgnr.substring(jgnr.indexOf(bgrName)+bgrName.length()));
			dzpfModel.setPjjglx("不负刑事责任");
			List<PfModel> dzpfList = new ArrayList<PfModel>();
			dzpfList.add(dzpfModel);
			fzModel.setDzpf(dzpfList);
			fzModel.setZxpf(dzpfModel);
			fzModel.setHblx("否");
			fzModel.setSzbf("否");
			if (StringUtil.contains(jgnr, "无罪释放")||StringUtil.contains(jgnr, "免予刑事处罚")) {
				fzModel.setMzhwzsf("是");
			}else{
				fzModel.setMzhwzsf("否");
			}
		}
	}
	
	/**
	 * 刑事二审结案方式
	 * @param cpjgModel
	 * @param jgnr
	 */
	public void setXsesjafs(WscpjgModel cpjgModel,String jgnr){
//			发回重审
		String zycsReg = "发回[\u4e00-\u9fa5]*法院重[新]?审";
		Pattern zycsPattern = Pattern.compile(zycsReg);
		Matcher zycsMatcher = zycsPattern.matcher(jgnr);
		if(jgnr.contains("按")&&(jgnr.contains("撤诉")||jgnr.contains("撤回上诉"))&&jgnr.contains("处理")){
			cpjgModel.setJafs("按撤回上诉处理");
		}else if(jgnr.contains("按")&&jgnr.contains("撤回抗诉")&&jgnr.contains("处理")){
			cpjgModel.setJafs("按撤回抗诉处理");
		}else if((!jgnr.contains("撤销")&&!jgnr.contains("变更")&&jgnr.contains("维持"))||jgnr.contains("维持原判")){
			cpjgModel.setJafs("维持");
		}else if(StringUtil.contains(jgnr, "中止审理")||StringUtil.contains(jgnr, "终止审理")){
			cpjgModel.setJafs("终止审理");
		}else if(StringUtil.contains(jgnr, "调解")){
			cpjgModel.setJafs("调解");
		}else if(zycsMatcher.find()){
			cpjgModel.setJafs("发回重审");
		}else if((jgnr.contains("维持")&&(jgnr.contains("撤销")||jgnr.contains("变更")))// 1.维持XX，撤销XX或者维持XX，变更XX
				 ||(jgnr.contains("撤销")&&jgnr.contains("判决")&&jgnr.contains("判处"))
				 ||jgnr.contains("、撤销")){// 2.撤销XX判决,犯XX罪，判处XX
			 cpjgModel.setJafs("改判");
		 }else if(StringUtil.contains(jgnr, "撤销原裁定")&&StringUtil.contains(jgnr, "驳回没收违法所得申请")){
			cpjgModel.setJafs("撤销原裁定并驳回没收违法所得申请");
		}else if(StringUtil.contains(jgnr, "撤销原裁定")&&StringUtil.contains(jgnr, "没收违法所得")){
			cpjgModel.setJafs("撤销原裁定并裁定没收违法所得");
		}else if(StringUtil.contains(jgnr, "变更没收违法所得裁定")){
			cpjgModel.setJafs("变更没收违法所得裁定");
		}else if((jgnr.contains("准予")||jgnr.contains("准许")) && jgnr.contains("撤回") &&(jgnr.contains("上诉")||jgnr.contains("起诉"))){
			cpjgModel.setJafs("准予撤回上诉");
		}else if((jgnr.contains("准予")||jgnr.contains("准许")) && jgnr.contains("撤回") &&jgnr.contains("抗诉")){
			cpjgModel.setJafs("准予撤回抗诉");
		}else if((jgnr.contains("准予")||jgnr.contains("准许")) && jgnr.contains("撤回") &&jgnr.contains("自诉")&&jgnr.contains("撤销一审裁判")){
			cpjgModel.setJafs("准许撤回自诉并撤销一审裁判");
		}else if(jgnr.contains("撤销")&&jgnr.contains("裁定")&&jgnr.contains("受理")){
			cpjgModel.setJafs("撤销原裁定并指令受理");
		 }else if(jgnr.contains("撤销")&&jgnr.contains("裁定")&&jgnr.contains("审理")){
			 cpjgModel.setJafs("撤销原裁定并指令审理");
		 }else if(jgnr.contains("与刑事部分裁判一并进入再审")){
			 cpjgModel.setJafs("与刑事部分裁判一并进入再审");
		 }else{
			 cpjgModel.setJafs("其他");
		 }
	}
	
	public void setGpyy(WscpjgModel cpjgModel,WsAnalyse wsAnalyse){
		List<String> cpfxgcList = wsAnalyse.getCpfxgc();
		if(cpfxgcList!=null && cpfxgcList.size()>0){
			String cpfxgc = "";
			List<String> gpyy = new ArrayList<String>();
			for(String s:cpfxgcList){
				cpfxgc +=s;
			}
			
			if(StringUtil.contains(cpfxgc, "事实不清")||StringUtil.contains(cpfxgc, "证据不足")){
				gpyy.add("事实不清或者证据不足");
			}
			if(rdssbd(cpfxgc)){
				gpyy.add("认定事实错误");
			}
			if(StringUtil.contains(cpfxgc, "重大立功表现")){
				gpyy.add("被告人有重大立功表现");
			}
			if(StringUtil.contains(cpfxgc, "新证据")){
				gpyy.add("出现了新证据");
			}
			if(StringUtil.contains(cpfxgc, "适用法律错误")||StringUtil.contains(cpfxgc, "适用法律有错误")||StringUtil.contains(cpfxgc, "适用法律不当")
					||StringUtil.contains(cpfxgc, "适用法律及量刑不当")){
				gpyy.add("适用法律错误");
			}
			if(StringUtil.contains(cpfxgc, "量刑过轻")||StringUtil.contains(cpfxgc, "量刑偏轻")||StringUtil.contains(cpfxgc, "量刑畸轻")){
				gpyy.add("量刑过轻");
			}
			if(StringUtil.contains(cpfxgc, "量刑过重")||StringUtil.contains(cpfxgc, "量刑偏重")){
				gpyy.add("量刑偏重");
			}
			if(StringUtil.contains(cpfxgc, "量刑不当")){
				gpyy.add("量刑平衡");
			}
			if(StringUtil.contains(cpfxgc, "怀孕")){
				gpyy.add("审判（复核）时怀孕");
			}
			if(gpyy.size()==0){
				gpyy.add("其他");
			}
			if(gpyy.size()>0){
				cpjgModel.setGpyy(gpyy);
			}
		}
	}
	
	/**
	 * 改判原因 认定事实错误
	 * @param cpfxgc
	 * @return
	 */
	public boolean rdssbd(String cpfxgc){
		String[] cpfxgcArrays = cpfxgc.split("，|。|；|（|）");
		for(String s :cpfxgcArrays){
			if(StringUtil.contains(s, "认定")&&StringUtil.contains(s, "事实")
					&&(StringUtil.contains(s, "错误")||StringUtil.contains(s, "不足")||StringUtil.contains(s, "有误"))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 解析是否提出管辖权异议
	 * @param wscpjgModel
	 */
	public void setTcgxyy(WscpjgModel cpjgModel,String allNr){
		if(StringUtil.contains(allNr, "管辖异议")){
			cpjgModel.setTcgxyy("是");
		}else{
			cpjgModel.setTcgxyy("否");
		}
	}
	
	public void setFdmspjjgfz(WscpjgModel cpjgModel,String pjjg,List<WssscyrModel> wssscyrModellist){
		List<String> pcr = new ArrayList<String>();
		List<String> bpcr = new ArrayList<String>();
		int indexOfms = pjjg.indexOf("附带民事诉讼原告人");
		for(WssscyrModel sscyr:wssscyrModellist){
			String sscyrName = sscyr.getSscyr();
			if(pjjg.indexOf(sscyrName)>indexOfms){
				bpcr.add(sscyrName);
			}else if(pjjg.indexOf(sscyrName)>indexOfms && pjjg.indexOf(sscyrName)>-1){
				pcr.add(sscyrName);
			}
		}
		List<String> moneyList = MoneyUtil.getJe(pjjg);
		if(pcr.size()>0 || bpcr.size()>0 || moneyList.size()>0){
			FdmspjfzModel fzModel = new FdmspjfzModel();
			if(pcr.size()>0){
				fzModel.setPcr(pcr);
			}
			if(bpcr.size()>0){
				fzModel.setBpcr(bpcr);
			}
			if(moneyList.size()>0){
				fzModel.setBcje(moneyList);
			}
			fzModel.setNr(pjjg);
			cpjgModel.setFdmspjfzModel(fzModel);
		}
	}
	/**
	 * 对附带民事赔偿的处理
	 * @param cpjgModel
	 * @param pjjg
	 */
	public void setDfdmspcdcl(WscpjgModel cpjgModel,String pjjg){
		//驳回附带民事诉讼原告人  不赔
		if(StringUtil.contains(pjjg, "驳回附带民事诉讼原告人")&&(!StringUtil.contains(pjjg, "其他诉讼请求")||!StringUtil.contains(pjjg, "其余诉讼请求"))){
			cpjgModel.setDfdmspccl("不赔");
		}else if(StringUtil.contains(pjjg, "附带民事诉讼原告人")&&StringUtil.contains(pjjg, "赔")){
			//附带民事诉讼原告人人
			cpjgModel.setDfdmspccl("赔偿");
		}
	}
	
	public void setFdmsbfcpjg(WscpjgModel cpjgModel,String pjjg){
		if(StringUtil.contains(cpjgModel.getJafs(), "撤诉") && pjjg.contains("附带民事诉讼原告人")){
			cpjgModel.setFdmscpjg("附带民事诉讼原告撤诉");
		}else if(StringUtil.contains(pjjg, "附带民事诉讼原告")){
			cpjgModel.setFdmscpjg("判决");
		}
	}

	/**
	 * 设置集团犯罪
	 * @param cpjgModel
	 * @param pjjg
	 */
	public void setJtfz(WscpjgModel cpjgModel,String pjjg){
		if(StringUtil.contains(pjjg, "集团犯罪")){
			cpjgModel.setJtfz("是");
		}else{
			cpjgModel.setJtfz("否");
		}
	}

}
