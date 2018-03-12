package nju.software.wsjx.model.caseinfo;

import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.parse.ParseXzysSegment;


/**
 * 行政一审文档
 * @author lr12
 *
 */
public class XzysCaseInfo extends BaseCaseInfo{

	public XzysCaseInfo(){
		
	}
	//个性化的属性和方法
	
	@Override
	public void generate(WsModel wsModel) {
		// TODO Auto-generated method stub
		this.wdbs="xzys";
	}	
	
}
