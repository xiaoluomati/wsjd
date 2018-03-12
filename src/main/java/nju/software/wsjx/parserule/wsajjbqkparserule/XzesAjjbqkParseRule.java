package nju.software.wsjx.parserule.wsajjbqkparserule;

import java.util.ArrayList;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.FcUtil;

/**
 * 行政二审案件基本信息解析
 * @author wangzh
 *
 */
public class XzesAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule{

	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> ajjbqk = wsAnalyse.getAjjbqk();
		//解析前审段落和本审段落
		WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();

		if(ajjbqk==null)
			return wsajjbqkModel;
		if(ajjbqk.size()==1){
			wsajjbqkModel.setBsdl(ajjbqk.get(0));
			List<String> bssldlist=new ArrayList<String>();
			bssldlist.add(ajjbqk.get(0));
			wsajjbqkModel.setBssld(bssldlist);
		}else{
			int pre=-1;
			int end=-1;
			//			int[] exindex=new int[ajjbqk.size()];
			for(int j=0;j<ajjbqk.size();j++){
				String ajnr=ajjbqk.get(j);
				if(ajnr.length()>50)
					ajnr=ajnr.substring(0,50);
				int ssrsc=worddis("上诉人","诉称",ajnr);
				int ssrss=worddis("上诉人","上诉",ajnr);
				int bfsc=worddis("不服","诉称",ajnr);
				int bfss=worddis("不服","上诉",ajnr);
				boolean isQsdl=isQsdl(ajnr);
				if(((ssrsc>0&&ssrsc<30)||(bfsc>0&&bfsc<15)||(ssrss>0&&ssrss<30)
						||(bfss>0&&bfss<30))&&(isQsdl==false)){
					end=j;
					break; 
				}
			}
			if(end==-1){
				for(int j=0;j<ajjbqk.size();j++){
					String ajnr=ajjbqk.get(j);
					if(ajnr.length()>50)
						ajnr=ajnr.substring(0,50);
					boolean likeQsdl=false;
					if(ajnr.contains("上诉称")){
						ajnr=ajnr.substring(0,ajnr.indexOf("上诉称"));
						likeQsdl=likeQsdl(ajnr);
						if(!likeQsdl){
							end=j;
							break;
						}

					}

				}
			}

			pre=0;

			if(pre<5&&pre>-1){  
				if(end==-1) end=ajjbqk.size()-1;
				//解析前审段落 
				String qsdl="";
				for (int i = 0; i <end; i++) {
					if(i!=end-1)			
						qsdl += ajjbqk.get(i) + " ";
					else
						qsdl += ajjbqk.get(i);
				}
				wsajjbqkModel.setQsdl(qsdl);
				//解析本审段落
				String bsdl = "";
				for (int i = end; i < ajjbqk.size() ; i++) {
					if(i!=ajjbqk.size()-1)
						bsdl += ajjbqk.get(i) + " ";
					else
						bsdl += ajjbqk.get(i) ;
				}
				wsajjbqkModel.setBsdl(bsdl);
				//解析本审第三人意见段
				List<Integer> bsindex=new ArrayList<Integer>();
				int [] bsin=new int[ajjbqk.size()];
				String bsdsryjd="";
				for(int i=end;i<ajjbqk.size();i++){
					String ajnr=ajjbqk.get(i);
					String dsrajnr=ajnr;
					if(ajnr.length()>10)
						dsrajnr=ajnr.substring(0,10);
					if(dsrajnr.contains("第三人")){
						bsdsryjd+=ajnr+" ";
						bsindex.add(i);	
						bsin[i]=1;
					}
				}  
				if(bsdsryjd!="")
					wsajjbqkModel.setBsdsryjd(bsdsryjd);
				//解析本审被上诉人辩称段
				List<String> bssrbcdlist=new ArrayList<String>();
				for(int i=end;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						String ajnr=ajjbqk.get(i);
						String bcajnr=ajnr;
						if(ajnr.length()>40)
							bcajnr=ajnr.substring(0,40);
						int bssr=worddis("被上诉人","称",bcajnr);
						if(bcajnr.contains("辩称")||bcajnr.contains("答辩")||(bssr>-1&&bssr<20)){
							bssrbcdlist.add(ajnr);
							bsindex.add(i);	
							bsin[i]=1;
						}
					}
				}  
				if(bssrbcdlist.size()>0)
					wsajjbqkModel.setBssrbcd(bssrbcdlist);
				//解析本审上诉人诉称段
				String ssrscd="";
				int ssindex=ajjbqk.size();
				if(end+2<=ajjbqk.size())
					ssindex=end+2;
				for(int i=end;i<ssindex;i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						String ajnr=ajjbqk.get(i);
						String ssajnr=ajnr;
						if(ajnr.length()>40)
							ssajnr=ajnr.substring(0,40);
						int ssrc=worddis("上诉人","称",ssajnr);
						int ssrsc=worddis("上诉人","诉称",ajnr);
						int ssrss=worddis("上诉人","上诉",ajnr);
						int bfsc=worddis("不服","诉称",ajnr);
						int bfss=worddis("不服","上诉",ajnr);
						if(ssajnr.contains("诉称")||(ssrc>-1&&ssrc<20)||(ssrsc>0&&ssrsc<30)||(bfsc>0&&bfsc<15)||(ssrss>0&&ssrss<30)
								||(bfss>0&&bfss<30)){
							ssrscd+=ajnr+" ";
							bsindex.add(i);		
							bsin[i]=1;
						}
					}

				}  
				if(ssrscd!="")
					wsajjbqkModel.setSsrscd(ssrscd);


