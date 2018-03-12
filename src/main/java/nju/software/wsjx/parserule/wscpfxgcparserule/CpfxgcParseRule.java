package nju.software.wsjx.parserule.wscpfxgcparserule;


import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

/**
 * 裁判分析过程解析
 * @author lr12
 *
 */
public interface CpfxgcParseRule {
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) throws ParseException;
}