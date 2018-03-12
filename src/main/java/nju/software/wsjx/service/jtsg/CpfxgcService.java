package nju.software.wsjx.service.jtsg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.util.StringUtil;

public interface CpfxgcService {

	/**
	 * 在裁判分析过程中，查找交通事故过错方
	 * @param cpfxgc
	 * @return
	 */
	public void getGcf(List<String> cpfxgc, List<WssscyrModel> wssscyrModellist);
	
	public  void setQlywr(List<String> cpfxgc, List<WssscyrModel> wssscyrModellist);
}
