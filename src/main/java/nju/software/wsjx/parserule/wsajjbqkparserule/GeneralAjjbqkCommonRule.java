package nju.software.wsjx.parserule.wsajjbqkparserule;

import java.util.ArrayList;
import java.util.List;
/**
 * 案件基本情况的通用方法
 * @author wangzh
 *
 */
public class GeneralAjjbqkCommonRule {
	public static int worddis(String word1,String word2,String content){
		int dis=-1;
		List<Integer> list1=new ArrayList<Integer>();
		List<Integer> list2=new ArrayList<Integer>();
		String word1content=content;
		String word2content=content;
		int word1index=0;
		int word2index=0;
		while(word1content.indexOf(word1)>-1){
			list1.add(word1content.indexOf(word1)+word1index);
			word1index+=word1content.indexOf(word1);
			word1content=word1content.substring(word1content.indexOf(word1)+1);
		}
		while(word2content.indexOf(word2)>-1){
			list2.add(word2content.indexOf(word2)+word2index);
			word2index+=word2content.indexOf(word2);
			word2content=word2content.substring(word2content.indexOf(word2)+1);

		}
		List<Integer> num=new ArrayList<Integer>();
		for(int i=0;i<list1.size();i++){
			for(int j=0;j<list2.size();j++){
				if((list2.get(j)-list1.get(i))>0){
					num.add(list2.get(j)-list1.get(i));
				}
			}
		}
		if(num.size()>0){
			dis=num.get(0);
			for(int i=1;i<num.size();i++){
				if(num.get(i)<dis)
					dis=num.get(i);
			}
		}

		return dis;
	}

}
