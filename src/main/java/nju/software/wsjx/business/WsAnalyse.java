package nju.software.wsjx.business;

import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.Enum.HeadExceptionEnum;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.POIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class WsAnalyse extends AbstractAnalyseWs {

	private String wswjm;//文书文件名

	private String wsnr;

	List<String> wsnrList;

	/**
	 * 若某一子项为空，定义为空
	 */
	private List<String> ws;

	private List<String> sscyr;
	
	private String ssjl;

	private List<String> ajjbqk;

	private List<String> cpfxgc;

	private List<String> cpjg;

	private List<String> ww;

	private List<String> fl;
	
	private boolean hasAjjbqk=true;

	/**
	 * end为该段结束,上一段解析不出来，有可能end为-1，解析从end+1
	 */
	private int end = -1;
	private int ajjbqkend=0;
	private int ajjbqkpre=0;

	public WsAnalyse(String wswjm, String wsnr) {
		super();
		this.wswjm = wswjm;
		this.wsnr = wsnr;
		csh();
	}

	public WsAnalyse(byte[] wsByte, String wswjm) {
		super();
		this.wsnr = POIUtil.getWqcGString(wsByte, wswjm);
		this.wswjm = wsnr;
		csh();
	}

	private void csh() {
		init();
		if(wsnrList==null||wsnrList.size()==0)
			return;
		hfWs();//划分文首
		hfSscyr();//划分诉讼参与人
		hfSsjl();//划分诉讼记录                              
		hfCpfxgc();//划分裁判分析过程
		hfCpjg();//划分裁判结果
		hfAjjbqk();//划分案件基本信息			
		hfWw();//划分文尾
		hfFl();//划分附录
	}

	@Override
	public void init() {
//		if (wsnr != null) {
//			//System.out.println(wsnr);
//			wsnr.replaceAll("\r", "");
//			/**
//			 * 去除全角半角空格
//			 */
//			wsnr.replaceAll("　", "");
//			wsnr.replaceAll(" ", "");
//			String[] qfsj = wsnr.split("\n");
//			wsnrList = new ArrayList<String>();
//			// 过滤数据把空格一行去掉
//			for (int i = 0; i < qfsj.length; i++) {
//				if (!qfsj[i].equals("")) {
//					String str = "";
//					for (int j = 0; j < qfsj[i].length(); j++) {
//						if (qfsj[i].charAt(j) != ' ')
//							str = str + qfsj[i].charAt(j);
//					}
//					wsnrList.add(str);
//				}
//			}
//		}
		if (wsnr != null) {
			//System.out.println(wsnr);
			wsnr=wsnr.replaceAll("[\\r]", "");
			wsnr=wsnr.replaceAll("[\\t]", "");
		    /**
			 * 去除全角半角空格
			 */
			wsnr=wsnr.replaceAll("　", "");
			wsnr=wsnr.replaceAll(" ", "");
			String[] qfsj = wsnr.split("\n");
			
			wsnrList = new ArrayList<String>();
			
			String rexExp="[.。:：;；?？)）\"”]";
			String rexExpcom = "[,，]";
			Pattern pattern=Pattern.compile(rexExp);
			Pattern patternCom=Pattern.compile(rexExpcom);
			
			String headExp="[院|书|号]";
			Pattern pattern_ws=Pattern.compile(headExp);
			StringBuffer paragraph=new StringBuffer();
			int wsEnd=0;
			
			for (int i = 0; i < qfsj.length; i++) {
				if (!qfsj[i].equals("")) {
					if (HeadEnum.HasHead(qfsj[i])) {
						wsEnd = i ;
						break;
					}
				}
					
			}
			/**
			 * 找到第一个文尾敏感词
			 */
			int index=0;
			for (int i = 0; i < qfsj.length; i++) {
				if (!qfsj[i].equals("")) {
					if(isCpjgEnd(qfsj[i])){
						index=i;
						break;
					}
				}
					
			}
			// 过滤数据把空格一行去掉并且将如果这段最后一个字不是标点符号自动默认跟下一行为一段
			for (int i = 0; i < qfsj.length; i++) {
				if (!qfsj[i].equals("")) {
					String str = qfsj[i];

					paragraph.append(str);
					if(!str.equals("")){
	
						//截取最后一个字符判断
						String temp=str.substring(str.length()-1);
						Matcher matcher=pattern.matcher(temp);
						Matcher matcherCom=pattern.matcher(temp);
						/*Matcher wsMarcher=pattern_ws.matcher(temp);*/
						/**
						 * 如果他最后一个字符合文首正则，则默认他可以无标点换行
						 * 且他如果包含文尾敏感词眼则认为他也可以无标点换行
						 */
						
						//如果存在句号等结尾，判断下一段是否含有exceptionEnum，有的话加进来
						if(matcher.find()||(i<wsEnd)||i>=index){
							if(i < qfsj.length-1 && HeadExceptionEnum.HasHead(qfsj[i + 1])){
								paragraph.append(qfsj[++i]);
								if(i < qfsj.length-1 && HeadExceptionEnum.HasHead(qfsj[i + 1])){
									paragraph.append(qfsj[++i]);
								}
							}
							//把这段加进来并且清空paragraph
							wsnrList.add(paragraph.toString());
						    paragraph.delete(0, paragraph.length());
						}
						//如果没有句号等结尾，判断下一段有没有headEnum开头
						else if(!matcher.find()){
							//如果下一段符合开头符合HeadEnum							
							if(i < qfsj.length-1 && HeadEnum.HasHead(qfsj[i + 1])){
								wsnrList.add(paragraph.toString());
							    paragraph.delete(0, paragraph.length());
							}
						}
					}
					
					
				}
			}
			wsnrList.add(paragraph.toString());
			/*for(String string:qfsj){
			System.out.println(string);
			System.out.println("=====");
			}*/
		}
	}

	@Override
	public void hfWs() {
		FcUtil fcUtil =new FcUtil();
		int index=0;
		if(wsnrList.size()<10)
			index=wsnrList.size();
		else
			index=10;
		for (int i = 0; i < index; i++) {
			if (HeadEnum.HasHead(wsnrList.get(i))) {
				end = i - 1;
				break;
			}
		}
		// 当end
		if (end >= 0) {
			ws = new ArrayList<String>();
		}
		String find = "法院|书|号";
		Pattern p = Pattern.compile(find);
		for (int i = 0; i < wsnrList.size() && i < end + 1; i++) {
			Matcher matcher = p.matcher(wsnrList.get(i));
			if(matcher.find()&&fcUtil.getWholeToken(wsnrList.get(i)).size()<15){
				ws.add(wsnrList.get(i));
			}

		}
	}

	@Override
	public void hfSscyr() {
		int pre = ++end;
		for (int i = end; i < wsnrList.size(); i++) {
			if(wsnrList.get(i).contains("一案")){
				end=i;
				break;
			}
		//	System.out.println(wsnrList.get(i));
			String sj = getFirstContent(wsnrList.get(i));
			
			if (isNotSscyr(sj)) {
				end = i;
				break;
			}
			if (likeSscyr(sj)) {
				continue;
			} else {
				if (i + 1 < wsnrList.size()) {
					sj = getFirstContent(wsnrList.get(i + 1));					
					if (isNotSscyr(sj)) {
						end = i;
						break;
					}
					if (!likeSscyr(sj)) {
						end = i;
						break;
					}
				}
			}
		}
		if (end > pre) {
			sscyr = new ArrayList<String>();

		}
		end--;
		for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
			sscyr.add(wsnrList.get(i));
		}
	//	System.out.println(sscyr);
	}

	/**
	 * 获取首句截取的内容
	 * 
	 * @param str
	 * @return
	 */
	public static  String getContent(String str) {
		String temp=deBracket(str);
		int dh = (temp.indexOf("，")!=-1?temp.indexOf("，"):temp.indexOf(","));
		int jh = (temp.indexOf("。")!=-1?temp.indexOf("。"):temp.indexOf("."));
		int max = 0;
		if (dh == -1) {
			max = jh;
		} else if (jh == -1) {
			max = dh;
		} else {
			max = jh > dh ? dh : jh;
		}
		String content = "";
		try {
			content = temp.substring(0, max);
		} catch (Exception e) {
		}
		return content;
	}
	/**
	 * 解析诉讼参与人时，获取首句内容
	 * 如果没有逗号或者句号则返回原字符串
	 * @param str
	 * @return
	 */
	public static  String getFirstContent(String str) {
		String temp=deBracket(str);
		int dh = (temp.indexOf("，")!=-1?temp.indexOf("，"):temp.indexOf(","));
		int jh = (temp.indexOf("。")!=-1?temp.indexOf("。"):temp.indexOf("."));
		int max = 0;
		if (dh == -1) {
			max = jh;
		} else if (jh == -1) {
			max = dh;
		} else {
			max = jh > dh ? dh : jh;
		}
		String content = "";
		try {
			content = temp.substring(0, max);
		} catch (Exception e) {
			return str;
		}
		return content;
	}
	
	/**
	 * 去掉括号的内容
	 * 
	 * @param content
	 * @return
	 */
	public static  String  deBracket(String content) {
		int count=20;
		//存在括号左边为全角右边为半角情况
		if(content.indexOf("（") != -1)
			content = content.replace('（','(');		
		if(content.indexOf("）") != -1)
			content = content.replace('）',')');
		while (((content.indexOf("（") != -1 && content.indexOf("）") != -1)
				|| (content.indexOf("(") != -1 && content.indexOf(")") != -1))&&count>0) {
			count--;
			int left = content.indexOf("（");
			int right = content.indexOf("）");
			if (left != -1) {
				if (right != -1&&left<right) {
					//System.out.println(content);
					content = content.substring(0, left)
							+ content
							.substring(right + 1, content.length());
				}
			}
			left = content.indexOf("(");
			right = content.indexOf(")");
			if (left != -1) {
				if (right != -1&&left<right) {
					content = content.substring(0, left)
							+ content
							.substring(right + 1, content.length());
				}
			}
		}
		return content;
	}
	/**
	 * 取得括号中的内容,不存在则返回null
	 * @param content
	 * @return
	 */
	public static  String  takeBracket(String content) {
		if ((content.indexOf("（") != -1 && content.indexOf("）") != -1)
				|| (content.indexOf("(") != -1 && content.indexOf(")") != -1)) {
			int left = content.indexOf("（");
			int right = content.indexOf("）");
			if (left != -1) {
				if (right != -1) {
					if (left+1<right)
						content = content.substring(left+1,right);
				}
			}
			left = content.indexOf("(");
			right = content.indexOf(")");
			if (left != -1) {
				if (right != -1) {
					content = content.substring(left+1, right);
				}
			}
		}else{
			content=null;
		}
		return content;
	}
	/**
	 * 获取截取的内容
	 * 
	 * @param str
	 * @return
	 */
	public static  ArrayList<String> getWholeContent(String str) {
		ArrayList<String> contentlist= new ArrayList<String>();
		String[] jhsplit=str.split("[。.]");
		for(int i=0;i<jhsplit.length;i++){
			String content=jhsplit[i];
			String []dhsplit=content.split("[，,]");
			for(int j=0;j<dhsplit.length;j++){
				String dhcontent=dhsplit[j];
				String []fhsplit=dhcontent.split("[；;]");
				for(int k=0;k<fhsplit.length;k++){
					if(fhsplit[k].length()>0){
						contentlist.add(fhsplit[k]);
					}
				}
			}
		}
		return contentlist;
	}
	/**
	 * 根据首句的分词来判断，小于10个分词的有可能是诉讼参与人
	 * 
	 * @param words
	 * @return
	 */
	public boolean likeSscyr(String words) {
		List<String> list = FcUtil.getWholeToken(words);
		return list.size() >= 15 ? false : true;
	}

	/**
	 * 判断肯定不是诉讼参与人
	 * 
	 * @param words
	 * @return
	 */
	private boolean isNotSscyr(String words) {
		/**
		 * 没有诉讼地位的敏感词 有诉称 辨称 一案 不服 纠纷 或者同时出现原被告 分词大于15 或者有本院 有认为 有认定 的都不可能是诉讼参与人
		 */
		if (words.contains("诉称") || words.contains("辨称")
				|| words.contains("一案") || words.contains("不服")
				|| words.contains("纠纷")||words.contains("起诉书")
				|| (words.contains("原告") && words.contains("被告"))
				|| !HeadEnum.HasHead(words) 
				|| words.contains("本院") || words.contains("认为")
				|| words.contains("认定"))

			return true;
		return false;
	}
	public void hfSsjl() {
		int pre=++end;
		ajjbqkpre=pre;
		ssjl=wsnrList.get(pre);
	//	System.out.println(ssjl);
	}
	@Override
	public void hfAjjbqk() {
		ajjbqkpre++;
		if (ajjbqkpre < ajjbqkend) {
			ajjbqk = new ArrayList<String>();
		}
		for (int i = ajjbqkpre; i <ajjbqkend && i < wsnrList.size(); i++) {
			ajjbqk.add(wsnrList.get(i));
		}
	}

	/**
	 * 判断肯定不属于案件基本情况
	 * 
	 * @param word
	 * @return
	 */
	private boolean isNotAjjjbqk(String word) {//这个方法没有被用到 
		if (word.contains("裁定如下") || word.contains("判决如下")
				|| word.contains("决定如下") || word.contains("本院认为")
				|| word.contains("本院经审查认为")||word.contains("主持调解") )
//		if (word.contains(" 本院认为") || word.contains(" 本院经审查认为"))
			return true;
		if(word.contains("达成协议如下"))
			return true;
		return false;
	}
	private boolean isCpfxgc_1(String word) {
		if ( word.indexOf("本院认为")==0|| word.indexOf("本院经审理认为")==0
				|| word.indexOf("本院经审查认为")==0 ||word.contains("主持调解")||word.indexOf("本院再审认为")==0 )
			return true;
		if(word.contains("达成协议如下")||word.contentEquals("达成如下协议")||word.contains("达成以下协议"))
			return true;
		Pattern pattern = Pattern.compile("((依据)|(依照)|(根据)).*((裁定如下)|(判决如下))");
		if(pattern.matcher(word).find())
		//	if(word.contains("依照")&&word.contains("裁定如下"))
			return true;
			
		return false;
	}
	private boolean isCpfxgc_2(String word) {
		if ( word.contains("本院认为")|| word.contains("本院经审理认为")
				|| word.contains("本院经审查认为")||word.contains("主持调解") ||word.indexOf("本院再审认为")==0)
			return true;
		if(word.contains("达成协议如下")||word.contentEquals("达成如下协议")||word.contains("达成以下协议"))
			return true;
		if(word.contains("依照")&&word.contains("裁定如下"))
			return true;
		return false;
	}
	@Override
	public void hfCpfxgc() {
		int pre = 0;
		boolean b=false;
		ajjbqkend=end;
		for(int i=wsnrList.size()-1;i>=end;i--){
	//		System.out.println(wsnrList.get(i));
			if(isCpfxgc_1(wsnrList.get(i))){				
				pre = i;
				ajjbqkend=i;
				b=true;				
				break;
			}
		}
		
		if(isCpfxgc_1(wsnrList.get(ajjbqkend-1))){
			pre = ajjbqkend -1;
			ajjbqkend = ajjbqkend - 1;
			b = true;
		}
		if(b==false){
			for(int i=wsnrList.size()-1;i>=end;i--){
				if(isCpfxgc_2(wsnrList.get(i))&&!isCpfxgc_2(wsnrList.get(i-1))){
					pre = i;
					ajjbqkend=i;
					b=true;
					break;
				}
			}
			if(isCpfxgc_2(wsnrList.get(ajjbqkend-1))){
				pre = ajjbqkend -1;
				ajjbqkend = ajjbqkend - 1;
				b = true;
			}
		}
		if(ajjbqkend!=0){
			for (int i = pre; i < wsnrList.size(); i++) {
				if (isCpfxgcEnd(wsnrList.get(i))) {
					end = i+1;
					break;
				}
			}
			if (end > pre) {
				cpfxgc=new ArrayList<String>();
			}
			end--;
			for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
				cpfxgc.add(wsnrList.get(i));
			}
		}
	//	System.out.println(cpfxgc);
	}

	private boolean isCpfxgcEnd(String word) {
		if (word.contains("裁定如下") || word.contains("判决如下")
				|| word.contains("决定如下")||word.contains("达成以下")||word.contains("达成如下")||word.contains("自愿签署"))
			return true;
		return false;
	}

	@Override
	public void hfCpjg() {
		if(ajjbqkend!=0){
			int pre = ++end;
			for (int i = end; i < wsnrList.size(); i++) {
				if (isCpjgEnd(wsnrList.get(i))) {
					end = i;
					break;
				}
			}
			if (end > pre) {
				cpjg=new ArrayList<String>();
			}
			end--;
			for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
				cpjg.add(wsnrList.get(i));
			}
		}else{
			int pre=0;
			for(int i=wsnrList.size()-1;i>=end;i--){
				if(isCpfxgcEnd(wsnrList.get(i))){
					pre=i+1;
					ajjbqkend=i;
					break;
				}
			}
			for (int i = pre; i < wsnrList.size(); i++) {
				if (isCpjgEnd(wsnrList.get(i))) {
					end = i;
					break;
				}
			}
			if (end > pre) {
				cpjg=new ArrayList<String>();
			}
			end--;
			for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
				cpjg.add(wsnrList.get(i));
			}
		}
		
	}
	private boolean isCpjgEnd(String word) {
		if (word.contains("审判长") || word.contains("审判员")|| word.contains("书记员") 
				|| word.contains("代理审判员") || word.contains("速录员") || word.contains("人民陪审员")||(word.length()<14&&word.contains("年")&&word.contains("月")&&word.contains("日")))
			return true;
		return false;
	}
	
	@Override
	public void hfWw() {
		int pre = ++end;
		for (int i = end; i < wsnrList.size(); i++) {
			if (isWwEnd(wsnrList.get(i))) {
				end = i+1;
				break;
			}
		}
		if (end > pre) {
			ww=new ArrayList<String>();
		}
		end--;
		for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
			ww.add(wsnrList.get(i));
		}
	}
	private boolean isWwEnd(String word) {
		if (word.contains("速录") && word.contains("书记")){ 
			return true;
		}
		else if (word.indexOf("速录")==-1 && word.contains("书记")){
		return true;
		}
			return false;
	}
	
	public void hfFl() {
		// TODO Auto-generated method stub
		int pre = ++end;
		for (int i = end; i < wsnrList.size(); i++) {
			if (isFlEnd(wsnrList.get(i))) {
				end = i;
				break;
			}
		}
		if (end > pre) {
			fl=new ArrayList<String>();
		}
		end--;
		for (int i = pre; i < end + 1 && i < wsnrList.size(); i++) {
			fl.add(wsnrList.get(i));
		}
	}
	private boolean isFlEnd(String word) {
		if (word.contains("PAGE"))
			return true;
		return false;
	}

	public String getWswjm() {
		return wswjm;
	}

	public void setWswjm(String wswjm) {
		this.wswjm = wswjm;
	}

	public String getWsnr() {
		return wsnr;
	}

	public void setWsnr(String wsnr) {
		this.wsnr = wsnr;
	}

	public List<String> getWs() {
		return ws;
	}

	public void setWs(List<String> ws) {
		this.ws = ws;
	}

	public List<String> getSscyr() {
		return sscyr;
	}

	public void setSscyr(List<String> sscyr) {
		this.sscyr = sscyr;
	}

	public List<String> getAjjbqk() {
		return ajjbqk;
	}

	public void setAjjbqk(List<String> ajjbqk) {
		this.ajjbqk = ajjbqk;
	}

	public List<String> getCpfxgc() {
		return cpfxgc;
	}

	public void setCpfxgc(List<String> cpfxgc) {
		this.cpfxgc = cpfxgc;
	}

	public List<String> getCpjg() {
		return cpjg;
	}

	public void setCpjg(List<String> cpjg) {
		this.cpjg = cpjg;
	}

	public List<String> getWw() {
		return ww;
	}

	public void setWw(List<String> ww) {
		this.ww = ww;
	}

	public List<String> getFl() {
		return fl;
	}

	public void setFl(List<String> fl) {
		this.fl = fl;
	}

	public String getSsjl() {
		return ssjl;
	}

	public void setSsjl(String ssjl) {
		this.ssjl = ssjl;
	}

	public void hfFj() {
		// TODO Auto-generated method stub
		
	}


	public static void main(String[] args){
		String ssjl = "原告朱文治与被告日新（天津）塑胶有限公司劳动争议一案，本院于2015年9月6日立案受理。依法由代理审判员李洪适用简易程序公开开庭进行了审理。原告之委托代理人钟磊，被告之委托代理人高玮、汪美婧到庭参加诉讼。本案现已审理终结。";
		ArrayList<String> contentlist = WsAnalyse.getWholeContent(ssjl);
	//	System.out.println(contentlist.size());
	}

}
