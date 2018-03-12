package nju.software.wsjx.parse;

import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

/**
 * 定义灵活的解析段落接口，诉讼参与人,诉讼记录,案件基本情况，裁判分析过程，裁判结果
 * @author lr12
 *
 */
public interface ParseflexibleSegment {

	public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist);
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse);
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist);
	public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse);
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl);
}
