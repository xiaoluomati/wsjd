package nju.software.wsjx.model.wsSegmentationModel;

import java.util.List;

public class WsModelWrapper {
	public WsModel wsModel;
	public List<String> errInfoList;//异常堆栈信息
	public String wjm;//文件名
	
	public WsModelWrapper(){
		super();
	}

	public WsModel getWsModel() {
		return wsModel;
	}

	public void setWsModel(WsModel wsModel) {
		this.wsModel = wsModel;
	}

	public List<String> getErrInfoList() {
		return errInfoList;
	}

	public void setErrInfoList(List<String> errInfoList) {
		this.errInfoList = errInfoList;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}

}
