package nju.software.wsjx.parserule.wsajjbqkparserule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.FcUtil;

/**
 * 行政一审案件基本信息解析
 * @author wangzh
 *
 */
public class XzysAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule{

	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> ajjbqklist = wsAnalyse.getAjjbqk();
		WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();
		List<Integer> exindex=new ArrayList<Integer>();
		List<String> ajjbqk=new ArrayList<String>();
		if(ajjbqklist==null||ajjbqklist.size()==0)
			return wsajjbqkModel;
		String ajjfirst=ajjbqklist.get(0);
		int cmsspre=1;
		if(ajjfirst.substring(0,ajjfirst.length()-1).lastIndexOf("。")>0)
			cmsspre=2;
		for(int i=0;i<ajjbqklist.size();i++){
			String temp=ajjbqklist.get(i);
			temp=temp.substring(0,temp.length()-1);
			int jhindex=temp.lastIndexOf("。");
			if(jhindex==-1){
				ajjbqk.add(ajjbqklist.get(i));
			}else{
				ajjbqk.add(ajjbqklist.get(i).substring(0,jhindex+1));
				ajjbqk.add(ajjbqklist.get(i).substring(jhindex+1,ajjbqklist.get(i).length()));
			}
			


		}

		int[] cmssin=new int[ajjbqk.size()];
		int cmsstotal=0;
		//解析第三人意见段
		String dsryjd="";
		for(int i=0;i<ajjbqk.size();i++){
			String ajnr=ajjbqk.get(i);
			if(ajnr.indexOf("第三人")==0){
				dsryjd=ajnr;
				exindex.add(i);
				break;
			}
		}
		if (dsryjd!="")
			wsajjbqkModel.setDsryjd(dsryjd);
		//解析被告辩称段
		String bgbcd="";
		int[] bgbcdindex=new int[ajjbqk.size()];
		for(int i=0;i<ajjbqk.size();i++){
			boolean b=true;
			for(int j=0;j<exindex.size();j++){
				if(i==exindex.get(j)){
					b=false;
					break;
				}
			}
			if(b){
				String ajnr=ajjbqk.get(i);
				String ajnrfirst="";
				if(ajnr.length()<50)
					ajnrfirst=ajnr;
				else
					ajnrfirst=ajnr.substring(0,50);
				List<String> nrtoken = FcUtil.getWholeToken(ajnr);
				int index=0;
				if(nrtoken.size()<30){
					index=nrtoken.size();
				}else{
					index=30;
				}
				boolean likezjd=likeZjd(ajnr);
				boolean iszjd=isZjd(ajnr);
				int bgdb=worddis("被告","答辩",ajnr);
				int bgbc=worddis("被告","辩称",ajnr);
				for(int j=0;j<index;j++){
					String content=nrtoken.get(j);
					if((content.equals("辩称")&&(likezjd==false))||
							((bgbc>-1&&bgbc<10)&&(iszjd==false))){
						bgbcd+=ajnr+" ";
						exindex.add(i);
						bgbcdindex[i]=1;
						break;
					}
					if(ajnrfirst.contains("答辩")&&(likezjd==false)||
							((bgdb>-1&&bgdb<25)&&(iszjd==false))){
						bgbcd+=ajnr+" ";
						exindex.add(i);
						bgbcdindex[i]=1;
						break;
					}
				}
			}
		}  

		//解析原告诉称段
		String ygscd="";
		int[] ygscdindex=new int[ajjbqk.size()];
		int ygscpre=-5;
		for(int i=0;i<ajjbqk.size();i++){
			boolean b=true;
			for(int j=0;j<exindex.size();j++){
				if(i==exindex.get(j)){
					b=false;
					break;
				}
			}
			if(b){
				String ajnr=ajjbqk.get(i);
				List<String> nrtoken = FcUtil.getWholeToken(ajnr);
				int index=0;
				if(nrtoken.size()<30){
					index=nrtoken.size();
				}else{
					index=30;
				}
				boolean iscmss=isCmssd(ajnr);
				boolean likezjd=likeZjd(ajnr);
				int yg=ajnr.indexOf("原告");
				int ch=ajnr.indexOf("称");
				int qqpl=worddis("请求","判令",ajnr);
				boolean isygsc=false;
				if(yg>-1&&yg<10&&ch>-1&&ch<15&&ch-yg>0)
					isygsc=true;
				for(int j=0;j<index;j++){
					String content=nrtoken.get(j);
					if(isygsc||(qqpl>-1&&qqpl<10)){
						if(ygscpre>-5){
							if((i-ygscpre)==1){
								ygscd+=ajnr+" ";
								exindex.add(i);
								ygscpre=i;
								ygscdindex[i]=1;
								break;
							}
						}else{
							ygscd+=ajnr+" ";
							exindex.add(i);
							ygscpre=i;
							ygscdindex[i]=1;
							break;
						}

					}
					if(content.equals("诉称")||content.equals("诉至")||
							(content.equals("诉讼请求")||content.equals("诉请")||(content.equals("陈述")))
							&&(likezjd==false)&&(iscmss==false)&&(!ajnr.contains("查明"))){
						if(ygscpre>-5){
							if((i-ygscpre)==1){
								ygscd+=ajnr+" ";
								exindex.add(i);
								ygscpre=i;
								ygscdindex[i]=1;
								break;
							}
						}else{
							ygscd+=ajnr+" ";
							exindex.add(i);
							ygscpre=i;
							ygscdindex[i]=1;
							break;
						}
					}
				}
			}
		}  

