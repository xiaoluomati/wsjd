package nju.software.wsjx.parserule.wscpjgparserule;

import java.util.ArrayList;
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
import nju.software.wsjx.util.MoneyUtil;
import nju.software.wsjx.util.StringUtil;

/**
 * 刑事一审裁判结果解析
 * @author wangzh
 *
 */
public class XsysCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule{

	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> cpjg = wsAnalyse.getCpjg();
		WscpjgModel cpjgModel = new WscpjgModel();
		String pjjgnr = "";
		for(int i=0;i<cpjg.size();i++){
			String jgnr = cpjg.get(i);
			pjjgnr=pjjgnr+jgnr;
//			如果刑事结果没有分组，先分组
			if(StringUtil.contains(jgnr, "一、")&&StringUtil.contains(jgnr, "二、")){
				String[] jgnrArrays = jgnr.split("一、|二、|三、|四、|五、|六、|七、|八、|九、");
				for(String s:jgnrArrays){
					if(!StringUtil.isBlank(s)){
						cpjg.add(s);
					}
				}
			}else{
//				刑事结果已经分组
//				刑事判决结果
				if(StringUtil.contains(jgnr, "被告人")&&StringUtil.contains(jgnr, "无罪")){
					jxWzPf(cpjgModel, jgnr);//无罪判罚刑事判决结果分组
				}else if(StringUtil.contains(jgnr, "被告人")&&StringUtil.contains(jgnr, "不负刑事责任")){
					jxBfxszrPf(cpjgModel, jgnr);//不负刑事责任判决结果分组
				}else{
//					给予刑事处罚、免予刑事处罚刑事判决结果分组
					String reg = "犯[^x00-xff]+?罪";
//					String reg = "犯([\u4e00-\u9fa5]+?[、]*[\u4e00-\u9fa5]+?)*罪";
					Pattern p = Pattern.compile(reg);
					Matcher m = p.matcher(jgnr);
					if(m.find()){
						String xqnr = "";
						if(i<cpjg.size()-1){
							xqnr = cpjg.get(i+1);
						}
						setXspf(cpjgModel,jgnr,xqnr);
					}
				}
			}
			if(StringUtil.contains(jgnr, "附带民事诉讼原告")){
				setFdmspjjgfz(cpjgModel, jgnr, wssscyrModellist);
			}
		}

