package nju.software.wsjx.service.model;

import java.util.ArrayList;
/**
 * 诉讼记录段的指控记录Model,刑事案件特有
 * @author DTLXY
 *
 */
public class WsssjlZkjlModel {
	private ArrayList<String> xgr;//相关人
	private ArrayList<WsssjlZkzmModel> zkzmModelist;
	public ArrayList<String> getXgr() {
		return xgr;
	}

	public void setXgr(ArrayList<String> xgr) {
		this.xgr = xgr;
	}

	public ArrayList<WsssjlZkzmModel> getZkzmModelist() {
		return zkzmModelist;
	}

	public void setZkzmModelist(ArrayList<WsssjlZkzmModel> zkzmModelist) {
		this.zkzmModelist = zkzmModelist;
	}

	
}