		//解析查明事实段
		
		for(int i=cmsspre;i<ajjbqk.size();i++){
			boolean b=true;
			for(int j=0;j<exindex.size();j++){
				if(i==exindex.get(j)){
					b=false;
					break;
				}
			}
			if(b){
				String ajnr=ajjbqk.get(i);
				List<String> nrtoken = FcUtil.getWholeToken(ajnr);
				int index=0;
				if(nrtoken.size()<30){
					index=nrtoken.size();
				}else{
					index=30;
				}
				boolean iscmss=isCmssd(ajnr);
				boolean likezjd=likeZjd(ajnr);
				boolean iszjd=isZjd(ajnr);
				if(((ajnr.indexOf("另查")>-1&&ajnr.indexOf("另查")<30)||
						(ajnr.indexOf("审理中")>-1&&ajnr.indexOf("审理中")<30)
						||iscmss)&&(iszjd==false)){
					cmssin[i]=1;
					cmsstotal++;
					exindex.add(i);
				}else{
					for(int j=0;j<index;j++){
						String content=nrtoken.get(j);
						if(content.equals("查明")
								&&(likezjd==false)&&(iszjd==false)){
							cmssin[i]=1;
							cmsstotal++;
							exindex.add(i);
							break;
						}
					}	
				}

			}
		} 	
		//解析证据段
		int[] zjdindex=new int[ajjbqk.size()];
		int zjdtotal=0;
		for(int i=0;i<ajjbqk.size();i++){
			boolean b=true;
			for(int j=0;j<exindex.size();j++){
				if(i==exindex.get(j)){
					b=false;
					break;
				}
			}
			if(b){
				String ajnr=ajjbqk.get(i);
				if((likeZjd(ajnr)||isZjd(ajnr))&&(!issurplus(ajnr))){
					zjdindex[i]=1;
					zjdtotal++;
					exindex.add(i);
				}								
			}
		} 
		int [] exin=new int[ajjbqk.size()];
		for(int i=0;i<exindex.size();i++){
			exin[exindex.get(i)]=1;
		}
//		for(int i=0;i<exin.length;i++){			
//		System.out.println(exin[i]+"  "+ygscdindex[i]+" "+bgbcdindex[i]+" "+zjdindex[i]+" "+cmssin[i]);
//	if(exin[i]==0)
//		System.out.println(ajjbqk.get(i));
//	}
		String xzsszyd="";
		if(cmsspre==1){
			if(exin[0]==0){
				xzsszyd=ajjbqk.get(0);
				exin[0]=1;
				exindex.add(0);
			}

		}else if(cmsspre==2){
			if(exin[0]==0&&exin[1]==1){
				xzsszyd=ajjbqk.get(0);
				exin[0]=1;
				exindex.add(0);
			}

			if(exin[0]==0&&exin[1]==0){
				xzsszyd=ajjbqk.get(0)+ajjbqk.get(1);
				exin[0]=1;
				exin[1]=1;
				exindex.add(0);
				exindex.add(1);
			}

		}
		if(xzsszyd!="")
		wsajjbqkModel.setXzsszyd(xzsszyd);
//		for(int i=0;i<exin.length;i++){			
//			System.out.println(exin[i]+"  "+ygscdindex[i]+" "+bgbcdindex[i]+" "+zjdindex[i]+" "+cmssin[i]);
//		if(exin[i]==0)
//			System.out.println(ajjbqk.get(i));
//		}
		//补齐原告诉称段
		boolean y=true;
		int ygscdpre=-1;
		for(int i=0;i<ajjbqk.size();i++){
			if(ygscdindex[i]==1){
				ygscdpre=i;
				break;
			}
		}
		if(ygscdpre>-1){
			while(y){
				y=false;
				for(int i=ygscdpre;i<ajjbqk.size()-1;i++){
					if(ygscdindex[i]==1){
						if(exin[i+1]==0){
							ygscdpre=i+1;
							ygscd+=ajjbqk.get(ygscdpre)+" ";
							ygscdindex[ygscdpre]=1;
							exin[ygscdpre]=1;
							exindex.add(ygscdpre);
							y=true;
						}
					}
				}
			}
		}
		if(ygscd!="")
			wsajjbqkModel.setYgscd(ygscd);
		//补齐被告辩称段
		boolean bg=true;
		int bgbcdpre=-1;
		for(int i=0;i<ajjbqk.size();i++){
			if(bgbcdindex[i]==1){
				bgbcdpre=i;
				break;
			}
		}
		if(bgbcdpre>-1){
			while(bg){
				bg=false;
				for(int i=bgbcdpre;i<ajjbqk.size()-1;i++){
					if(bgbcdindex[i]==1){
						if(exin[i+1]==0){
							bgbcdpre=i+1;
							bgbcd+=ajjbqk.get(bgbcdpre)+" ";
							bgbcdindex[bgbcdpre]=1;
							exin[bgbcdpre]=1;
							exindex.add(bgbcdpre);
							bg=true;
						}
					}
				}
			}
		}
		if(bgbcd!="")
			wsajjbqkModel.setBgbcd(bgbcd);
		//			for(int i=0;i<ajjbqk.size();i++){
		//				System.out.println(i+" "+zjdindex[i]+"  "+exin[i]);
		//			}
		//补齐证据段
		for(int i=0;i<ajjbqk.size();i++){
			if(zjdindex[i]==1){
				for(int j=0;j<i;j++){
					if(zjdindex[j]==1){
						for(int k=j;k<i;k++){
							boolean b=true;
							for(int m=0;m<exindex.size();m++){
								if(k==exindex.get(m)){
									b=false;
									break;
								}
							}
							if(b){
								zjdindex[k]=1;
								zjdtotal++;
								exindex.add(k);
							}
						}
					}
				}
			}
		}
		//			for(int i=0;i<zjdindex.length;i++){
		//				if(zjdindex[i]==1)
		//					System.out.println(i);
		//			}
		//将剩余段落划分到查明事实段
		for(int i=0;i<ajjbqk.size();i++){
			boolean b=true;
			for(int j=0;j<exindex.size();j++){
				if(i==exindex.get(j)){
					b=false;
					break;
				}
			}
			if(b){
				cmssin[i]=1;
				cmsstotal++;
			}
		}
		//将证据段整理放入model中
		if(zjdtotal>0){
			if(zjdtotal==1){
				List<String> zjdlist=new ArrayList<String>();
				for(int i=0;i<zjdindex.length;i++){
					if(zjdindex[i]==1){
						zjdlist.add(ajjbqk.get(i));
					}
				}
				wsajjbqkModel.setZjd(zjdlist);
			}else{
				List<String> zjdlist=new ArrayList<String>();
				String zjd="";
				int pre=0;
				for(int i=0;i<zjdindex.length;i++){
					if(zjdindex[i]==1){
						zjd=ajjbqk.get(i);
						pre=i;
						break;
					}
				}
				for(int i=pre+1;i<ajjbqk.size();i++){
					if((zjdindex[i]==1)&&((i-pre)==1)){								
						zjd+=ajjbqk.get(i)+" ";
						pre=i;
					}else if(zjdindex[i]==1){
						zjdlist.add(zjd);
						zjd=ajjbqk.get(i)+" ";
						pre=i;
					}
				}
				zjdlist.add(zjd);
				wsajjbqkModel.setZjd(zjdlist);
			}

		}
		//将查明事实段整理放入model中
		if(cmsstotal>0){
			if(cmsstotal==1){
				List<String> cmsslist=new ArrayList<String>();
				for(int i=0;i<cmssin.length;i++){
					if(cmssin[i]==1){
						cmsslist.add(ajjbqk.get(i));
					}
				}
				wsajjbqkModel.setCmssd(cmsslist);
			}else{
				List<String> cmsslist=new ArrayList<String>();
				String cmss="";
				int pre=0;
				for(int i=0;i<cmssin.length;i++){
					if(cmssin[i]==1){
						cmss=ajjbqk.get(i);
						pre=i;
						break;
					}
				}
				for(int i=pre+1;i<ajjbqk.size();i++){
					if((cmssin[i]==1)&&((i-pre)==1)){								
						cmss+=ajjbqk.get(i)+" ";
						pre=i;
					}else if(cmssin[i]==1){
						cmsslist.add(cmss);
						cmss=ajjbqk.get(i)+" ";
						pre=i;
					}
				}
				cmsslist.add(cmss);
				wsajjbqkModel.setCmssd(cmsslist);
			}

		}



		return wsajjbqkModel;
	}
	public boolean likeZjd(String content){
		boolean b=false;
		if(content.length()>=100)
			content=content.substring(0,100);
		if(content.contains("证据")||content.contains("证言")||content.contains("佐证")
				||content.contains("证言")||content.contains("证实")||content.contains("证明")
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
	public boolean issurplus(String content){
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
