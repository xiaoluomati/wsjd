package nju.software.wsjx.parserule.wswwparserule;







import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.WwEnum;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.util.DateUtil;
import nju.software.wsjx.util.StringUtil;

public class GeneralWwParseRule implements WwParseRule{

	public WswwModel jxWswwModel(List<String> wsww) throws ParseException{
		// TODO Auto-generated method stub
		WswwModel wswwModel = new WswwModel();
		if(wsww == null)
			return wswwModel;
		HashMap<String, String> spzzcyMap = new HashMap<String, String>();//审判组织成员Map
	
		for (int i = 0; i < wsww.size(); i++) {
			String content = wsww.get(i);
			String wwsf = WwEnum.getWw(content);
			// 解析身份和名称
			if(wwsf!=null){
				
				int index = content.indexOf(wwsf);
				String wwmc = content.substring(index + wwsf.length(),
						content.length());
				String js = WwEnum.getWw(wwmc);
				//审判人员之间没有空格
				if(js!=null){
					content = WwEnum.addBlank(content);
					String[] contentWithBlank = content.split(" ");
					for(String s:contentWithBlank){
						wsww.add(s);
					}
				}else{
//					书记员xxx（兼）
					if(StringUtil.equals(wwsf, "书记员")&&StringUtil.contains(wwmc, "（兼）")){
						wwsf = WwEnum.SJYJ.getContent();
						wwmc = wwmc.substring(0,wwmc.indexOf("（兼）"));
					}else if(StringUtil.equals(wwsf, "书记员")&&StringUtil.contains(wwmc, "（代）")){
						wwsf = WwEnum.SJYD.getContent();
						wwmc = wwmc.substring(0,wwmc.indexOf("（代）"));
					}
					spzzcyMap.put(wwmc, wwsf);
				}
			}else if (content.contains("年") || content.contains("月")
					|| content.contains("日")) {
				try{
					String wsrq;
					boolean hasWsrq = content.length() > 8;// content的长度大于"xxxx年x月x日"的长度则存在文书日期
					if (!hasWsrq) {
						wsrq = null;
					} else {
						wsrq = DateUtil.convertToCNDate(content);
					}
					if (wsrq != null) {
//						将汉字日期转化为阿拉伯数字日期
						wsrq = DateUtil.convertToCNDate(wsrq);
						if((wsrq.indexOf("年") - 4)>-1){
							String year = wsrq.substring(wsrq.indexOf("年") - 4,
									wsrq.indexOf("年"));
							String month = wsrq.substring(wsrq.indexOf("年") + 1,
									wsrq.indexOf("月"));
							String day = wsrq.substring(wsrq.indexOf("月") + 1,
									wsrq.indexOf("日"));
							String yearMonth = year+"-"+month;
							wsrq=wsrq==null?null:wsrq.replace("本件与原本核对无异", "").replace("null日", "");
							wswwModel.setWsrq(wsrq);
							wswwModel.setYear(year);
							wswwModel.setMonth(month);
							wswwModel.setDay(day);
							wswwModel.setYearAndMonth(yearMonth);
						}
					}
				}catch(Exception e){
					
				}
				
			}

		}
		wswwModel.setSpzzcyMap(spzzcyMap);

		return wswwModel;
	}
	


}
