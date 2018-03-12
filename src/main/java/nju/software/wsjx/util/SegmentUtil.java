package nju.software.wsjx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.Enum.WslbEnum;

/**
 * 分类工具类
 * 
 * @author lr12
 *
 */
public class SegmentUtil {

	/**
	 * 预处理，按照换行切分，把一些空格替换
	 * @param words
	 * @return
	 */
	public static List<String> toParagraph(String words) {
		List<String> paragraphs = new ArrayList<String>();
		if (words != null) {
			words = words.replaceAll("[\\r]", "");
			words = words.replaceAll("[\\t]", "");
			// 去除全角半角空格
			words = words.replaceAll("　", "");
			words = words.replaceAll(" ", "");
			String[] qfsj = words.split("\n");
			for (int i = 0; i < qfsj.length; i++) {
				if (!qfsj[i].equals("")) {
					paragraphs.add(qfsj[i]);
				}

			}

		}

		return paragraphs;
	}

	/**
	 * 获取文书类别例如（庭审笔录、裁判文书等）
	 * @param paragraphs
	 * @return
	 */
	public static WslbEnum getWslb(List<String> paragraphs){
		if(paragraphs==null||paragraphs.size()<2)
			return WslbEnum.QT;
		String wslbWords=getWslbparagraphs(paragraphs);
		Pattern tsblpPattern=Pattern.compile("笔录");
		Matcher matcher= tsblpPattern.matcher(wslbWords);
		if(matcher.find()){
			return WslbEnum.TSBL;
		}
		if(wslbWords.contains("判决"))
			return WslbEnum.PJS;
		if(wslbWords.contains("裁定"))
			return WslbEnum.CDS;
		if(wslbWords.contains("调解"))
			return WslbEnum.TJS;
		return WslbEnum.QT;
	}
	/**
	 * 获取文书类别段
	 * @param paragraphs
	 * @return
	 */
	public static String getWslbparagraphs(List<String> paragraphs){
		if(paragraphs==null||paragraphs.size()<2)
			return null;
		int index=-1;
		for(int i=0;i<paragraphs.size();i++){
			if(paragraphs.get(i).contains("法院")){
				index=i;
				break;
			}
		}
		if(index==-1||index>=paragraphs.size()-1)
			return null;
		//获取第二段话
		return paragraphs.get(index+1);
		
	}
}
