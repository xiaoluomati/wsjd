package nju.software.wsjx.parserule.wssscyrparserule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.Enum.MZEnum;
import nju.software.wsjx.model.Enum.WhcdEnum;
import nju.software.wsjx.model.Enum.ZWEnum;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.StringUtil;

public class MszsSscyrParseRule extends GeneralSscyrCommonRule implements SscyrParseRule{
	public List<WssscyrModel> jxWssscyrModelList(WsAnalyse wsAnalyse) {
		List<String> sscyr = wsAnalyse.getSscyr();//得到了诉讼参与人的所有内容·········每个String是一段
		String ssjl = wsAnalyse.getSsjl();
		List<WssscyrModel> wssscyrModellist = new ArrayList<WssscyrModel>();
		String dt = "";
		String wdt = "";
		if (ssjl != null) {
			ArrayList<String> ssjlfd = WsAnalyse.getWholeContent(ssjl);
			for (int i = 0; i < ssjlfd.size(); i++) {
				if (ssjlfd.get(i).contains("未到庭")
						|| ssjlfd.get(i).contains("没有到庭")) {
					wdt = ssjlfd.get(i);
					break;
				} else if (ssjlfd.get(i).contains("到庭")) {
					dt = ssjlfd.get(i);
					break;
				}
			}
		}
		
		for (int i = 0; i < sscyr.size(); i++) {
			WssscyrModel wssscyrModel = new WssscyrModel();
			ArrayList<String> contentlist = WsAnalyse.getWholeContent(sscyr
					.get(i));
			String sscyrallinfo="";
			for(String sscyrinfo:contentlist){
				sscyrallinfo+=sscyrinfo;
			}
			wssscyrModel.setSscyrallinfo(sscyrallinfo);
			// 解析诉讼参与人名、身份与类型
			//String content = WsAnalyse.deBracket(contentlist.get(0));
			String content=contentlist.get(0);
			
			String ysssdw=null;
			String esssdw=null;
			
			ysssdw=WsAnalyse.takeBracket(content);//原审诉讼地位(取的是括号里的值)
			if(ysssdw!=null){
				if(!(HeadEnum.HasHead(ysssdw))){
					ysssdw=null;
				}else if(ysssdw.contains("系")){
					ysssdw=null;
				}else if(ysssdw.contains("女婿")){
					ysssdw=null;
				}else if(ysssdw.contains("子")){
					ysssdw=null;
				}
			}  
			
			String bssssf=HeadEnum.getHead(WsAnalyse.deBracket(content));//本审诉讼身份
			
			String sssf=bssssf;
			if(ysssdw!=null){
				sssf=bssssf+"（"+ysssdw+"）";
			}
			if (sssf != null) {
				wssscyrModel.setSssf(sssf);
				content=WsAnalyse.deBracket(content);
				int index = content.indexOf(bssssf);
				String ssmc = content.substring(index + bssssf.length(),
						content.length());
				
				//去除诉讼名称里的冒号
				if(ssmc.contains("：")){
					ssmc=ssmc.replaceFirst("：", "");
				}
				wssscyrModel.setSscyr(ssmc);
				if (dt != "") {
					if (dt.contains(ssmc)) {
						wssscyrModel.setDtqk("到庭");
					}
				}
				if (wdt != "") {
					if (wdt.contains(ssmc)) {
						wssscyrModel.setDtqk("未到庭");
					}
				}
				if (FcUtil.getWholeToken(ssmc).size() > 3) {
					wssscyrModel.setDsrlx("法人");
				} else {
					wssscyrModel.setDsrlx("自然人");
				}
		    }
			//解析诉讼地位和原审诉讼地位
			String ssdw=bssssf;
//			if(ssdw.contains("上诉人")||ssdw.contains("被上诉人")){
//				wssscyrModel.setSsdw(ssdw);
//			}
			if(StringUtil.contains(ssdw, "再审申请人")||StringUtil.contains(ssdw, "被申请人")){
				wssscyrModel.setSsdw(ssdw);
			}
			if(ysssdw!=null){
				String temp=ysssdw;
				if(ysssdw.contains("原审")){
					ysssdw=ysssdw.substring(ysssdw.indexOf("原审")+2,ysssdw.indexOf("原审")+4 );
				}else if(ysssdw.contains("一审")){
					ysssdw=ysssdw.substring(ysssdw.indexOf("一审")+2, ysssdw.indexOf("一审")+4);
				}
				if(ysssdw.contains("第三")){
					ysssdw="第三人";
				}if(ysssdw.contains("起诉")){
					ysssdw="原告";
				}if(ysssdw.contains("本诉")){
					ysssdw=temp.substring(temp.indexOf("本诉")+2,temp.indexOf("本诉")+4);
				}
				if(temp.contains("二审")){
					esssdw=temp.substring(7);
				}
				wssscyrModel.setYsssdw(ysssdw);
				wssscyrModel.setEsssdw(esssdw);
			}
	
			//解析当事人类别
			String dsrlb=null;//当事人类别
			String[] dlr={"法定代理人","委托代理人","法定代表人","责任人","第三人"};//代理人
			if(bssssf!=null){
				if(bssssf.equals("再审申请人")){
					dsrlb="再审申请人";
				}else if(bssssf.equals("被申请人")){
					dsrlb="被申请人";
				}
				for(int j=0;j<dlr.length;j++){
					if(dsrlb!=null&&dsrlb.equals(dlr[j])){
						dsrlb="代理人";
					}
				}
			}
			wssscyrModel.setDsrlb(dsrlb);


			for (int j = 1; j < contentlist.size(); j++) {
				String zjlx=null;//证件类型
				String zjhm = null;
				// 解析证件类型、证件号
				if (contentlist.get(j).indexOf("身份") != -1) {
					zjlx="身份证";
					Pattern pattern = Pattern.compile("\\d{18}|\\d{17}(\\d|X|x)");
					Matcher match = pattern.matcher(contentlist.get(j));
					while (match.find()){
						zjhm=match.group();
					}
				}else if(contentlist.get(j).indexOf("执业证")!=-1){
					zjlx="执业证";
					ArrayList<String> zjxx = (ArrayList<String>) FcUtil
							.getWholeToken(contentlist.get(j));
					Pattern pattern = Pattern.compile("\\d");
					for (int k = 0; k < zjxx.size(); k++) {
						int count = 0;
						Matcher match = pattern.matcher(zjxx.get(k));
						while (match.find())
							count++;
						if (count >= 14)
							zjhm = zjxx.get(k);
					}
				}else if (contentlist.get(j).indexOf("护照")!=-1){
					zjlx="护照";
					Pattern pattern = Pattern.compile("[a-zA-Z0-9]{5,17}");
					Matcher match = pattern.matcher(contentlist.get(j));
					while (match.find()){
						zjhm=match.group();
					}

				}
				if (zjlx!=null){
					wssscyrModel.setZjlx(zjlx);
				}
				// 解析地址
				String dsrdz = null;
				if (contentlist.get(j).indexOf("住所地") != -1
						&& contentlist.get(j).indexOf("住所地") < 3) {
					dsrdz = contentlist.get(j).substring(
							contentlist.get(j).indexOf("住所地") + 3,
							contentlist.get(j).length());

				} else if (contentlist.get(j).indexOf("住") != -1
						&& contentlist.get(j).indexOf("住") < 3) {
					dsrdz = contentlist.get(j).substring(
							contentlist.get(j).indexOf("住") + 1,
							contentlist.get(j).length());

				}



				// 解析性别
				String xb = null;
				if (contentlist.get(j).indexOf("男") != -1
						&& contentlist.get(j).length() < 4) {
					xb = "男";
				} else if (contentlist.get(j).indexOf("女") != -1
						&& contentlist.get(j).length() < 4) {
					xb = "女";
				}

				// 解析出生日期
				String csrq = null;
				int rq = 0;
				if (contentlist.get(j).indexOf("年") != -1)
					rq++;
				if (contentlist.get(j).indexOf("月") != -1)
					rq++;
				if (contentlist.get(j).indexOf("日") != -1)
					rq++;
				if (contentlist.get(j).indexOf("生") != -1)
					rq++;
				if (rq == 4&&(contentlist.get(j).indexOf("年") - 3)>0) {
//					XX年XX月XX日
					if(contentlist.get(j).indexOf("年")>3){
						csrq = contentlist.get(j).substring(
								contentlist.get(j).indexOf("年") - 4,
								contentlist.get(j).indexOf("日") + 1);
					}

				}
				// 解析详细出生日期
				String year = null;
				String month = null;
				String day = null;
				if (csrq != null) {
					year = csrq.substring(csrq.indexOf("年") - 4,
							csrq.indexOf("年"));
					month = csrq.substring(csrq.indexOf("年") + 1,
							csrq.indexOf("月"));
					day = csrq.substring(csrq.indexOf("月") + 1,
							csrq.indexOf("日"));
				}
				// 解析民族
				String mz = null;
				if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
					mz = MZEnum.getMZ(contentlist.get(j));
				}

				// 解析职务
				String dsrzw = null;
				if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
					dsrzw = ZWEnum.getZW(contentlist.get(j));
				} else {
					dsrzw = ZWEnum.getZW(contentlist.get(j));
				}

				// 解析文化程度
				String dsrwhcd = null;
				if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
					dsrwhcd = WhcdEnum.getWhcd(contentlist.get(j));
				}

