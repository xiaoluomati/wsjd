package nju.software.wsjx.parserule.wswsparserule;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;

/**
 * 因为文首几乎不会有改动，定义一个通用的解析规则
 * @author lr12
 *
 */
public class GeneralWsParseRule implements WsParseRule{

	@Override
	public WswsModel jxWswsModel(List<String> wsws) throws ParseException {
		WswsModel wswsModel = new WswsModel();
		if(wsws == null)
			return wswsModel;
		String find1 = "法院";
		String find2 = "书";
		String find3 = "号";
		//分析文书制作单位
		String wszzdw="法院";
		wswsModel.setWszzdw(wszzdw);
		Pattern p1 = Pattern.compile(find1);
		Pattern p2 = Pattern.compile(find2);
		Pattern p3 = Pattern.compile(find3);
		for (int i = 0; i < wsws.size(); i++) {
			Matcher matcher1 = p1.matcher(wsws.get(i));
			Matcher matcher2 = p2.matcher(wsws.get(i));
			Matcher matcher3 = p3.matcher(wsws.get(i));
			if (matcher1.find()){
				String jbfy=wsws.get(i);
				wswsModel.setJbfy(jbfy);
				//法院级别分析
				if(jbfy.contains("高级")){
					wswsModel.setFyjb("高级");
				}else if(jbfy.contains("中级")){
					wswsModel.setFyjb("中级");
				}else if(jbfy.contains("最高")){
					wswsModel.setFyjb("最高");
				}else{
					wswsModel.setFyjb("基层");
				}
				//行政区划（省）分析
				String[] zxsqj={"北京","天津","上海","重庆"};//直辖市全集
				String[] zzqqj={"内蒙古","广西","西藏","宁夏","新疆"};//自治区全集
				String xzqhProv=null;
				boolean hasZzs=false;
				for(String zzx:zxsqj){
					if(jbfy.contains(zzx)){
						xzqhProv=zzx;
						hasZzs=true;
					}
				}
				for(String zzq:zzqqj){
					if(jbfy.contains(zzq)){
						xzqhProv=zzq;
					}
				}
				if(jbfy.indexOf("省")!=-1){
					xzqhProv=jbfy.substring(0, jbfy.indexOf("省"));
				}
				wswsModel.setXzqhProv(xzqhProv);
				
				//行政区划（市）分析
				String xzqhCity=null;
				
				if(jbfy.indexOf("市")!=-1&&jbfy.indexOf("省")!=-1){
					if (jbfy.indexOf("省")<jbfy.indexOf("市"))
					xzqhCity=jbfy.substring(jbfy.indexOf("省")+1, jbfy.indexOf("市")+1);
				}else if(jbfy.indexOf("市")!=-1&&jbfy.indexOf("自治区")!=-1){
					if (jbfy.indexOf("区")<jbfy.indexOf("市"))
					xzqhCity=jbfy.substring(jbfy.indexOf("区")+1, jbfy.indexOf("市")+1);
				}else if(jbfy.indexOf("市")!=-1&&!hasZzs){
					if (0<jbfy.indexOf("市"))
					xzqhCity=jbfy.substring(0, jbfy.indexOf("市")+1);
				}
				else if(jbfy.indexOf("市")==-1){
					xzqhCity=xzqhProv;
				}
				//考虑一中院二中院情况
				for(String zzx:zxsqj){
					if(jbfy.contains(zzx)){
						if(jbfy.contains("第一中级")){
							xzqhCity="一中院";
						}else if(jbfy.contains("第二中级")){
							xzqhCity="二中院";
						}else if(jbfy.contains("第三中级")){
							xzqhCity="三中院";
						}
						else{
							xzqhCity=null;
						}
					}
						
				}
				wswsModel.setXzqhCity(xzqhCity);
				
				
				
			}
			
			if (matcher2.find()){
				String wsmc=wsws.get(i);
				wswsModel.setWsmc(wsmc);
				//分析案件性质
				String ajxz=null;//案件性质
				if(wsmc.length()>3)
					ajxz=wsmc.substring(0, 2);
				wswsModel.setAjxz(ajxz+"案件");
				//分析文书种类
				String wszl=null;
				int index_shu=wsmc.indexOf("书");
				if(index_shu!=-1 && index_shu-2 < index_shu+1)
					if (index_shu-2<index_shu+1&&index_shu-2>0)

					wszl=wsmc.substring(index_shu-2, index_shu+1);
				wswsModel.setWszl(wszl);
						
				}
			
			if (matcher3.find() && (!wsws.get(i).contains(",")) && (!wsws.get(i).contains("。")) 
					&& (!wsws.get(i).contains("，")) && (!wsws.get(i).contains("。")) ) {				
				String ah=wsws.get(i);
				ah = delUrl(ah);//删除链接
				ah = SBC2DBC(ah);//全角转半角								
				wswsModel.setAh(ah);
				String year = "2016";
				String temp = wsws.get(i);
				
				
				String str = null;

//				if (temp.lastIndexOf("（") != -1) {
//					str = temp.substring(temp.lastIndexOf("（"),
//							temp.lastIndexOf("）"));
//					year = str.substring(1, str.length());
//				} else if (temp.lastIndexOf("(") != -1) {
//					str = temp.substring(temp.lastIndexOf("("),
//							temp.lastIndexOf(")"));
//					year = str.substring(1, str.length());
//				} else if (temp.lastIndexOf("〔") != -1) {
//					str = temp.substring(temp.lastIndexOf("〔"),
//							temp.lastIndexOf("〕"));
//					year = str.substring(1, str.length());
//				}

				if (temp!=null){
					temp = temp.replace("（","(");
					temp = temp.replace("）",")");
				}

				if (temp.indexOf("（") != -1&&temp.indexOf("）")!=-1) {
					if (temp.lastIndexOf("（")<=temp.lastIndexOf("）")) {
						str = temp.substring(temp.lastIndexOf("（"),
								temp.lastIndexOf("）"));
						year = str.substring(1, str.length());						
					}
				} else if (temp.indexOf("(") != -1&&temp.indexOf(")")!=-1) {
					if (temp.lastIndexOf("(")<=temp.lastIndexOf(")")) {
						str = temp.substring(temp.lastIndexOf("("),
								temp.lastIndexOf(")"));
						try{
							int integer = Integer.parseInt(str);
						}
						catch(Exception e){
							str = temp.substring(temp.indexOf("("),
									temp.indexOf(")"));
						}
						year = str.substring(1, str.length());
					}
				} else if (temp.indexOf("〔") != -1&&temp.indexOf("〕")!=-1) {
					if (temp.lastIndexOf("〔")<=temp.lastIndexOf("〕")) {
						str = temp.substring(temp.lastIndexOf("〔"),
								temp.lastIndexOf("〕"));						
						try{
							int integer = Integer.parseInt(str);
						}
						catch(Exception e){
							str = temp.substring(temp.indexOf("〔"),
									temp.indexOf("〕"));
						}
						year = str.substring(1, str.length());
					}
				}else if (temp.indexOf("[") != -1&&temp.indexOf("]")!=-1) {
					if (temp.lastIndexOf("[")<=temp.lastIndexOf("]")) {
						str = temp.substring(temp.lastIndexOf("["),
								temp.lastIndexOf("]"));						
						try{
							int integer = Integer.parseInt(str);
						}
						catch(Exception e){
							str = temp.substring(temp.indexOf("["),
									temp.indexOf("]"));
						}
						year = str.substring(1, str.length());
					}
				}	
				
				//全角转半角
				if (year != null) {
					String land = SBC2DBC(year);
			        if(land != null)
			        	wswsModel.setLand(land);
				}
				
				//分析审判程序（目前只分出一审二审再审）
				String spcx=null;//审判程序
				String[] spcxqj={"一审","二审","再审","再审审查","管辖"};//审判程序全集
				String[] spcxjcqj={"初","终","再","申","辖"};//审判程序简称全集
				//Todo
				//审判程序简称
				for(int j=0;j<spcxqj.length;j++){
					if(temp.contains(spcxjcqj[j])){
						spcx=spcxqj[j];
						
					}
				}
				wswsModel.setSpcx(spcx+"案件");
			}
			
			
			
		}
		//分析案件类型
		String ajlx=null;
		String ajxz=wswsModel.getAjxz();
		String spcx=wswsModel.getSpcx();
		if (ajxz!=null&&wswsModel.getSpcx()!=null&&ajxz.contains("案件"))
			
		ajlx=ajxz.substring(0, ajxz.indexOf("案件"))+wswsModel.getSpcx();
		
		String parseName=ajlx!=null?ajlx.replaceAll("案件", ""):null;
		wswsModel.setParseName(parseName);
		wswsModel.setAjlx(ajlx);

		return wswsModel;
	}
	/**
	 * 全角转半角
	 * @param data
	 * @return
	 */
	public static String SBC2DBC(String data) {
		if(data == null)
			return data;
		char[] charArray = data.toCharArray();  
        for (int k = 0; k < charArray.length; k++) {  
            if (charArray[k] == '\u3000') {  
                charArray[k] =' ';  
            } else if (charArray[k] > '\uFF00' &&  
                    charArray[k]  < '\uFF5F') {  
                charArray[k] = (char) (charArray[k] - 65248);  
            } else {  	
            }  
        }
	    String result = new String(charArray);		
	    return result;		
	}
	/**
	 * 删除链接问题
	 * @param data
	 * @return
	 */
	public static String delUrl(String data) {
		if(data == null)
			return null;
		//删除不可见字符\u200b
		String str = "";
		for (int i = 0; i < data.length(); i++) {
		      int ch = (int) data.charAt(i);
		      if (ch != '\u200b')
		    	  str += data.charAt(i);
		}		
		//删除存在的链接问题
		while(str.contains("(http") || str.contains("(javascript")){			
			int zkh = str.indexOf("(http");
			if(zkh == -1)
				zkh = str.indexOf("(javascript");	
			int khNum = 1;
			int ykh = zkh;
			while(khNum != 0){
				ykh++;
				if(str.charAt(ykh) == '(')
					khNum++;
				if(str.charAt(ykh) == ')')
					khNum--;					
			}
			str = str.substring(0, zkh) + str.substring(ykh + 1);					
		}
		return str;
	}
	
}
