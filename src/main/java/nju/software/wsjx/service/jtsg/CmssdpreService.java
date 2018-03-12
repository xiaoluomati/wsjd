package nju.software.wsjx.service.jtsg;

import java.util.List;

public interface CmssdpreService {
	/**
	 * 提取事故详情
	 */
	public String getSgxq(List<String> cmssd);
	/**
	 * 提取事故时间
	 */
	public String getSgsj(String sgxq);
	/**
	 * 提取事故地点
	 */
	public String getSgdd(String sgxq);
	/**
	 * 提取事故经过
	 */
	public String getSgjg(String sgxq);
	/**
	 * 机动车所有人
	 */
	public String getJdcsyr(List<String> cmssd, String sgxq, List<String> bglist);
	/**
	 * 机动车管理人
	 */
	public String getJdcglr(List<String> cmssd, String sgxq, List<String> bglist);
	/**
	 * 公安机关认定意见
	 */
	public String getGajgrdyj(List<String> cmssd);
	/**
	 * 受害人居住地
	 */
	public String getShrjzd(List wssscyrModellist);
	/**
	 * 受害人职业
	 */
	public String getShrzy(List wssscyrModellist);
}
