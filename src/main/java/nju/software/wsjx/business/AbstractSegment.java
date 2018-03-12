package nju.software.wsjx.business;

import java.util.List;
import java.util.Map;

/**
 * 抽象分段
 * @author lr12
 *
 */
public interface AbstractSegment {

	
	/**
	 * 初始化
	 * @param wsnr
	 * @return
	 */
	public List<String> inint(String wsnr);
	
	/**
	 * 分段
	 * @param paragraphs
	 * @return
	 */
	public Map<String, List<String>> segment(List<String> paragraphs);
}
