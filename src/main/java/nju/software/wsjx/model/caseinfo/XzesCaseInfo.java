package nju.software.wsjx.model.caseinfo;

import nju.software.wsjx.model.wsSegmentationModel.WsModel;

/**
 * 行政二审案件信息
 * @author wangzh
 *
 */
public class XzesCaseInfo extends BaseCaseInfo{

	public XzesCaseInfo(){
		
	}
	//个性化的属性和方法
	
	@Override
	public void generate(WsModel wsModel) {
		// TODO Auto-generated method stub
		this.wdbs="xzes";
	}	
	
}
