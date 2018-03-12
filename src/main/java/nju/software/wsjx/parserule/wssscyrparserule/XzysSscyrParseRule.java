package nju.software.wsjx.parserule.wssscyrparserule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.Enum.MZEnum;
import nju.software.wsjx.model.Enum.StEnum;
import nju.software.wsjx.model.Enum.TshyEnum;
import nju.software.wsjx.model.Enum.WhcdEnum;
import nju.software.wsjx.model.Enum.ZWEnum;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.util.XzglfwSelector;
/**
 * 行政一审诉讼参与人解析
 * @author wangzh
 *
 */
public class XzysSscyrParseRule extends GeneralSscyrCommonRule implements SscyrParseRule{

	public List<WssscyrModel> jxWssscyrModelList(WsAnalyse wsAnalyse) {
		List<String> wssscyr = wsAnalyse.getSscyr();
        List<String> ajjbqk = wsAnalyse.getAjjbqk();
        String ssjl = wsAnalyse.getSsjl();
		List<WssscyrModel> wssscyrModellist = new ArrayList<WssscyrModel>();
		if(wssscyr == null || wssscyr.size() == 0)
			return wssscyrModellist;
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
		String qz="";
		for (int i = 0; i < wssscyr.size(); i++) {
			WssscyrModel wssscyrModel = new WssscyrModel();
			ArrayList<String> contentlist = WsAnalyse.getWholeContent(wssscyr
					.get(i));
			String sscyrallinfo="";
			for(String sscyrinfo:contentlist){
				sscyrallinfo+=sscyrinfo; 

			}
			
			wssscyrModel.setSscyrallinfo(sscyrallinfo);
			// 解析诉讼参与人名、身份与类型
			//String content = WsAnalyse.deBracket(contentlist.get(0));
			String content=contentlist.get(0);
			
			String bssssf = HeadEnum.getHead(WsAnalyse.deBracket(content));//诉讼身份

			String sssf=bssssf;
			if (sssf != null) {
				wssscyrModel.setSssf(sssf);
				//删除括号后面的内容
				if(content.indexOf(")") != -1)
					content = content.substring(0,content.indexOf(")")+1);
				if(content.indexOf("）") != -1)
					content = content.substring(0,content.indexOf("）")+1);
				content=WsAnalyse.deBracket(content);
				int index = content.indexOf(bssssf);
				
				String ssmc = content.substring(index + bssssf.length(),
						content.length());
				
				if((ssmc.equals("")||ssmc == null)&&contentlist.size()>=2){
					ssmc = contentlist.get(1);
				}
				//去除诉讼名称里的冒号
				if(ssmc.contains("：")){
					ssmc=ssmc.replaceFirst("：", "");
				}
				
				if(ssmc!=null){
					ssmc=ssmc.replaceAll("[，,.。()（）:：]", "");
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
				if (FcUtil.getWholeToken(ssmc).size() > 3||ssmc.length()>7) {
					wssscyrModel.setDsrlx("法人");
				} else {
					wssscyrModel.setDsrlx("自然人");
				}
			}
			//解析诉讼地位
			String ssdw=bssssf;
			if(ssdw!=null){
				if(ssdw.contains("原告")||ssdw.contains("被告")){
					wssscyrModel.setSsdw(ssdw);
				}
				//解析行政法律关系主体
				if(ssdw.equals("原告")){
					wssscyrModel.setXzfagxzt("行政相对方");
				}else if(ssdw.equals("被告")){
					wssscyrModel.setXzfagxzt("行政主体");
					//解析被告类型
					setBglx(wssscyrModel,ssjl,ajjbqk);
				}
			}

			//解析当事人类别
			String dsrlb=null;//当事人类别
			String[] dlr={"法定代理人","委托代理人","委托诉讼代理人","法定代表人","责任人","第三人"};//代理人
			if(bssssf!=null){
				if(bssssf.equals("原告")){
					dsrlb="原告";
				}else if(bssssf.equals("被告")){
					dsrlb="被告";
				}
				for(int j=0;j<dlr.length;j++){
					if(bssssf!=null&&bssssf.equals(dlr[j])){
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
				//解析组织机构代码
				String zzjgdm=null;
				String contentinfo=contentlist.get(j);
				int zzindex=contentinfo.indexOf("组织机构代码");
				if(zzindex!=-1){
					zzjgdm=contentinfo.substring(zzindex, contentinfo.length());
					String regex="[A-Za-z0-9]{8}-[A-Za-z0-9]{1}|[A-Za-z0-9]{9}|[A-Za-z0-9]{9}|[A-Za-z0-9]{8}－[A-Za-z0-9]{1}";
					Pattern pattern=Pattern.compile(regex);
					Matcher matcher=pattern.matcher(zzjgdm);
					while(matcher.find()){
						zzjgdm=matcher.group();
					}
				}
				if(zzjgdm!=null){
					wssscyrModel.setZzjgdm(zzjgdm);
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
								contentlist.get(j).indexOf("日",contentlist.get(j).indexOf("年")) + 1);
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
			//解析行政管理范围
			setXzglfw(wssscyrModel);
			if(StEnum.hasStzt(sssf))
				qz=wssscyrModel.getSscyr();
			String dsrzw=wssscyrModel.getDsrzw();
			if (dsrzw != null) {
				wssscyrModel.setDsrzw(dsrzw);
				if("律师".equals(dsrzw)){
					wssscyrModel.setXgsscyr(qz);
				}
			}
			wssscyrModellist.add(wssscyrModel);
		}

		setFddbr(wssscyrModellist);
		//解析特殊行业
		setTshy(wssscyrModellist);

		return wssscyrModellist;
	}

	private void setXzglfw(WssscyrModel wssscyrModel) {
		//行政管理范围
		String xzglfw=null;
		String dsrmc=wssscyrModel.getSscyr();//当事人名称
		String dsrssdw=wssscyrModel.getSsdw();//当事人诉讼地位
		if (dsrmc!=null&&dsrssdw!=null){
			if (dsrssdw.equals("被告")) {
				xzglfw = XzglfwSelector.selectXzglfw(dsrmc);
			}
		}
		wssscyrModel.setXzglfw(xzglfw);
	}


	private void setBglx(WssscyrModel wssscyrModel,String ssjl,List<String> ajjbqk) {
		// TODO Auto-generated method stub
		String bglx=null;
		if(ajjbqk!=null){
			ArrayList<String> ajjbqkfd = WsAnalyse.getWholeContent(ajjbqk
					.get(0));
			for(int i=0;i<ajjbqkfd.size();i++){
				String item=ajjbqkfd.get(i);
				if(item.contains(wssscyrModel.getSscyr()+"批准")){
					bglx="具体行政的批准机关";
				}
			}
		}
		if(!StringUtil.isBlank(ssjl)){
			if(ssjl.contains("行政复议")){
				bglx="具体行政行为的复议机关";
			}else{
				bglx="作出具体行政行为的行政机关";
			}
		}
		wssscyrModel.setBglx(bglx);
	}

	private void setTshy(List<WssscyrModel> wssscyrModellist) {
		// TODO Auto-generated method stub
		//三种情况，一种是法定代表人存在工作单位的；一种是诉讼人就是公司；一种是当事人的职位
		for(int i=0;i<wssscyrModellist.size();i++){
			if(wssscyrModellist.get(i).getGzdw()!=null){
				setTshyDlr(wssscyrModellist.get(i));
			}else if(wssscyrModellist.get(i).getDsrzw()!=null){
				setTshyDsrzw(wssscyrModellist.get(i));
			}

			if(wssscyrModellist.get(i).getTshy()==null){
				setTshySsr(wssscyrModellist.get(i));
			}else if(wssscyrModellist.get(i).getTshy()==null){
				setTshyAll(wssscyrModellist.get(i));
			}
		}
	}

	private void setTshyDsrzw(WssscyrModel wssscyrModel) {
		String tshy=null;//特殊行业
		String dsrzw=wssscyrModel.getDsrzw();//当事人职务
		if(dsrzw!=null){
			tshy=TshyEnum.selectTshy(dsrzw);
			wssscyrModel.setTshy(tshy);
		}

	}

	private void setTshySsr(WssscyrModel wssscyrModel) {
		// TODO Auto-generated method stub
		String tshy=null;//特殊行业
		String dsrmc=wssscyrModel.getSscyr();//当事人名称
		if(dsrmc!=null){
			tshy=TshyEnum.selectTshy(dsrmc);
			wssscyrModel.setTshy(tshy);
		}
	}

	private void setTshyDlr(WssscyrModel wssscyrModel) {
		// TODO Auto-generated method stub
		String tshy=null;
		String gzdw=wssscyrModel.getGzdw();//工作单位
		if(gzdw!=null){
			tshy=TshyEnum.selectTshy(gzdw);
			wssscyrModel.setTshy(tshy);
		}
	}

	private void setTshyAll(WssscyrModel wssscyrModel) {
		// TODO Auto-generated method stub
		String tshy=null;//特殊行业
		String dsrall=wssscyrModel.getSscyrallinfo();//当事人全部信息
		if(dsrall!=null){
			tshy=TshyEnum.selectTshy(dsrall);
			wssscyrModel.setTshy(tshy);
		}

	}




	public boolean equalsPunc(String temp){
		boolean equalsPunc=false;
		if(temp.equals("，")||temp.equals("，")||temp.equals("。")||temp.equals("；")||temp.equals("（")){
			equalsPunc=true;
		}
		return equalsPunc;
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
		}else if(name!=null&&(name.endsWith("政府")||name.endsWith("局")||name.endsWith("街道办事处"))){
			wssscyrModel.setDwxz("机关");
		}else if (name!=null&&(name.endsWith("合作社"))){
			wssscyrModel.setDwxz("集体企业");
		}
	}
	public static void setFddbr(List<WssscyrModel> wssscyrModellist ){
		int ssfIndex = -1;
		int ysfIndex = -1;
		boolean ssf = false;
		boolean ysf = false;
		for(int i=0;i<wssscyrModellist.size();i++){
			String ssdw = wssscyrModellist.get(i).getSssf();//诉讼身份
			if(!ssf && ssdw.indexOf("原告")==0){
				ssfIndex = i;
				ssf = true;
			}
			if(ssdw!=null){
				if(!ysf && ssdw.indexOf("被告")==0){
					ysfIndex = i;
					ysf = true;
				}
			}
		}
		String ssfDbr = "";
		String ysfDbr = "";
		for(int i=0;i<wssscyrModellist.size();i++){
			//原告法定代表人
			if(ssfIndex!=-1 && StringUtil.equals(wssscyrModellist.get(i).getSssf(), "法定代表人") &&((i<ysfIndex)||ysfIndex==-1)){
				ssfDbr = wssscyrModellist.get(i).getSscyr();
//        		这个法定代表人的工作单位就是原告
				wssscyrModellist.get(i).setGzdw(wssscyrModellist.get(ssfIndex).getSscyr());
				wssscyrModellist.get(i).setGzdwxz(wssscyrModellist.get(ssfIndex).getDwxz());
			}else if(ysfIndex!=-1 && StringUtil.equals(wssscyrModellist.get(i).getSssf(), "法定代表人") && i>ysfIndex){
				ysfDbr = wssscyrModellist.get(i).getSscyr();
//        		这个法定代表人的工作单位就是被告
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