		if(!StringUtil.isBlank(pjjgnr)){
			setXsysjafs(cpjgModel, pjjgnr);//刑事一审结案方式
			setYsxsbfcpjg(cpjgModel, pjjgnr, wsAnalyse);//一审刑事部分裁判结果
			setTcgxyy(cpjgModel, pjjgnr);//提出管辖异议
			if(pjjgnr.indexOf("如不服")>-1){//上诉情况
				setSsqk(cpjgModel, pjjgnr);
			}
			setDfdmspcdcl(cpjgModel, pjjgnr);//对附带民事赔偿的处理
			setFdmsbfcpjg(cpjgModel, pjjgnr);//附带民事部分裁判结果
		}
		return cpjgModel;
	}
	
	/**
	 * 刑事一审上诉情况
	 * @param cpjgModel
	 * @param temp
	 */
	public static void setSsqk(WscpjgModel cpjgModel,String temp){
		String cpjg = temp.substring(temp.indexOf("如不服"));
//		上诉期限
		 int indexStart = cpjg.indexOf("日起");
		 if(indexStart==-1){
			 indexStart = cpjg.indexOf("天起");
		 }
	     int fyIndex =-1; 
	     if(indexStart>-1){
	    	 fyIndex = cpjg.indexOf("内");
	    	 if(fyIndex>-1 && fyIndex > indexStart){
	    		 cpjgModel.setSsqx(cpjg.substring(indexStart+2, fyIndex));
		     }
	     }
//	     上诉法院
	     String[] fyPrefix = {"直接向","或者向","通过本院向"};
	     String fyprefix = getMin(fyPrefix, cpjg);
	     if(!StringUtil.isBlank(fyprefix)){
	    	 indexStart = cpjg.indexOf(fyprefix);
	    	 String[] ssSuffix ={"提起上诉","提出上诉","上诉"};
	    	 String sssuffix = getMin(ssSuffix, cpjg);
		     if(indexStart>-1 && !StringUtil.isBlank(sssuffix) && indexStart<cpjg.indexOf(sssuffix)){
		    	 cpjgModel.setKssz(cpjg.substring(indexStart+fyprefix.length(), cpjg.indexOf(sssuffix)));
		     }
	     }else{
	    	 indexStart = cpjg.indexOf("直接上诉");
	    	 fyIndex = cpjg.indexOf("法院");
	    	 if(indexStart>-1 && fyIndex>-1 && indexStart<fyIndex){
	    		 cpjgModel.setKssz(cpjg.substring(indexStart+5, fyIndex+2));
	    	 }
	     }
//	     提交材料
	     String[] tjPrefix = {"提交","应交","应当交","应递交","应当递交","应当提交"};
	     String tjprefix = getMin(tjPrefix, cpjg);
	     if(!StringUtil.isBlank(tjprefix)){
	    	 indexStart = cpjg.indexOf(tjprefix);
	    	 String tjcl = cpjg.substring(indexStart+tjprefix.length());
	    	 if(tjcl.endsWith("。")){
	    		 tjcl = tjcl.substring(0, tjcl.length()-1);
	    	 }
	    	 cpjgModel.setSstjcl(tjcl);
	     }
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
	 * 刑事一审撤诉人集合
	 * @param cpjgModel
	 * @param wssscyrModellist
	 * @param cpjg
	 * @param wsAnalyse
	 */
	public static void setCsrjh(WscpjgModel cpjgModel,List<WssscyrModel> wssscyrModellist,String cpjg,WsAnalyse wsAnalyse){
		List<String> csrjh = new ArrayList<String>();
		String csrStr = "";
		for(WssscyrModel sscyr:wssscyrModellist){
			if(StringUtil.contains(cpjg, sscyr.getSscyr())){
				csrjh.add(sscyr.getSscyr());
				csrStr += sscyr.getSscyr();
			}
		}
		if(csrjh.size()>0){
			cpjgModel.setCsrjh(csrjh);
//			自诉人与被告人自行和解后撤诉 出现在诉讼记录段
			String ssjl = wsAnalyse.getSsjl();
//			达成和解 ；自愿和解； 达成协议；达成和解协议；
			if((StringUtil.contains(ssjl, "和解")||StringUtil.contains(ssjl, "达成"))&&StringUtil.contains(ssjl, "撤诉")){
				cpjgModel.setCslx("自诉人与被告人自行和解后撤诉");
				return;
			}
			if(StringUtil.contains(csrStr, "检察院")){
				cpjgModel.setCslx("检察院撤诉");
			}else{
				cpjgModel.setCslx("自诉人撤诉");
			}
		}
	}
	
	/**
	 * 解析刑期起止日期
	 * @param fzModel 刑事判决结果分组
	 * @param pjjg
	 * @return
	 */
	public void setXqqzrq(XspjjgfzModel fzModel,String pjjg){
		String[] qzrq = new String[2];
		String reg = "即自[\\d一二三四五六七八九零〇]*年[\\d一二三四五六七八九零〇]*月[\\d一二三四五六七八九零〇]*日起至[\\d一二三四五六七八九零〇]*年[\\d一二三四五六七八九零〇]*月[\\d一二三四五六七八九零〇]*日止";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(pjjg);
		if(m.find()){
			String xqcontent = m.group();
			int middleIndex = xqcontent.indexOf("起至");
			if(middleIndex>2){
				String ksrq = xqcontent.substring(2, middleIndex);
				qzrq[0] = ksrq;
				fzModel.setXqksrq(ksrq);
				String jsrq = xqcontent.substring(middleIndex+2, xqcontent.length()-1);
				qzrq[1] = jsrq;
				fzModel.setXqjsrq(jsrq);
			}
		}
//		设置刑期折抵办法
		reg = "羁押一日折抵刑期[^x00-xff]日";
		p = Pattern.compile(reg);
		m = p.matcher(pjjg);
		if(m.find()){
			String zdbfcontent = m.group();
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
//		被告人
		String brgName = "";
		XspjjgfzModel fzModel ;
		int firstIndex = pjjg.indexOf("被告人");
		int nextIndex = pjjg.indexOf("犯");
		if(firstIndex>-1 && firstIndex<nextIndex){
			brgName = pjjg.substring(firstIndex+3, nextIndex);//被告人姓名
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
//		每个罪名，带有一个单罪判罚
		for(int i = 0;i<zmList.size();i++){
			String pfContent = "";
			if(i<zmList.size()-1){
//				A罪；B罪；A罪
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
//		判罚
		if(dzpfList.size()>0){
			fzModel.setDzpf(dzpfList);
//			执行判罚
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
		
//		合并量刑
		if(dzpfList.size()>1){
			fzModel.setHblx("是");
			fzModel.setSzbf("是");
		}else{
			fzModel.setHblx("否");
			fzModel.setSzbf("否");
		}
		
//		刑期，如果刑期内容是下一个被告人的判罚，则判断刑期是不是在这个判决结果里
		if(StringUtil.contains(pjjg, "刑期")&&StringUtil.contains(pjjg, "起至")){
			setXqqzrq(fzModel, pjjg);
		}else if(StringUtil.contains(xqnr, "刑期")&&StringUtil.contains(xqnr, "起至")&&!StringUtil.contains(xqnr, "被告人")){
			setXqqzrq(fzModel, xqnr);
		}

		if(cpjgModel.getPjjgfzModels()==null){
			List<XspjjgfzModel> xspjjgfzModels = new ArrayList<XspjjgfzModel>();
			xspjjgfzModels.add(fzModel);
			cpjgModel.setPjjgfzModels(xspjjgfzModels);
		}else{
			cpjgModel.getPjjgfzModels().add(fzModel);
		}
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
//		罪名
		String zmName = zm.substring(1);
		if(zmName.endsWith("，")){
			zmName = zmName.substring(0, zmName.length()-1);
		}
		ZmModel zmModel = new ZmModel(zmName);
		/*
		 * 后续增加解析罪名代码、完整罪名
		 */
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
				pfModel.setZx(xzModel);
				zx = false;
			}
			if(StringUtil.contains(pf, "缓刑") && hxflag){
				Xszx hx =  new Xszx();
				if(StringUtil.contains(pf, "战时")){
					hx.setZxlb("战时缓刑");
				}else{
					hx.setZxlb("普通缓刑");
				}
				hx.setZxxq(pf.substring(pf.indexOf("缓刑")+2));
				pfModel.setHx(hx);
				hxflag = false;
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
//		原罪判罚-与前罪并罚
		if(StringUtil.contains(pfContent, "前罪并罚")||StringUtil.contains(pfContent, "原罪并罚")){
			pfModel.setYzszbf("是");
		}else if(StringUtil.contains(pfContent, "前罪")||StringUtil.contains(pfContent, "已判决")||StringUtil.contains(pfContent, "判决书")){
			pfModel.setYzszbf("否");
		}
		return pfModel;
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
	 * 刑事一审结案方式
	 * @param cpjgModel
	 * @param jgnr
	 */
	public void setXsysjafs(WscpjgModel cpjgModel,String jgnr){
//		驳回起诉
		String bhqsReg = "驳回[\u4e00-\u9fa5]*对[\u4e00-\u9fa5]*的[\u4e00-\u9fa5]*起诉";
		Pattern bhqsPattern = Pattern.compile(bhqsReg);
		Matcher bhqsMatcher = bhqsPattern.matcher(jgnr);
//		准允撤诉
		String zycsReg = "准[允许][\u4e00-\u9fa5]*撤诉";
		Pattern zycsPattern = Pattern.compile(zycsReg);
		Matcher zycsMatcher = bhqsPattern.matcher(jgnr);
		String reg = "犯([\u4e00-\u9fa5]+?[、]*[\u4e00-\u9fa5]+?)*罪";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(jgnr);
		
		if(StringUtil.contains(jgnr,"按" )&&StringUtil.contains(jgnr, "撤诉处理")){
			cpjgModel.setJafs("按撤诉处理");
		}else if(StringUtil.contains(jgnr, "不予受理")){
			cpjgModel.setJafs("不予受理");
		}else if(StringUtil.contains(jgnr, "不予立案")){
			cpjgModel.setJafs("不予立案");
		}else if(StringUtil.contains(jgnr, "不予登记立案")){
			cpjgModel.setJafs("不予登记立案");
		}else if(bhqsMatcher.find()){
			cpjgModel.setJafs("驳回起诉");
		}else if(zycsMatcher.find()){
			cpjgModel.setJafs("准允撤诉");
		}else if(StringUtil.contains(jgnr, "中止审理")||StringUtil.contains(jgnr, "终止审理")){
			cpjgModel.setJafs("终止审理");
		}else if(StringUtil.contains(jgnr, "调解")){
			cpjgModel.setJafs("调解");
		}else if(m.find()){
			cpjgModel.setJafs("判决");
		}
	}
	/**
	 * 一审刑事部分裁判裁判结果
	 * @param wscpjgModel
	 * @param allNr
	 * @param wsAnalyse
	 */
	public void setYsxsbfcpjg(WscpjgModel cpjgModel,String allNr,WsAnalyse wsAnalyse){
		String cpfxgc="";
		if(wsAnalyse.getCpfxgc()!=null && wsAnalyse.getCpfxgc().size()>0){
			for(String cpfx:wsAnalyse.getCpfxgc()){
				cpfxgc = cpfxgc+cpfx;
			}
		}
		String ssjl = wsAnalyse.getSsjl();
		
		String reg = "准[予许][\u4e00-\u9fa5]*检察院撤回[^x00-xff]*起诉";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(allNr);
		
		if(cpjgModel.getJafs()!=null && cpjgModel.getJafs().contains("撤诉") && 
				(ssjl.contains("和解")||cpfxgc.contains("和解"))){
			cpjgModel.setYsxsbfpjjg("自诉人与被告人自行和解后撤诉");
		}else if(m.find()){
			cpjgModel.setYsxsbfpjjg("检察院撤诉");
		}else if(StringUtil.equals("驳回起诉", cpjgModel.getJafs())){
			cpjgModel.setYsxsbfpjjg("驳回起诉");
		}else if(StringUtil.equals("终止审理", cpjgModel.getJafs())){
			cpjgModel.setYsxsbfpjjg("终止");
		}else if(StringUtil.equals("判决", cpjgModel.getJafs())){
			cpjgModel.setYsxsbfpjjg("判决");
		}else {
			cpjgModel.setYsxsbfpjjg("无");
		}
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

}
