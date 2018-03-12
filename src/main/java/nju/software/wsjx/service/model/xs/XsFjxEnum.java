package nju.software.wsjx.service.model.xs;

import nju.software.wsjx.util.StringUtil;

/**
 * 刑事附加刑
 * @author 服兰
 *
 */
public enum XsFjxEnum {
	
	BDZZQL("剥夺政治权利"),//lb,qx
	FJ("罚金"),
	QZCJ("驱逐出境"),//lb
	MSGRBFCC("没收个人部分财产"),
	MSGRQBCC("没收个人全部财产"),
	BDJX("剥夺军衔");//lb
	String fjx;

	private XsFjxEnum(String fjx) {
		this.fjx = fjx;
	}

	public String getFjx() {
		return fjx;
	}

	public void setFjx(String fjx) {
		this.fjx = fjx;
	}
	
	public static XsFjxEnum getFjx(String content){
		for(XsFjxEnum fjx:XsFjxEnum.values()){
			if(StringUtil.contains(content, fjx.getFjx())){
				return fjx;
			}
		}
		if(StringUtil.contains(content, "没收财产")){
			return MSGRBFCC;
		}
		return null;
	}
}
