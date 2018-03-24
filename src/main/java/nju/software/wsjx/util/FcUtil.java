package nju.software.wsjx.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;
/**
 * 分词工具，获取某个字符串的所有分词
 * @author lr12
 *
 */
public class FcUtil {
	public static List<String> getTokens(String keyword) 
		{
			// TODO Auto-generated method stub
			List<String> tokens = new ArrayList<String>();
			if (StringUtil.isBlank(keyword)) {
				return tokens;
			}
			String text = keyword;
			// 创建分词对象
			// 遍历分词数据
			try {

				Analyzer anal = Constant.Analyzer;
				StringReader reader = new StringReader(text);
				// 分词
				TokenStream ts = anal.tokenStream("", reader);
				ts.reset();
				CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
				while (ts.incrementToken()) {
					tokens.add(term.toString());
				}
				reader.close();
				ts.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tokens.clear();
				tokens.add(keyword);
			}
			return tokens;
	}
	public static List<String> getWholeToken(String keyword){
		List<String> tokens = new ArrayList<String>();
		if (StringUtil.isBlank(keyword)) {
			return tokens;
		}
		String text = keyword;
		// 创建分词对象

		// 遍历分词数据

		
		try {

			IKAnalyzer ikAnalyzer=new IKAnalyzer();
			ikAnalyzer.setUseSmart(true);
			
			StringReader reader = new StringReader(text);
			// 分词
			TokenStream ts = ikAnalyzer.tokenStream("", reader);
			ts.reset();
			CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);

			while (ts.incrementToken()) {
				
				tokens.add(term.toString());
				//System.out.print(term.toString() + "|");
			}
			reader.close();
			ts.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tokens.clear();
			tokens.add(keyword);
		}

		return tokens;
	}
	public static void main(String[] args){
		ArrayList<String> contentlist = new ArrayList<String>();
		String rexCh="[一二三四五六七八九十]";
		String rexNu="\\d{1}";
		Pattern patternCh=Pattern.compile(rexCh);
		Pattern patternNu=Pattern.compile(rexNu);
		String content="第3829451号图形商标（见下";
		if(content.length()>=4)
			content=content.substring(0,4);
		Matcher matcherCh=patternCh.matcher(content);
		Matcher matcherNu=patternNu.matcher(content);
		int num=0;
		while(matcherNu.find()){
			num++;
		}
System.out.println(num);
	
		String str="上述两原审第三人的共同委托代理人苏广毅";
		String[] st=str.split("。");
		System.out.println(st.length);
		for(int i=0;i<st.length;i++)
		System.out.println(st[i]);
		System.out.println(getTokens("原告苏广毅以被告已经履行支付义务为由"));
//		String str="原告刘莹（反诉被告），女，1971年10月18日出生，汉族，厦门国际银行北京分行干部，"
//				+ "住天津市河西区黑牛城道与紫金山路交口新世纪20-2-401，公民身份号码130705197110180964。";
//		String str1="原告刘莹（反诉被告），女，1971年10月18日出生。"+str;
//		String str2="原告刘莹（反诉被告";
//		String[] jhsplit=str1.split("。");
//		for(int i=0;i<jhsplit.length;i++){
//			String content=jhsplit[i];
//			String []dhsplit=content.split("，");
//			for(int j=0;j<dhsplit.length;j++){
//				if(dhsplit[j].length()>0){
//					contentlist.add(dhsplit[j]);
//				}
//			}
//		}
//		String str="原告天津市滨海华洋《电线电》缆销售有限公司";
//		String str1=null;
//		if(str.indexOf("身份")!=-1){
//			ArrayList<String> zjxx=(ArrayList<String>) FcUtil.getWholeToken(str);
//			  Pattern pattern = Pattern.compile("\\d"); 
//			  for(int k=0;k<zjxx.size();k++){
//				  int count=0;
//				   Matcher match = pattern.matcher(zjxx.get(k));
//				   while(match.find()) count++;   
//				   if(count>=0) {
//					   str1=zjxx.get(k);
//
//				   }
//
//			  }
//		}

//System.out.println(str.substring(str.indexOf("《"),str.indexOf("》")));
        }
	
}
