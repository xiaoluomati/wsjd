package nju.software.wsjx.service.jtsg;

import java.util.List;

/**
 * 车辆投保情况
 * @author LXY
 *
 */
public interface CltbqkService {
	/**
	 * 是否投保
	 * @param cmss
	 * @return
	 */
	public boolean isToubao(List<String> cmsslist);
	/**
	 * 投保险种
	 * @param cmss
	 * @return
	 */
	public String touBaoXZ(List<String> cmsslist);
	/**
	 * 是否在保险期内
	 * @param cmss
	 * @return
	 */
	public boolean isValid(List<String> cmsslist);
	/**
	 * 是否先期赔付
	 * @param cmss
	 * @return
	 */
	public boolean isPay(List<String> cmsslist);
	/**
	 * 鉴定是否可信
	 * @param cmss
	 * @return
	 */
	public boolean identifyIsTrue(List<String> cmsslist);
	/**
	 * 伤情情况
	 * @param cmsslist
	 * @return
	 */
	public String getShangQing(List<String> cmsslist);
	/**
	 * 实际支出情况
	 * @param cmsslist
	 * @return
	 */
	public String getRealPay(List<String> cmsslist);
	/**
	 * 相关鉴定
	 * @param cmsslist
	 * @return
	 */
	public String getIdentifyContent(List<String> cmsslist);
}
