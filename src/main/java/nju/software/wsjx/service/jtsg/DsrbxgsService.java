package nju.software.wsjx.service.jtsg;

import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

/**
 * 用于判断文书是否符合交通事故责任纠纷案件审理标准
 * 当事人部分
 * @author Admin
 *
 */
public interface DsrbxgsService {
	public boolean jqxBg(List<WssscyrModel> sscyrModels);
}
