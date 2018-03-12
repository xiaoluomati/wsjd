package nju.software.wsjx.parserule.wsajjbqkparserule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

/**
 * 刑事二审案件基本信息解析
 * @author wangzh
 *
 */
public class XsesAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule{
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> ajjbqk = wsAnalyse.getAjjbqk();	
		WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();
		if(ajjbqk.size()==1){
			String xsbssld=ajjbqk.get(0);
			wsajjbqkModel.setXsbssld(xsbssld);
		}else{
			int xsbssldindex=-1;
			int ssssbhyjindex=-1;
			int gsjgctyjindex=-1;
			//解析本审审理段
			for(int i=ajjbqk.size()-1;i>=0;i--){
				String ajnr=ajjbqk.get(i);
				if(ajnr.length()>10)
					ajnr=ajnr.substring(0,10);
				  if(ajnr.contains("审理查明")
						 ){
					xsbssldindex=i;
					break;
				}
			}
			if(xsbssldindex==-1){
				for(int i=ajjbqk.size()-1;i>=0;i--){
					String ajnr=ajjbqk.get(i);
					if(ajnr.length()>10)
						ajnr=ajnr.substring(0,10);
					  if(ajnr.contains("经审查")
							  ||(ajnr.indexOf("查明")>0&&ajnr.indexOf("查明")<8)){
						xsbssldindex=i;
						break;
					}
				}
			}
			if(xsbssldindex==-1){
				for(int i=ajjbqk.size()-1;i>=0;i--){
					String ajnr=ajjbqk.get(i);
					if(ajnr.length()>60)
						ajnr=ajnr.substring(0,60);
					  if(ajnr.contains("本院审理")){
						xsbssldindex=i;
						break;
					}
				}
			}
			for(int i=0;i<xsbssldindex;i++){
				String ajnr=ajjbqk.get(i);
				if(ajnr.length()>30)
					ajnr=ajnr.substring(0,30);
				  if((ajnr.contains("上诉")||ajnr.contains("宣判后")||ajnr.contains("辩称")
						  ||ajnr.contains("辩护")||ajnr.contains("抗诉"))){
					  ssssbhyjindex=i;
					  break;
				  }
			}
			int[] ssindex=new int[ajjbqk.size()];
			String ssssbhyjpre="";
			String qssldend="";
			if(ssssbhyjindex>0){
				String content=ajjbqk.get(ssssbhyjindex-1);
				String[] contentlist=content.split("。");
				String temp=contentlist[contentlist.length-1];
				if(temp.length()>20)
					temp=temp.substring(0,20);
				 if((temp.contains("上诉")||temp.contains("宣判后")||temp.contains("辩称")
						  ||temp.contains("辩护")||temp.contains("抗诉"))){
					 ssssbhyjpre=contentlist[contentlist.length-1]+"。";
					 for(int i=0;i<contentlist.length-1;i++){
						 qssldend+=contentlist[i]+"。";
					 }
				  }
				for(int i=ssssbhyjindex;i<xsbssldindex;i++){
					String ajnr=ajjbqk.get(i);
					if(ajnr.length()>20)
						ajnr=ajnr.substring(0,20);
					if(ajnr.contains("检察")||ajnr.contains("出庭")||ajnr.contains("公诉机关")){
						ssindex[i]=1;
						gsjgctyjindex=i;
					}
						
				}
			}else{
				int end=ajjbqk.size()-1;
				if(xsbssldindex>0)
					end=xsbssldindex-1;
				String content=ajjbqk.get(end);
				String[] contentlist=content.split("。");
				String temp=contentlist[contentlist.length-1];
				if(temp.length()>20)
					temp=temp.substring(0,20);
				 if((temp.contains("上诉")||temp.contains("宣判后")||temp.contains("辩称")
						  ||temp.contains("辩护")||temp.contains("抗诉"))){
					 ssssbhyjpre=contentlist[contentlist.length-1]+"。";
				  }
			}
			String qssld="";
			String xsbssld="";
			String ssssbhyj="";
			String gsjgctyj="";
			if(xsbssldindex==-1)
				xsbssldindex=0;
			if(ssssbhyjindex>-1){
				if(gsjgctyjindex==-1){
					if(ssssbhyjpre==""){
						for(int i=0;i<ssssbhyjindex;i++){
							qssld+=ajjbqk.get(i)+" ";
						}
						for(int i=ssssbhyjindex;i<xsbssldindex;i++){
							ssssbhyj+=ajjbqk.get(i)+" ";
						}
						for(int i=xsbssldindex;i<ajjbqk.size();i++)
							xsbssld+=ajjbqk.get(i)+" ";
					}else{
						for(int i=0;i<ssssbhyjindex-1;i++){
							qssld+=ajjbqk.get(i)+" ";
						}
						qssld+=qssldend+" ";
						ssssbhyj+=ssssbhyjpre;
						for(int i=ssssbhyjindex;i<xsbssldindex;i++){
							ssssbhyj+=ajjbqk.get(i)+" ";
						}
						for(int i=xsbssldindex;i<ajjbqk.size();i++)
							xsbssld+=ajjbqk.get(i)+" ";
					}

				}else{
					if(ssssbhyjpre==""){
						for(int i=0;i<ssssbhyjindex;i++){
							qssld+=ajjbqk.get(i)+" ";
						}
						for(int i=ssssbhyjindex;i<xsbssldindex;i++){
							if(ssindex[i]==0)
							ssssbhyj+=ajjbqk.get(i)+" ";
							else
							gsjgctyj+=ajjbqk.get(i)+" ";
						}
						for(int i=xsbssldindex;i<ajjbqk.size();i++)
							xsbssld+=ajjbqk.get(i)+" ";
					}else{
						for(int i=0;i<ssssbhyjindex-1;i++){
							qssld+=ajjbqk.get(i)+" ";
						}
						qssld+=qssldend+" ";
						ssssbhyj+=ssssbhyjpre;
						for(int i=ssssbhyjindex;i<xsbssldindex;i++){
							if(ssindex[i]==0)
							ssssbhyj+=ajjbqk.get(i)+" ";
							else
							gsjgctyj+=ajjbqk.get(i)+" ";
						}
						for(int i=xsbssldindex;i<ajjbqk.size();i++)
							xsbssld+=ajjbqk.get(i)+" ";
					}	
				}

			}else{
				if(ssssbhyjpre==""){
					for(int i=0;i<xsbssldindex;i++){
						qssld+=ajjbqk.get(i)+" ";
					}
					for(int i=xsbssldindex;i<ajjbqk.size();i++)
						xsbssld+=ajjbqk.get(i)+" ";
				}else{
					for(int i=0;i<ssssbhyjindex-1;i++){
						qssld+=ajjbqk.get(i)+" ";
					}
					qssld+=qssldend+" ";
					ssssbhyj+=ssssbhyjpre;
					for(int i=xsbssldindex;i<ajjbqk.size();i++)
						xsbssld+=ajjbqk.get(i)+" ";
				}
			}
			if(qssld!=""){
				wsajjbqkModel.setXsqssld(qssld);
				String []qssldlist=qssld.split(" ");
				String qscpyzypjjg="";
				String qscmssyzj="";
				int qscpyz=-1;
				for(int i=0;i<qssldlist.length;i++){
					String ajnr=qssldlist[i];	
					if(ajnr.length()>30)
						ajnr=ajnr.substring(0,30);
					  int yprw=worddis("原判","认为",ajnr);
					  int ysrw=worddis("原审","认为",ajnr);
					  int yisrw=worddis("一审","认为",ajnr);
					  if((yprw>-1&&yprw<5)||(ysrw>-1&&ysrw<5)||(yisrw>-1&&yisrw<5)){
						  qscpyz=i;
						  break;
					  }				 
				}
				if(qscpyz==-1){
					for(int i=qssldlist.length-1;i>=0;i--){
						String ajnr=qssldlist[i];	
						if(ajnr.length()>100)
							ajnr=ajnr.substring(0,100);
						  if(ajnr.contains("依照")||ajnr.contains("认为，")||ajnr.contains("判决：")){
							  qscpyz=i;
							  break;
						  }				 
					}	
				}
				if(qscpyz==-1){
					for(int i=qssldlist.length-1;i>0;i--){
						String ajnr=qssldlist[i];	
						if(ajnr.length()>30)
							ajnr=ajnr.substring(0,30);
						  int yprw=worddis("原判","认定",ajnr);
						  int ysrw=worddis("原审","认定",ajnr);
						  int yisrw=worddis("一审","认定",ajnr);
						  if((yprw>-1&&yprw<5)||(ysrw>-1&&ysrw<5)||(yisrw>-1&&yisrw<5)){
							  qscpyz=i;
							  break;
						  }				 
					}
				}
				if(qscpyz==-1){
					for(int i=0;i<qssldlist.length;i++){
						qscmssyzj+=qssldlist[i];
					}
					wsajjbqkModel.setQscmssyzj(qscmssyzj);
				}else{
					for(int i=0;i<qscpyz;i++){
						qscmssyzj+=qssldlist[i];
					}
					for(int i=qscpyz;i<qssldlist.length;i++)
						qscpyzypjjg+=qssldlist[i];
					wsajjbqkModel.setQscmssyzj(qscmssyzj);
					wsajjbqkModel.setQscpyzypjjg(qscpyzypjjg);
				}
			}

			if(ssssbhyj!="")
				wsajjbqkModel.setSsssbhyj(ssssbhyj);
			if(gsjgctyj!="")
				wsajjbqkModel.setGsjgctyj(gsjgctyj);
			if(xsbssld!="")
				wsajjbqkModel.setXsbssld(xsbssld);
		}
		return wsajjbqkModel;
	}
	public boolean likeZjd(String content){
		boolean b=false;
		if(content.length()>=100)
			content=content.substring(0,100);
		if(content.contains("证据")||content.contains("证言")||content.contains("佐证")
				||content.contains("证言")||content.contains("证实")
				||content.contains("质证")||content.contains("书证")){
			b=true;
		}
		return b;
	}
	public boolean isZjd(String content){
		boolean b=false;
		if(content.contains("经庭审质证")||content.contains("不予采信")||content.contains("予以采信")
				||content.contains("予以证明")||content.contains("开庭质证")||content.contains("可以采信")
				||content.contains("在卷为凭")||content.contains("予以佐证"))
			b=true;
		if(content.length()>=50)
			content=content.substring(0,50);
		if(content.contains("证据:")||content.contains("证据：")||content.contains("证据如下：")
				||content.contains("证据如下:")
				){
			b=true;
		}
		return b;
	}
	public boolean isCmssd(String content){
		boolean b=false;
		int qrss=worddis("确认","事实",content);
		int rdss=worddis("认定","事实",content);
		int cmss=worddis("查明","事实",content);
		if((qrss>-1&&qrss<10)||(rdss>-1&&rdss<10)||(cmss>-1&&cmss<10)
				||content.contains("以下事实")||content.contains("下列事实")||content.contains("事实如下"))
			b=true;
		return b;
	}
	public static boolean isBhdl(String content){
		boolean b=false;
		if(issurplus(content))
			return false;
		int bgryy=worddis("被告","异议",content);		
		int bgrgr=worddis("被告","供认",content);
		int bgrrk=worddis("被告","认可",content);		
		int bgrfr=worddis("被告","否认",content);
		int bgryj=worddis("被告","意见",content);
		if(content.length()>70)
			content=content.substring(0,70);
		boolean isbh=true;
		if(content.contains("公诉机关")){
			isbh=false;
			if(content.indexOf("公诉机关")>-1&&content.indexOf("被告")>-1){
				if(content.indexOf("公诉机关")>content.indexOf("被告"))
					isbh=true;
			}
		}
		if(((bgryy>-1&&bgryy<60)||(bgryj>-1&&bgryj<50)||(bgrrk>-1&&bgrrk<60)||(bgrfr>-1&&bgrfr<60)||
				(bgrgr>-1&&bgrgr<60)
				||content.contains("辩解")||content.contains("辩护")||content.contains("辩称"))
				&&(isbh==true)
				)
			b=true;

		return b;
	}
	public static boolean issurplus(String content){
		boolean b=false;
		String rexCh="[一二三四五六七八九十]";
		String rexNu="\\d{1}";
		Pattern patternCh=Pattern.compile(rexCh);
		Pattern patternNu=Pattern.compile(rexNu);

		if(content.length()>=3)
			content=content.substring(0,3);
		Matcher matcherCh=patternCh.matcher(content);
		Matcher matcherNu=patternNu.matcher(content);
		if(matcherCh.find()||matcherNu.find())
			b=true;
		return b;
	}
}
