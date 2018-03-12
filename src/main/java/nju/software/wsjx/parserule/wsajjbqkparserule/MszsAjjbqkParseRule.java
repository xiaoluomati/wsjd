package nju.software.wsjx.parserule.wsajjbqkparserule;

import java.util.ArrayList;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.FcUtil;

/**
 * 民事再审案件基本信息解析
 * @author zhouwei
 *
 */
public class MszsAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule{
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> ajjbqk = wsAnalyse.getAjjbqk();
		//解析前一审段落、前审段落和本审段落
		WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();

		if(ajjbqk==null || ajjbqk.size()==0){
			return wsajjbqkModel;
		}
		if(ajjbqk.size()==1){
			wsajjbqkModel.setBsdl(ajjbqk.get(0));
			List<String> bssldlist=new ArrayList<String>();
			bssldlist.add(ajjbqk.get(0));
			wsajjbqkModel.setBssld(bssldlist);
		}else{
			String ajjbqk_1=ajjbqk.get(0);
			int qsindex=0;
			List<String> tokens = FcUtil.getWholeToken(ajjbqk_1);
			int pre=-1;
			//前一审段落
			int end=-1;
			//前审段落
			int end1=-1;
			//再审段落
			int end2=-1;
			int index1=0;
			if(tokens.size()<20){
				index1=tokens.size();
			}else{
				index1=20;
			}
			for(int i=0;i<index1;i++){
				String content=tokens.get(i);
				if(content.equals("原审")||content.equals("前审")||content.equals("查明")||content.equals("一审")
						||content.equals("诉称")||content.equals("原判")||content.equals("抗诉")||content.equals("再审")){				  
					pre=0;
					break;
				}
			}
			if(pre==0){  
				for(int i=1;i<ajjbqk.size();i++){
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
						if(content.equals("起诉")){
							end=i;
							break;
						}
						if(content.equals("不服")||content.equals("上诉")||content.equals("上诉人")||
								(ajnr.indexOf("上诉称")>-1&&ajnr.indexOf("上诉称")<30)){
							end1=i;
							break;
						}
						if((content.equals("再审"))){
							if(end2==-1||end>end2||end1>end2){
								end2=i;
								break;
							}
						}
					}
				}  
				if(end==-1) end=0;
				if(end1==-1) end1=0;
				if(end2==-1) end2=ajjbqk.size()-1;
				if(end>end1){ 
					end1=end;
				}
				//解析前一审段落
				String qysdl="";
				for (int i = end; i <end1; i++) {
					if(i!=end1-1)			
						qysdl += ajjbqk.get(i) + " ";
					else
						qysdl += ajjbqk.get(i);
				}
				if(qysdl!=""){
					wsajjbqkModel.setQysdl(qysdl);	
				}
				//解析前审段落
				String qsdl="";
				for (int i = end1; i <end2; i++) {
					if(i!=end2-1)			
						qsdl += ajjbqk.get(i) + " ";
					else
						qsdl += ajjbqk.get(i);
				}
				if(qsdl!=""){
					wsajjbqkModel.setQsdl(qsdl);
				}
				//解析本审段落
				String bsdl = "";
				for (int i = 0; i < end ; i++) {
					bsdl += ajjbqk.get(i) + " ";
				}
				for (int i = end2; i < ajjbqk.size() ; i++) {
					if(i!=ajjbqk.size()-1)
						bsdl += ajjbqk.get(i) + " ";
					else
						bsdl += ajjbqk.get(i) ;
				}
				if(bsdl!=""){
					wsajjbqkModel.setBsdl(bsdl);
				}
				//解析本审被申诉人辩称段
				List<Integer> bsindex=new ArrayList<Integer>();
				List<String> bssrbcdlist=new ArrayList<String>();
				for(int i=0;i<end;i++){
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
						if(bcajnr.contains("辩称")||bcajnr.contains("答辩")){
							bssrbcdlist.add(ajnr);
							bsindex.add(i);					
						}
					}
				}  
				for(int i=end2;i<ajjbqk.size();i++){
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
						if(bcajnr.contains("辩称")||bcajnr.contains("答辩")){
							bssrbcdlist.add(ajnr);
							bsindex.add(i);					
						}
					}
				} 
				if(bssrbcdlist.size()>0)
					wsajjbqkModel.setBssrbcd(bssrbcdlist);
				//解析本审申诉段
				String ssrscd="";
				for(int i=0;i<end;i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
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
						boolean ssr=true;
						for(int j=0;j<index;j++){
							String content=nrtoken.get(j);
							if(content.equals("审理")||content.equals("查明")){
								ssr=false;
								break;
							}
						}
						if(ssr){
							for(int j=0;j<index;j++){
								String content=nrtoken.get(j);
								if(content.equals("称")||content.equals("诉称")||content.equals("申诉")){
									ssrscd+=ajnr+" ";
									bsindex.add(i);
									break;
								}
							}
						}

					}

				}
				for(int i=end2;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
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
						boolean ssr=true;
						for(int j=0;j<index;j++){
							String content=nrtoken.get(j);
							if(content.equals("审理")||content.equals("查明")){
								ssr=false;
								break;
							}
						}
						if(ssr){
							for(int j=0;j<index;j++){
								String content=nrtoken.get(j);
								if(content.equals("称")||content.equals("诉称")||content.equals("申诉")){
									ssrscd+=ajnr+" ";
									bsindex.add(i);
									break;
								}
							}
						}

					}

				}
				if(ssrscd!="")
				wsajjbqkModel.setSsrscd(ssrscd);
				//解析本审审理段
				String bssld="";
				List<String> bssldlist=new ArrayList<String>();
				for(int i=0;i<end;i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						bssld=ajjbqk.get(i);
						if(!bssld.equals("")){
							bssldlist.add(bssld);
							bssld="";
						}
					}
				}
				for(int i=end2;i<ajjbqk.size();i++){
					boolean b=true;
					for(int j=0;j<bsindex.size();j++){
						if(i==bsindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						bssld=ajjbqk.get(i);
						if(!bssld.equals("")){
							bssldlist.add(bssld);
							bssld="";
						}
					}
				}
				wsajjbqkModel.setBssld(bssldlist);
				//解析前审判决段
				String []qsdllast=ajjbqk.get(end2-1).split("。");
				int qspjindex=-1;
				for(int i=0;i<qsdllast.length;i++){
					if(qsdllast[i].contains("综上")||qsdllast[i].contains("依照")||qsdllast[i].contains("依据")){
						int zs=qsdllast[i].indexOf("综上");
						int yz=qsdllast[i].indexOf("依照");
						int yj=qsdllast[i].indexOf("依据");
						int min=0;
						if(zs!=-1) min=zs;
						if(yz!=-1) min=min<yz?min:yz;
						if(yj!=-1) min=min<yj?min:yj;
						if(qsdllast[i].indexOf("判决")>min||qsdllast[i].indexOf("裁定")>min){	
							qspjindex=i;
							break;
						}
					}
				}
				if(qspjindex==-1){
					for(int i=0;i<qsdllast.length;i++){
						if((qsdllast[i].contains("判决")||qsdllast[i].contains("裁定"))){
							qspjindex=i;
							break;
						}
					}
				}
				int qspjdpre=end2;
				if(qspjindex>-1){
					String qspjd="";
					for(int j=qspjindex;j<qsdllast.length;j++){
						qspjd+=qsdllast[j]+"。";
					}
					qspjdpre=end2-1;
					wsajjbqkModel.setQspjd(qspjd);
				}
				//解析前审被告辩称段、原告诉称段
				List<Integer> exindex=new ArrayList<Integer>();
				List<Integer> qsdlindex=new ArrayList<Integer>();
				String qsbgbcd="";
				for(int i=end1;i<qspjdpre;i++){
					String ajnr=ajjbqk.get(i);
						if(ajnr.indexOf("辩称")>-1&&ajnr.indexOf("辩称")<30){
							qsbgbcd+=ajnr+" ";
							exindex.add(i);					
						}
				}
				for(int i=0;i<qspjindex;i++){
					if(qsdllast[i].indexOf("辩称")>-1&&qsdllast[i].indexOf("辩称")<30){
						qsbgbcd+=qsdllast[i]+"。";
						qsdlindex.add(i);	
					}
				}
				if(qsbgbcd!=""){
					wsajjbqkModel.setQsbgbcd(qsbgbcd);
				}
				String qsygscd="";
				for(int i=end1;i<qspjdpre;i++){
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
						if(content.equals("上诉请求")||content.equals("诉讼")||content.equals("诉称")||content.equals("诉至")||
								content.equals("诉讼请求")||content.equals("请求")){
							qsygscd+=ajnr+" ";
							exindex.add(i);
							break;
						}
					}
				}  
				for(int i=0;i<qspjindex;i++){
					if(qsdllast[i].contains("上诉请求")||qsdllast[i].contains("诉讼")||qsdllast[i].contains("诉称")||qsdllast[i].contains("诉至")||
							qsdllast[i].contains("诉讼请求")||qsdllast[i].contains("请求")){
						qsygscd+=qsdllast[i]+"。";
						qsdlindex.add(i);
					}
				}
				if(qsygscd!=""){
					wsajjbqkModel.setQsygscd(qsygscd);
				}
				//解析前审审理段
				String qssld="";
				List<String> qssldlist=new ArrayList<String>();
				for(int i=end1;i<qspjdpre;i++){
					boolean b=true;
					for(int j=0;j<exindex.size();j++){
						if(i==exindex.get(j)){
							b=false;
							break;
						}

					}
					if(b){
						qssld=ajjbqk.get(i);
						if(!qssld.equals("")){
							qssldlist.add(qssld);
							qssld="";
						}
					}
				}
				for(int i=0;i<qspjindex;i++){
					boolean b=true;
					for(int j=0;j<qsdlindex.size();j++){
						if(i==qsdlindex.get(j)){
							b=false;
							break;
						}
					}
					if(b){
						qssld+=qsdllast[i]+"。";
					}
				}
				if(!qssld.equals("")){
					qssldlist.add(qssld);
				}
				wsajjbqkModel.setQssld(qssldlist);
				//解析前一审判决段
				if(end1>0){
					String []qysdllast=ajjbqk.get(end1-1).split("。");
					int qyspjindex=-1;
					for(int i=0;i<qysdllast.length;i++){
						if(qysdllast[i].contains("综上")||qysdllast[i].contains("依照")||qysdllast[i].contains("依据")){
							int zs=qysdllast[i].indexOf("综上");
							int yz=qysdllast[i].indexOf("依照");
							int yj=qysdllast[i].indexOf("依据");
							int min=0;
							if(zs!=-1) min=zs;
							if(yz!=-1) min=min<yz?min:yz;
							if(yj!=-1) min=min<yj?min:yj;
							if(qysdllast[i].indexOf("判决")>min||qysdllast[i].indexOf("裁定")>min){	
								qyspjindex=i;
								break;
							}
						}
					}
					if(qyspjindex==-1){
						for(int i=0;i<qysdllast.length;i++){
							if((qysdllast[i].contains("判决")||qysdllast[i].contains("裁定"))){
								qyspjindex=i;
								break;
							}
						}
					}
					if(qyspjindex>-1){
						String qyspjd="";
						for(int j=qyspjindex;j<qysdllast.length;j++){
							qyspjd+=qysdllast[j]+"。";
						}
						wsajjbqkModel.setQyspjd(qyspjd);
					}	
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
}
