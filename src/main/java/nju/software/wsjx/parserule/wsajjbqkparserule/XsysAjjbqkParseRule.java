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
 * 刑事一审案件基本信息解析
 * @author wangzh
 *
 */
public class XsysAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule{
	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
		List<String> ajjbqk = wsAnalyse.getAjjbqk();
		WsajjbqkModel wsajjbqkModel = new WsajjbqkModel();
		if(ajjbqk.size()==1){
			String xsbssld=ajjbqk.get(0);
			wsajjbqkModel.setXsbssld(xsbssld);
		}else{
			int zkdlindex=-1;
			int index=0;
			//解析指控段落
			for(int i=0;i<ajjbqk.size();i++){
				String ajnr=ajjbqk.get(i);
				if(ajnr.length()>25)
					ajnr=ajnr.substring(0,25);
				if(ajnr.contains("指控")){
					zkdlindex=i;
					break;
				}
			}
			//解析本审审理段
			int bssldindex=-1;
			index=zkdlindex+1;
			for(int i=index;i<ajjbqk.size();i++){
				String ajnr=ajjbqk.get(i);
				if(issurplus(ajnr))
					continue;
				if(ajnr.length()>10)
					ajnr=ajnr.substring(0,10);
				if((ajnr.indexOf("查明")>0&&ajnr.indexOf("查明")<8)||ajnr.contains("本院认为")
						||ajnr.contains("庭审质证"))	{
					bssldindex=i;
					break;
				}
			}	
			if(bssldindex==-1){
				for(int i=ajjbqk.size()-1;i>index-1;i--){
					String ajnr=ajjbqk.get(i);
					if(ajnr.length()>25)
						ajnr=ajnr.substring(0,25);
					if((ajnr.indexOf("查明")>0&&ajnr.indexOf("查明")<8)||
							(ajnr.indexOf("上述事实")==0)||(ajnr.indexOf("以上事实")==0)||
							ajnr.contains("认定事实")||ajnr.contains("本院认为"))	{
						bssldindex=i;
						break;
					}
				}	
			}
			int end=-1;
			if(bssldindex==-1)
				end=ajjbqk.size();
			else
				end=bssldindex;
			int bhdlindex=-1;
			//解析辩护段落
			for(int i=index;i<end;i++){
				String ajnr=ajjbqk.get(i);
				if(isBhdl(ajnr)&&(!isZjd(ajnr))){
					bhdlindex=i;	
					break;
				}			
			}
			//解析附带民事诉讼请求段
			int fdmsssqqdindex=-1;
			for(int i=index;i<end;i++){
				String ajnr=ajjbqk.get(i);
				if(ajnr.length()>20)
					ajnr=ajnr.substring(0,20);
				if(ajnr.contains("附带民事")){
					fdmsssqqdindex=i;	
					break;
				}			
			}
			String zkdl="";
			String bhdl="";
			String bssld="";
			List<String>bssldlist=new ArrayList<String>();
			String fdmsssqqd="";
			int zkdlend=-1;
			int bhdlend=-1;
			if(zkdlindex>-1&&bhdlindex>-1&&bssldindex>-1&&fdmsssqqdindex>-1&&fdmsssqqdindex>bhdlindex){
				zkdlend=bhdlindex;
				bhdlend=fdmsssqqdindex;
				for(int i=zkdlindex;i<bhdlindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=bhdlindex;i<fdmsssqqdindex;i++)
					bhdl+=ajjbqk.get(i);
				for(int i=fdmsssqqdindex;i<bssldindex;i++)
					fdmsssqqd+=ajjbqk.get(i);
				for(int i=bssldindex;i<ajjbqk.size();i++){
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}else if(zkdlindex>-1&&bhdlindex>-1&&bssldindex>-1&&fdmsssqqdindex>-1&&fdmsssqqdindex<bhdlindex){
				zkdlend=fdmsssqqdindex;
				bhdlend=bssldindex;
				for(int i=zkdlindex;i<fdmsssqqdindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=fdmsssqqdindex;i<bhdlindex;i++)
					fdmsssqqd+=ajjbqk.get(i);
				for(int i=bhdlindex;i<bssldindex;i++)
					bhdl+=ajjbqk.get(i);
				for(int i=bssldindex;i<ajjbqk.size();i++)
				{
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}
			else if(zkdlindex>-1&&bhdlindex>-1&&bssldindex>-1&&fdmsssqqdindex==-1){
				zkdlend=bhdlindex;
				bhdlend=bssldindex;
				for(int i=zkdlindex;i<bhdlindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=bhdlindex;i<bssldindex;i++)
					bhdl+=ajjbqk.get(i);
				for(int i=bssldindex;i<ajjbqk.size();i++)
				{
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}else if(zkdlindex>-1&&bssldindex>-1&&fdmsssqqdindex>-1&&bhdlindex==-1){
				zkdlend=fdmsssqqdindex;
				for(int i=zkdlindex;i<fdmsssqqdindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=fdmsssqqdindex;i<bssldindex;i++)
					fdmsssqqd+=ajjbqk.get(i);
				for(int i=bssldindex;i<ajjbqk.size();i++)
				{
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}else if(zkdlindex>-1&&bssldindex>-1&&fdmsssqqdindex==-1&&bhdlindex==-1){
				zkdlend=bssldindex;
				for(int i=zkdlindex;i<bssldindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=bssldindex;i<ajjbqk.size();i++)
				{
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}else if(zkdlindex==-1&&bssldindex>-1&&fdmsssqqdindex==-1&&bhdlindex==-1){
				for(int i=bssldindex;i<ajjbqk.size();i++)
				{
					bssld+=ajjbqk.get(i);	
					bssldlist.add(ajjbqk.get(i));
				}
			}else if(zkdlindex>-1&&bssldindex==-1&&fdmsssqqdindex==-1&&bhdlindex>-1){
				zkdlend=bhdlindex;
				bhdlend=ajjbqk.size();
				for(int i=zkdlindex;i<bhdlindex;i++)
					zkdl+=ajjbqk.get(i);
				for(int i=bhdlindex;i<ajjbqk.size();i++)
					bhdl+=ajjbqk.get(i);
			}
			//解析指控事实、指控证据、指控意见
			if(zkdl!=""){
				wsajjbqkModel.setZkdl(zkdl);
				String zkss="";
				String zkzj="";
				String zkyj="";
				int zkzjindex=-1;
				int zkyjindex=-1;
				//第一次查找指控证据
				for(int i=zkdlindex+1;i<zkdlend;i++){
					String ajnr=ajjbqk.get(i);
					if(isZjd(ajnr)||likeZjd(ajnr)){
						zkzjindex=i;
						break;
					}
				}
				//第一次查找指控意见
				for(int i=zkdlend-1;i>zkdlindex-1;i--){
					String ajnr=ajjbqk.get(i);
					int zjxszr=worddis("追究","刑事责任",ajnr);
					if(ajnr.contains("《")||ajnr.contains("依法判处")||ajnr.contains("提请本院")
							||ajnr.contains("公诉机关认为")||ajnr.contains("诉请")||ajnr.contains("据此认为")
							||(zjxszr>-1&&zjxszr<10)||ajnr.contains("指控认为")){
						zkyjindex=i;
						break;
					}
				}
				int zkzjindex_2=-1;
				//第二次查找指控证据
				if(zkzjindex==-1){
					String[] zksslist=ajjbqk.get(zkdlindex).split("。");
					for(int i=0;i<zksslist.length;i++){
						String ajnr=zksslist[i];
						if(isZjd(ajnr)||likeZjd(ajnr)){
							zkzjindex_2=i;
							break;
						}
					}
					if(zkzjindex_2>-1&&zkyjindex>zkdlindex){
						for(int i=0;i<zkzjindex_2;i++)
							zkss+=zksslist[i]+"。";
						for(int i=zkzjindex_2;i<zksslist.length;i++)
							zkzj+=zksslist[i]+"。";
						for(int i=zkyjindex;i<zkdlend;i++)
							zkyj+=ajjbqk.get(i);
					}else if(zkzjindex_2>-1&&zkyjindex==zkdlindex){
						String zkzjyj="";
						for(int i=0;i<zkzjindex_2;i++)
							zkss+=zksslist[i]+"。";
						for(int i=zkzjindex_2;i<zksslist.length;i++)
							zkzjyj+=zksslist[i]+"。";
						int rw=zkzjyj.indexOf("认为");
						if(rw==-1)
							rw=zkzjyj.indexOf("认定");
						if(rw==-1)
							rw=zkzjyj.indexOf("诉请");
						if(rw>-1){
							String temp=zkzjyj.substring(0,rw);
							int jh=temp.lastIndexOf("。");
							int dh=temp.lastIndexOf("，");
							if(jh>dh&&jh>0)
								rw=jh+1;
							else if(jh<dh&&dh>0)
								rw=dh+1;
							zkzj=zkzjyj.substring(0,rw);
							zkyj=zkzjyj.substring(rw,zkzjyj.length());
						}else{
							zkyj=zkzjyj;
						}
					}
					else if(zkzjindex_2==-1&&zkyjindex>zkdlindex){
						for(int i=zkdlindex;i<zkyjindex;i++)
							zkss+=ajjbqk.get(i);
						for(int i=zkyjindex;i<zkdlend;i++)
							zkyj+=ajjbqk.get(i);
					}else if(zkzjindex_2==-1&&zkyjindex==zkdlindex){
						String zkssyj=ajjbqk.get(zkdlindex);
						int rw=ajjbqk.get(zkdlindex).lastIndexOf("认为");
						if(rw==-1)
							rw=zkssyj.indexOf("认定");
						if(rw==-1)
							rw=zkssyj.indexOf("诉请");
						if(rw>-1){
							String temp=zkssyj.substring(0,rw);
							int jh=temp.lastIndexOf("。");
							int dh=temp.lastIndexOf("，");
							if(jh>dh&&jh>0)
								rw=jh+1;
							else if(jh<dh&&dh>0)
								rw=dh+1;
							zkss=zkssyj.substring(0,rw);
							zkyj=zkssyj.substring(rw,zkssyj.length());	
						}else{
							zkss=ajjbqk.get(zkdlindex);
						}
					}
					else{
						for(int i=zkdlindex;i<zkdlend;i++)
							zkss+=ajjbqk.get(i);
					}
				}else{
					if(zkyjindex>-1){
						if(zkyjindex>zkzjindex){
							for(int i=0;i<zkzjindex;i++)
								zkss+=ajjbqk.get(i);
							for(int i=zkzjindex;i<zkyjindex;i++)
								zkzj+=ajjbqk.get(i);
							for(int i=zkyjindex;i<zkdlend;i++)
								zkyj+=ajjbqk.get(i);
						}else if(zkyjindex==zkzjindex){
							for(int i=zkdlindex;i<zkzjindex;i++)
								zkss+=ajjbqk.get(i);
							String zkzjyj=ajjbqk.get(zkyjindex);
							int rw=zkzjyj.indexOf("认为");
							if(rw==-1)
								rw=zkzjyj.indexOf("认定");
							if(rw==-1)
								rw=zkzjyj.indexOf("诉请");
							if(rw>-1){
								String temp=zkzjyj.substring(0,rw);
								int jh=temp.lastIndexOf("。");
								int dh=temp.lastIndexOf("，");
								if(jh>dh&&jh>0)
									rw=jh+1;
								else if(jh<dh&&dh>0)
									rw=dh+1;
								zkzj=zkzjyj.substring(0,rw);
								zkyj=zkzjyj.substring(rw,zkzjyj.length());
							}else{
								zkyj=ajjbqk.get(zkyjindex);
							}

						}else if(zkyjindex<zkzjindex){
							for(int i=0;i<zkyjindex;i++)
								zkss+=ajjbqk.get(i);
							for(int i=zkyjindex;i<zkzjindex;i++)
								zkyj+=ajjbqk.get(i);
							for(int i=zkzjindex;i<zkdlend;i++)
								zkzj+=ajjbqk.get(i);
						}
					}else{
						for(int i=0;i<zkzjindex;i++)
							zkss+=ajjbqk.get(i);
						for(int i=zkzjindex;i<zkdlend;i++)
							zkzj+=ajjbqk.get(i);
					}
				}
				if(zkss!="")
					wsajjbqkModel.setZkss(zkss);
				if(zkzj!="")
					wsajjbqkModel.setZkzj(zkzj);
				if(zkyj!="")
					wsajjbqkModel.setZkyj(zkyj);
			}
			//解析被告人辩称和辩护人辩护
			if(bhdl!=""){
				wsajjbqkModel.setBhdl(bhdl);
				List<String>bgrbclist=new ArrayList<String>();
				List<String>bhrbhlist=new ArrayList<String>();
				for(int i=bhdlindex;i<bhdlend;i++){
					String ajnr=ajjbqk.get(i);
					if(ajnr.contains("辩护")){
						bhrbhlist.add(ajnr);
					}else{
						bgrbclist.add(ajnr);
					}
				}
				if(bgrbclist!=null)
					wsajjbqkModel.setBgrbc(bgrbclist);
				if(bhrbhlist!=null)
					wsajjbqkModel.setBhrbh(bhrbhlist);
			}
			if(fdmsssqqd!="")
				wsajjbqkModel.setFdmsssqqd(fdmsssqqd);
			if(bssld!=""){
				wsajjbqkModel.setXsbssld(bssld);
				wsajjbqkModel.setBssld(bssldlist);
			}
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
				||content.contains("证据如下:")||content.contains("证据有：")
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