				// 将解析过的内容放入model中
				if (dsrdz != null) {
					//去除地址中的冒号
					if(dsrdz.contains("：")){
						dsrdz=dsrdz.replaceFirst("：", "");
					}
					wssscyrModel.setDsrdz(dsrdz);
				}
				if (xb != null) {
					wssscyrModel.setXb(xb);
				}
				if (csrq != null) {
					wssscyrModel.setCsrq(csrq);
				}
				if (zjhm != null) {
					wssscyrModel.setZjhm(zjhm);
				}
				if (mz != null) {
					wssscyrModel.setMz(mz);
				}
				if (dsrzw != null) {
					wssscyrModel.setDsrzw(dsrzw);
				}
				if (year != null && month != null && day != null) {
					wssscyrModel.setYear(year);
					wssscyrModel.setMonth(month);
					wssscyrModel.setDay(day);

				}
			}
			//解析国籍
			setDsrgj(wssscyrModel);
//			解析单位性质
			setDwxz(wssscyrModel);
			wssscyrModellist.add(wssscyrModel);
		}
		setFddbr(wssscyrModellist);
		return wssscyrModellist;
				
	}
		public static void setDwxz(WssscyrModel wssscyrModel){
			String name = wssscyrModel.getSscyr();
			if(name!=null && name.indexOf("股份有限公司")>-1){
				wssscyrModel.setDwxz("股份有限公司");
			}else if(name!=null && (name.indexOf("有限责任公司")>-1||name.indexOf("有限公司")>-1)){
				wssscyrModel.setDwxz("有限责任公司");
			}else if(name!=null && name.endsWith("公司")){
				wssscyrModel.setDwxz("有限责任公司");
			}else if(name!=null && (name.indexOf("律师事务所")>-1||name.endsWith("医院"))){
				wssscyrModel.setDwxz("事业单位");
			}else if(name!=null && (name.endsWith("厂")||name.endsWith("商行")||name.endsWith("法律事务所")||name.endsWith("运输队"))){
				wssscyrModel.setDwxz("企业");
			}
		}
		
		public static void setFddbr(List<WssscyrModel> wssscyrModellist ){
			int ssfIndex = -1;
			int ysfIndex = -1;
			boolean ssf = false;
			boolean ysf = false;
			for(int i=0;i<wssscyrModellist.size();i++){
				String ssdw = wssscyrModellist.get(i).getSssf();//诉讼身份
				if(!ssf && StringUtil.indexOf(ssdw, "再审申请人")==0){
					ssfIndex = i;
					ssf = true;
				}
				if(!ysf && StringUtil.indexOf(ssdw, "被申请人")==0){
					ysfIndex = i;
					ysf = true;
				}
			}
			String ssfDbr = "";
			String ysfDbr = "";
			for(int i=0;i<wssscyrModellist.size();i++){
				//上诉方法定代表人
				if(ssfIndex!=-1 && StringUtil.equals(wssscyrModellist.get(i).getSssf(), "法定代表人") &&((i<ysfIndex)||ysfIndex==-1)){
					ssfDbr = wssscyrModellist.get(i).getSscyr();
//	        		这个法定代表人的工作单位就是上诉方
					wssscyrModellist.get(i).setGzdw(wssscyrModellist.get(ssfIndex).getSscyr());
					wssscyrModellist.get(i).setGzdwxz(wssscyrModellist.get(ssfIndex).getDwxz());
				}else if(ysfIndex!=-1 && StringUtil.equals(wssscyrModellist.get(i).getSssf(), "法定代表人") && i>ysfIndex){
					ysfDbr = wssscyrModellist.get(i).getSscyr();
//	        		这个法定代表人的工作单位就是应诉方
					wssscyrModellist.get(i).setGzdw(wssscyrModellist.get(ysfIndex).getSscyr());
					wssscyrModellist.get(i).setGzdwxz(wssscyrModellist.get(ysfIndex).getDwxz());
				}
			}
			if(ssfIndex!=-1 && !StringUtil.isBlank(ssfDbr))
				wssscyrModellist.get(ssfIndex).setFddbr(ssfDbr);
			if(ysfIndex!=-1 && !StringUtil.isBlank(ysfDbr))
				wssscyrModellist.get(ysfIndex).setFddbr(ysfDbr);
		}		

}	