				//解析本审审理段
				int bssltotal=0;
				int [] bsslindex=new int[ajjbqk.size()];
				for(int i=end;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						boolean isBssld=isBssld(ajjbqk.get(i));
						if(isBssld){
							bsindex.add(i);
							bsslindex[i]=1;
							bssltotal++;
							bsin[i]=1;
						}
					}
				}
				//解析本审证据段
				int[] zjdindex=new int[ajjbqk.size()];
				int zjdtotal=0;
				for(int i=end;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						boolean iszjd=isZjd(ajjbqk.get(i));
						if(iszjd){
							zjdindex[i]=1;
							zjdtotal++;
							bsin[i]=1;
							bsindex.add(i);
						}

					}
				}
				//补齐本审审理段
				for(int i=end;i<ajjbqk.size();i++){
					if(bsslindex[i]==1){
						for(int j=end;j<i;j++){
							if(bsslindex[j]==1){
								for(int k=j;k<i;k++){
									boolean b=true;
									for(int m=0;m<bsindex.size();m++){
										if(k==bsindex.get(m)){
											b=false;
											break;
										}
									}
									if(b){
										bsslindex[k]=1;
										bssltotal++;
										bsindex.add(k);
										bsin[k]=1;
									}
								}
							}
						}
					}
				}
				for(int i=end;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}
					}
					if(b){
						zjdindex[i]=1;
						zjdtotal++;
						bsin[i]=1;
						bsindex.add(i);
					}
				}
				if(bssltotal>0){
					if(bssltotal==1){
						List<String> bssldlist=new ArrayList<String>();
						for(int i=end;i<bsslindex.length;i++){
							if(bsslindex[i]==1){
								bssldlist.add(ajjbqk.get(i));
							}
						}
						wsajjbqkModel.setBssld(bssldlist);
					}else{
						List<String> bssldlist=new ArrayList<String>();
						String bssld="";
						int prez=0;
						for(int i=end;i<bsslindex.length;i++){
							if(bsslindex[i]==1){
								bssld=ajjbqk.get(i);
								prez=i;
								break;
							}
						}
						for(int i=prez+1;i<ajjbqk.size();i++){
							if((bsslindex[i]==1)&&((i-prez)==1)){								
								bssld+=ajjbqk.get(i)+" ";
								prez=i;
							}else if(bsslindex[i]==1){
								bssldlist.add(bssld);
								bssld=ajjbqk.get(i)+" ";
								prez=i;
							}
						}
						bssldlist.add(bssld);
						wsajjbqkModel.setBssld(bssldlist);
					}

				}
				if(zjdtotal>0){
					if(zjdtotal==1){
						List<String> zjdlist=new ArrayList<String>();
						for(int i=end;i<zjdindex.length;i++){
							if(zjdindex[i]==1){
								zjdlist.add(ajjbqk.get(i));
							}
						}
						wsajjbqkModel.setZjd(zjdlist);
					}else{
						List<String> zjdlist=new ArrayList<String>();
						String zjd="";
						int prez=0;
						for(int i=end;i<zjdindex.length;i++){
							if(zjdindex[i]==1){
								zjd=ajjbqk.get(i);
								prez=i;
								break;
							}
						}
						for(int i=prez+1;i<ajjbqk.size();i++){
							if((zjdindex[i]==1)&&((i-prez)==1)){								
								zjd+=ajjbqk.get(i)+" ";
								prez=i;
							}else if(zjdindex[i]==1){
								zjdlist.add(zjd);
								zjd=ajjbqk.get(i)+" ";
								prez=i;
							}
						}
						zjdlist.add(zjd);
						wsajjbqkModel.setBszjd(zjdlist);
					}

				}
				//解析前审判决段
				if(end>0){
					String []qsdllast=ajjbqk.get(end-1).split("。");
					int qspjindex=-1;
					for(int i=qsdllast.length-1;i>=0;i--){
						if(qsdllast[i].contains("据此判决")||qsdllast[i].contains("据此裁定"))
						{	
							qspjindex=i;
							break;
						}
						if(qsdllast[i].contains("综上")||qsdllast[i].contains("根据")||qsdllast[i].contains("依照")||qsdllast[i].contains("依据")){
							int zs=qsdllast[i].indexOf("综上");
							int yz=qsdllast[i].indexOf("依照");
							int yj=qsdllast[i].indexOf("依据");
							int gj=qsdllast[i].indexOf("根据");
							int min=0;
							if(zs!=-1) min=zs;
							if(yz!=-1) min=min<yz?min:yz;
							if(yj!=-1) min=min<yj?min:yj;
							if(gj!=-1) min=min<gj?min:gj;
							if(qsdllast[i].indexOf("判决")>min||qsdllast[i].indexOf("裁定")>min
									){	
								qspjindex=i;
								break;
							}
						}
					}
					if(qspjindex==-1){
						for(int i=qsdllast.length-1;i>=0;i--){
							if((qsdllast[i].contains("判决")||qsdllast[i].contains("裁定"))){
								qspjindex=i;
								break;
							}
						}


					}
					int qspjdpre=end;
					if(qspjindex>-1){
						String qspjd="";
						for(int j=qspjindex;j<qsdllast.length;j++){
							qspjd+=qsdllast[j]+"。";
						}
						qspjdpre=end-1;
						wsajjbqkModel.setQspjd(qspjd);
					}
					//解析前审被告辩称段、原告诉称段、前审反诉诉称段
					List<Integer> exindex=new ArrayList<Integer>();
					String qsbgbcd="";
					for(int i=0;i<qspjdpre;i++){
						String ajnr=ajjbqk.get(i);
						if(ajnr.indexOf("辩称")>-1&&ajnr.indexOf("辩称")<30){
							qsbgbcd+=ajnr+" ";
							exindex.add(i);					
						}
					}  
					if(qsbgbcd!="")
						wsajjbqkModel.setQsbgbcd(qsbgbcd);
					String qsygscd="";
					for(int i=0;i<qspjdpre;i++){
						String ajnr=ajjbqk.get(i);
						List<String> nrtoken = FcUtil.getWholeToken(ajnr);
						int index=0;
						if(nrtoken.size()<20){
							index=nrtoken.size();
						}else{
							index=20;
						}
						for(int j=0;j<index;j++){
							String content=nrtoken.get(j);
							if(content.equals("诉讼")||content.equals("诉称")||content.equals("诉至")||
									content.equals("述称")||
									content.equals("诉讼请求")){
								qsygscd+=ajnr+" ";
								exindex.add(i);
								break;
							}
						}
					}  
					if(qsygscd!="")
						wsajjbqkModel.setQsygscd(qsygscd);
					String qsfsscd="";
					for(int i=0;i<qspjdpre;i++){
						String ajnr=ajjbqk.get(i);
						List<String> nrtoken = FcUtil.getWholeToken(ajnr);
						int index=0;
						if(nrtoken.size()<30){
							index=nrtoken.size();
						}else{
							index=30;
						}
						for(int j=0;j<index;j++){
							String content=nrtoken.get(j);
							if(content.equals("反诉")){
								qsfsscd+=ajnr+" ";
								exindex.add(i);
								break;
							}
						}
					} 
					if(qsfsscd!="") 					
						wsajjbqkModel.setQsfsscd(qsfsscd);
					//解析前审证据段
					List<String> qszjdlist=new ArrayList<String>();
					for(int i=0;i<qspjdpre;i++){
						String ajnr=ajjbqk.get(i);
						boolean isZjd=isZjd(ajnr);
						if(isZjd){
							qszjdlist.add(ajnr);
							exindex.add(i);
							break;
						}

					} 
					if(qszjdlist.size()>0) 					
						wsajjbqkModel.setQszjd(qszjdlist);
					//解析前审审理段
					String qssld="";
					List<String> qssldlist=new ArrayList<String>();
					for(int i=0;i<qspjdpre;i++){
						boolean b=true;
						for(int j=0;j<exindex.size();j++){
							if(i==exindex.get(j)){
								b=false;
								break;
							}

						}
						if(b){
							qssld+=ajjbqk.get(i)+" ";
						}else{
							if(!qssld.equals("")){
								qssldlist.add(qssld);
								qssld="";
							}
						}
					}
					for(int j=0;j<qspjindex;j++){
						qssld+=qsdllast[j]+"。";
					}
					if(!qssld.equals("")){
						qssldlist.add(qssld);
					}
					wsajjbqkModel.setQssld(qssldlist);
				}


			}else{
				String bsdl = "";
				for (int i = 0; i < ajjbqk.size() - 1; i++) {
					if(i!=ajjbqk.size()-1)
						bsdl += ajjbqk.get(i) + " ";
					else
						bsdl+=ajjbqk.get(i);
				}
				wsajjbqkModel.setBsdl(bsdl);

			}
		}
		return wsajjbqkModel;
	}

	public static boolean likeQsdl(String ajnr){
		if(ajnr.length()>20)
			ajnr=ajnr.substring(0,20);
		if(ajnr.contains("原审")||ajnr.contains("一审")||ajnr.contains("查明")||
				ajnr.contains("前审")||ajnr.contains("原判"))
			return true;
		return false;
	}
	public static boolean isQsdl(String ajnr){
		if(ajnr.length()>20)
			ajnr=ajnr.substring(0,20);
		if(ajnr.contains("向原审")||ajnr.contains("向一审")||
				ajnr.contains("向前审")||ajnr.contains("于原审"))
			return true;
		return false;
	}
	public boolean likeZjd(String content){
		boolean b=false;
		if(content.length()>=50)
			content=content.substring(content.length()-50,content.length());
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
				||content.contains("证据如下:")||content.contains("等证据")||content.contains("上述证据")
				){
			b=true;
		}
		return b;
	}
	public boolean isBssld(String result){
		if(result.length()>30)
			result=result.substring(0,30);
		int byrd=worddis("本院","认定",result);
		int byqr=worddis("本院","确认",result);
		if(result.contains("查明")||result.contains("审理")||result.contains("审查")
				||(byrd>0&&byrd<10)||(byrd>0&&byrd<10))
			return true;
		return false;
	}


}
