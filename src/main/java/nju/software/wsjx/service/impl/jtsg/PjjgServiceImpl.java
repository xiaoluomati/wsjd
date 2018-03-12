package nju.software.wsjx.service.impl.jtsg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.jtsg.PjjgService;
import nju.software.wsjx.util.StringUtil;

public class PjjgServiceImpl implements PjjgService{

	@Override
	public boolean isPeichangIndexCorrect(String content, List<WssscyrModel> models) {
		// TODO Auto-generated method stub
		Map<String,Integer> indexMap = new HashMap<String,Integer>();
		for(WssscyrModel model:models){
			int index = StringUtil.indexOf(content, model.getSscyr());
			model.setPeichangIndex(index);
			if(StringUtil.contains(model.getSscyr(), "保险") 
					&& indexMap.get("保险")==null && index !=-1){
				indexMap.put("保险", index);
			}else if(StringUtil.contains(model.getSsdw(), "被告") && !StringUtil.contains(model.getSsdw(), "保险")
					&& (indexMap.get("被告")==null || indexMap.get("被告")>index)&& index !=-1){
				indexMap.put("被告", index);
			}
			
		}
		if(indexMap.get("保险")!=null && indexMap.get("被告")!=null && indexMap.get("保险") >indexMap.get("被告")  ){
			return false;
		}
		return true;
	}

}
