package nju.software.wsjx.parse;

import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
/**
 * 存放解析固定规则段落的逻辑，例如文首，文尾,附录段
 * @author lr12
 *
 */
public interface ParseFixedSegment {

	//解析固定的段落逻辑
	public WswsModel jxWswsModel(List<String> wsws);
	
	//解析固定的文书文尾
	public WswwModel jxWswwModel(List<String> wsww);
	
}
