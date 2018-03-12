package nju.software.wsjx.model.facadeModel;

import java.io.Serializable;
import java.util.List;

/**
 * 文书信息
 * @author lr12
 *
 */
@SuppressWarnings("serial")
public class WsxxModel implements Serializable{

	
	/**
	 * 裁判理由
	 */
	private String cply;
	
	/**
	 * 裁判依据
	 */
	private String cpyj;
	
	/**
	 * 裁判结果
	 */
	private List<String> cpjg;

	public String getCply() {
		return cply;
	}

	public void setCply(String cply) {
		this.cply = cply;
	}

	public String getCpyj() {
		return cpyj;
	}

	public void setCpyj(String cpyj) {
		this.cpyj = cpyj;
	}

	public List<String> getCpjg() {
		return cpjg;
	}

	public void setCpjg(List<String> cpjg) {
		this.cpjg = cpjg;
	}

	@Override
	public String toString() {
		return "WsxxModel [cply=" + cply + ", cpyj=" + cpyj + ", cpjg=" + cpjg
				+ "]";
	}

	
	
}
