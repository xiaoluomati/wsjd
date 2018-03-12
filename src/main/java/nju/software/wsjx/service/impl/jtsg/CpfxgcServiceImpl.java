package nju.software.wsjx.service.impl.jtsg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.jtsg.CpfxgcService;
import nju.software.wsjx.util.StringUtil;

public class CpfxgcServiceImpl implements CpfxgcService{

	@Override
	public void getGcf(List<String> cpfxgc,List<WssscyrModel> wssscyrModellist) {
		// TODO Auto-generated method stub
//		遍历每句裁判分析过程
		int count = 0;
		List<Pattern> patternList = new ArrayList<>();
		for(WssscyrModel cyr:wssscyrModellist){
			String reg = cyr.getSscyr()+"[\u4e00-\u9fa5]*[负,承担]+[\u4e00-\u9fa5]*责任";
//			String reg = cyr.getSscyr()+"[^x00-xff]*[负,承担]+[^x00-xff]*责任";
			Pattern p = Pattern.compile(reg);
			patternList.add(p);
		}
		Matcher m ;
		for(String content:cpfxgc){
			for(Pattern p:patternList){
				m = p.matcher(content);
				if(m.find()){
					count++;
					int index = patternList.indexOf(p);
					wssscyrModellist.get(index).setJtsgzr(m.group().replaceAll(wssscyrModellist.get(index).getSscyr(), ""));
				}
			}
		}
		if(count==0){
			
		}
		System.out.println(1);
	}
	 
	public void setQlywr(List<String> cpfxgc,List<WssscyrModel> wssscyrModellist){
        String pjjg = "";
        for(String content:cpfxgc){
        	pjjg = pjjg+content;
        }
		HashMap<String,String> qlr = new HashMap<String, String>();
		HashMap<String,String> ywr = new HashMap<String, String>();
//		所有姓名的index
		int firstIndex = getMin(pjjg);//最前动词
		
		HashMap<String, Integer> nameIndexMap = new HashMap<String, Integer>();
		for(WssscyrModel cyr:wssscyrModellist){
			if(!StringUtil.isBlank(cyr.getSscyr())){
				String name  = cyr.getSscyr(); 
				int nameIndex = pjjg.indexOf(name);
				nameIndexMap.put(name, nameIndex);
			}
		}
//		如果存在名字在动词之后的，则属于前后情况，flag=false
		boolean flag=true;
		if(firstIndex>-1&&firstIndex!=pjjg.length()){
			for(Map.Entry<String, Integer> entry:nameIndexMap.entrySet()){
				if(entry.getValue()>firstIndex){
					flag=false;
					break;
				}
			}
		}
		
//		第一个关于支付的动词，之前为义务人，之后为权利人
//		如果所有的姓名都在动词之前，否则使用前后方法
		if(!flag&&firstIndex>-1&&firstIndex!=pjjg.length()){
			for( WssscyrModel  sscyr:wssscyrModellist){
				String name = sscyr.getSscyr(); 
				int nameIndex = pjjg.indexOf(name);
				if(nameIndex>-1 &&nameIndex<firstIndex ){
					//义务人是主语
					sscyr.setSfcdpczr("承担赔偿责任");
					ywr.put(name, sscyr.getSssf());
				}else if(nameIndex>-1 &&nameIndex>firstIndex){
					sscyr.setSfcdpczr("不承担赔偿责任");
				}
			}
		}else if(flag&&firstIndex>-1&&firstIndex!=pjjg.length()){
//			所有名字在动词之前
			int qlIndex=-1;
			for( WssscyrModel  sscyr:wssscyrModellist){
				String name = sscyr.getSscyr(); 
				if(!StringUtil.isBlank(name)){
					String ssdwname = sscyr.getSssf()+sscyr.getSscyr(); 
//					如果向当事人存在，那么这个当事人是第一个权利人
					if(nameIndexMap.get(name)>-1 &&(pjjg.indexOf("向"+name)>0||(pjjg.indexOf("向"+ssdwname)>0))){
						sscyr.setSfcdpczr("不承担赔偿责任");
						qlIndex=nameIndexMap.get(name);
						break;
					}
				}
			}
			if(qlIndex!=-1){
				for( WssscyrModel  sscyr:wssscyrModellist){
					String name = sscyr.getSscyr(); 
					if(!StringUtil.isBlank(name)){
						if(nameIndexMap.get(name)>-1 && nameIndexMap.get(name)<qlIndex){
							sscyr.setSfcdpczr("承担赔偿责任");
						}
					}
					
				}
			}
		}
	}
	/**
	 * 获得权利人义务人判断的中介点，即第一个关于支付的动词位置
	 * @param content
	 * @return
	 */
	public static int getMin(String content){
		List<Integer> indexList = new ArrayList<Integer>();
		indexList.add(content.indexOf("赔偿"));
		indexList.add(content.indexOf("支付"));
		indexList.add(content.indexOf("承担"));
		indexList.add(content.indexOf("负担"));
		indexList.add(content.indexOf("给付"));
		indexList.add(content.indexOf("返还"));
		indexList.add(content.indexOf("偿还"));
		indexList.add(content.indexOf("赔付"));
		indexList.add(content.indexOf("补偿"));
		int result=content.length();
		for(Integer index:indexList){
			if(index!=-1 && index<result){
				result = index;
			}
		}
		return result;
	}
}
